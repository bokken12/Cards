package uselessSubclasses;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class SensibleApplication extends JFrame implements ActionListener {
	
	public static final int SMALL_MAX_NUMBER_TO_CHECK = 30000;
	public static final int MAX_NUMBER_TO_CHECK = 10000000;
	
	JTextArea outputArea;
	JButton justAButton;

	public SensibleApplication() {
		super("Prime number calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = (JPanel) getContentPane();
		
		justAButton = new JButton("Compute!");
		justAButton.addActionListener(this);
		
		final JButton anotherButton = new JButton("Maybe");
		anotherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anotherButton.getText().equals("Maybe")) {
					anotherButton.setText("Maybe not");
				} else {
					anotherButton.setText("Maybe");
				}
			}
		});
		
		outputArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		
		contentPane.add(justAButton, BorderLayout.PAGE_START);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(anotherButton, BorderLayout.PAGE_END);
	}
	
	private static boolean isPrime(int number) {
		int max = (int) Math.sqrt(number);
		for (int i = 2; i <= max; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	

//	// This one is bad news!
//	public void actionPerformed(ActionEvent e) {
//		// Button pressed, compute primes
//		for (int i = 2; i < SMALL_MAX_NUMBER_TO_CHECK; i++) {
//			if (isPrime(i)) {
//				outputArea.setText(outputArea.getText() + i + "\n");				
//			}
//		}
//	}
		
	public void actionPerformed(ActionEvent e) {
		new PrimeFinder().execute();
	}
	
	private class PrimeFinder extends SwingWorker<Integer, String> {
		
        @Override
        protected Integer doInBackground() {
        	int numPrimesFound = 0;
    		for (int i = 2; i < MAX_NUMBER_TO_CHECK; i++) {
    			if (isPrime(i)) {
    				numPrimesFound++;
    				publish(Integer.toString(i) + "\n");
    			}
            }
            return numPrimesFound;
        }
 
    	StringBuilder sb = new StringBuilder();
    	
        @Override
        protected void process(List<String> primes) {
        	for (String str : primes) {
        		sb.append(str);
        	}
        	
        	if (sb.length() > 10000) {
        		outputArea.setText(outputArea.getText() + sb.toString());
        		sb = new StringBuilder();
        	}
        }
        
        protected void done() {
			try {
				int numFound = get();
	        	justAButton.setText("Computed " + numFound + " primes.");
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
        }
    }
 
	
	public static void main(String[] args) {
		// Most old code uses SwingUtilities.invokeLater() or
		// SwingUtilities.invokeAndWait(), but that was last millenium.
		// The preferred way to do this is now with the methods in EventQueue
		EventQueue.invokeLater(new Runnable() {			
			public void run() {
				JFrame frame = new SensibleApplication();
				frame.pack();		
				frame.setVisible(true);
			}
		});
	}

}


// SwingWorker<T,V>
// T - result type returned by doInBackground and get methods
// V - type for intermediate results of publish and process methods (List<V>)
// static class MyWorker extends SwingWorker<Integer, Integer> {}
// T doInBackground() - done on compute thread
// void done() - Performed on EDT at end
// T get() - waits and then returns T when computing done
// boolean isDone()
// publish(V...) - send some V values from compute thread in doInBackground()
// process(List<V>) - deal with some published V on the EDT.
// execute() - set the show in motion
