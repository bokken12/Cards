package clientStates;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import clientStuff.StateMachine;
import messaging.Message;
import player.Player;

public class GameWaitingState extends State {

	 JLabel waiting = new JLabel("Finding a match...");
	 private ArrayList<JButton> deckButtons;
	 JLabel wait = new JLabel("");
	 Player player;
	 ImageIcon background  = new ImageIcon("MenuBackground.jpg");
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, this);
	}

	@Override
	public void MessageRecieved(Message message) {
		
	}
	
	public GameWaitingState(Player p) {
		player = p;
	}

	@Override
	public void onInitialize(StateMachine stater) {
	    deckButtons = new ArrayList<JButton>();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		HashMap<String, ArrayList<Integer>> deecks = player.getDecks();
        Object[] a = deecks.keySet().toArray();

        add(Box.createHorizontalGlue());
        add(waiting);
        wait.setText("Choose a deck:");
        add(wait);
        for (int i = 0; i < a.length; i++)
        {
            JButton b = new JButton(a[i].toString());
            System.out.println(deecks);
            b.addActionListener(this);
            deckButtons.add(b);

            add(b);
        }
        add(Box.createHorizontalGlue());
        revalidate();
        repaint();

	}

	@Override
	public void onBegin(StateMachine stater) {
		

	}

	@Override
	public void onLeave(StateMachine stater) {
		
	}

	@Override
	public void onDestroy(StateMachine stater) {
		

	}

}
