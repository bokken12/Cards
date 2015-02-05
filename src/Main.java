
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {


	private static final int PORT_NUMBER = 5002;

	/* Our preferred size. */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 600;

	/* How many columns the input field should have. */
	private static final int NUM_COLUMNS = 30;

	/* Input and output streams. */
	private BufferedReader input;
	private PrintWriter output;

	/* Text field where the user can enter text to chat with. */
	private JTextField usernameText;
	private JTextField passwordText;
	private JButton createAccount;
	private JTextField emailText = new JTextField("Enter Email");
	private JTextField newUsernameText  = new JTextField("Enter Username");
	private JTextField newPasswordText = new JTextField("Enter Password");
	private JTextField verifyPasswordText = new JTextField("Verify Password");
	private JPanel south;
	JButton createGame = new JButton("Create Game");
	JButton refresh = new JButton("Refresh");
	String username = "";
	JButton play = new JButton("Play");
	JButton cards = new JButton("Cards");

	public static void main(String[] args){
		JFrame frame = new Main();
	}
	public void init() {
		Socket s = connect();
		System.out.println("=== Connection Established! ===");

		/* Extract the input and output streams from the socket. */
		try {
			input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			output = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			System.out.println("AAaaAAAAaaaaaAAAAA");
		}

		south = new JPanel();
		usernameText = new JTextField("Username", 10);
		usernameText.addActionListener(this);
		//usernameText.addKeyListener(this);
		south.add(usernameText);
		passwordText = new JTextField("Password", 10);
		passwordText.addActionListener(this);
		//passwordText.addKeyListener(this);
		south.add(passwordText);
		createAccount = new JButton("Create Account");
		createAccount.addActionListener(this);
		south.add(createAccount);
		this.add(south);
	}
	public Main() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		init();
		this.pack();
		this.setVisible(true);
		try {
			/* Continuously read messages from the source. */
			while (true) {
				String line = input.readLine();
				if (line == null) break;

				else if(line.startsWith("--refresh")) {
					line.substring(10);
				}

			}
		} catch (IOException e) {
			System.out.println("AAAAAaaaAAaaAaaa");
		}
		System.out.println("=== Connection Closed ===");
	}

	private Socket connect() {
		while (true) {
			try {
				Socket s = new Socket(/*/*"Joels-iMac-3""127.0.0.1"InetAddress.getByName("24.130.146.148"), PORT_NUMBER, */InetAddress.getByName("10.0.1.13"), PORT_NUMBER);
				System.out.println("Socket achieved");
				return s;
			} catch (IOException e) {
				System.out.println("no connection");
				//fatalError("Error connecting to the server, try again later.");
			}
		}  
	}
	public void fatalError(String errorMessage){
		JLabel error = new JLabel(errorMessage);
		//error.setFont("Dialog-20");
		this.add(error, getWidth()/2 - error.getWidth()/2, getHeight()/2 - error.getHeight()/2);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeGame();
	}
	public void closeGame(){
		System.exit(0);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(passwordText)) {
			if(!(usernameText.getText().equals(""))){
				sendText("--login " + usernameText.getText() + " " + passwordText.getText());

				username = usernameText.getText();
				passwordText.setText("");
				if(loginConfirmation()){

				}
			}
		}
		else if(e.getSource().equals(createAccount)){
			/*passwordText.setVisible(false);
			usernameText.setVisible(false);
			createAccount.setVisible(false);*/
			SwingUtilities.invokeLater(new Runnable() 
		    {
		      public void run()
		      {
		    	  	south.removeAll();
					south.add(emailText);
					south.add(newUsernameText);
					south.add(newPasswordText);
					south.add(verifyPasswordText);
					south.revalidate();
		      }
		    });
		}
		else if(e.getSource().equals(emailText) || e.getSource().equals(newUsernameText) || e.getSource().equals(newPasswordText) || e.getSource().equals(verifyPasswordText)){
			if(!(emailText.equals("") || newUsernameText.equals("") || newPasswordText.equals("") || verifyPasswordText.equals(""))){
				if(newPasswordText.equals(verifyPasswordText)){
					sendText("--accountCreation " + emailText + " " + newUsernameText + " " + newPasswordText);
					if(accountCreationConfirmation()){

						//TODO enter game
					}
				}
			} else {

			}
		} 
		else if(e.getSource().equals(createGame)) {
			sendText("--createGame " + username);
		}
		else if(e.getSource().equals(refresh)) {
			sendText("--refresh");
		}
		else if(e.getSource().equals(play));
		removeAll();
		//I'm not sure how it does it, but I've checked that the refresh is from here. That equals play button is going to have to be examined.
		add(refresh);
		sendText("--refresh"); 
	}

	/**
	 * Sends the specified text to the other user.
	 * 
	 * @param line The text to send.
	 */
	private void sendText(String line) {
		/* Output the data and flush the output stream to force
		 * the data to send.
		 */
		output.println(line);
		output.flush();

	}
	private boolean accountCreationConfirmation(){
		while(true){
			String line = "";
			try {
				line = input.readLine();
			} catch (IOException e) {
				line = "";
			}
			if(line.startsWith("AccountConfirmed")){
				break;
			}
		}
		return true;
	}
	private boolean loginConfirmation(){
		while(true){
			String line = "";
			try {
				line = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				line = "";
			}
			if(line.startsWith("--loginaccepted")) {
				removeAll();
				//ArrayList<String> Games = new ArrayList<String>();
				add(play, getWidth()/2, 50);
				add(play, getWidth()/2, 90);
				break;
			}
		}
		return true;
	}
}
/*public void KeyPressed(KeyEvent e){
		if(e.getSource().equals(passwordText)){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(!(usernameText.getText().equals(""))){
					//TODO send username and password
				}

	        }
		}
	}*/

