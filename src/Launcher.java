
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
import java.util.Arrays;
import java.util.Set;

import javax.swing.*;

import Player.Player;
import cards.Card;

public class Launcher extends JFrame implements ActionListener {

	private static final boolean DEBUG = true;

	private static final int PORT_NUMBER = 5002;

	/* Our preferred size. */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 600;

	/* How many columns the input field should have. */
	private static final int NUM_COLUMNS = 30;

	/* Input and output streams. */
	private static BufferedReader input;
	private static PrintWriter output;

	/* Text field where the user can enter text to chat with. */
	static Launcher frame;
	private JTextField usernameText;
	private static JTextField passwordText;
	private JButton createAccount;
	private JTextField emailText = new JTextField("Enter Email");
	private static JTextField newUsernameText  = new JTextField("Enter Username");
	private static JTextField newPasswordText = new JTextField("Enter Password");
	private JTextField verifyPasswordText = new JTextField("Verify Password");
	private JButton newcreateAccount = new JButton("Create Account");
	
	private JPanel south;
	JButton createGame = new JButton("Create Game");
	JButton refresh = new JButton("Refresh");
	String username = "";
	JButton play = new JButton("Play");
	JButton cards = new JButton("Cards");
	static String currentline = "";

	public static void main(String[] args){
		frame = new Launcher();
		System.out.println("We're done initializing!");
		try {
			/* Continuously read messages from the source. */
			while (true) {
				currentline = input.readLine();
				//if (line == null) break;

				/*else */
				if(currentline.startsWith("--refresh")) {
					currentline.substring(10);
				} else if (currentline.startsWith("AccountConfirmed")){
					System.out.println("Account confirmed! Yay!");
					doLogin(newUsernameText.getText(), newPasswordText.getText());
				}
			}
		} catch (IOException e) {
			System.out.println("AAAAAaaaAAaaAaaa");
		}
		System.out.println("=== Connection Closed ===");
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
		south.setLayout(new FlowLayout());
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
	public Launcher() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		init();
		this.pack();
		this.setVisible(true);
	}
	public void run(){

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
				doLogin(usernameText.getText(), passwordText.getText());
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
					emailText.addActionListener(frame);
					south.add(newUsernameText);
					newUsernameText.addActionListener(frame);
					south.add(newPasswordText);
					newPasswordText.addActionListener(frame);
					south.add(verifyPasswordText);
					verifyPasswordText.addActionListener(frame);
					south.add(newcreateAccount);
					newcreateAccount.addActionListener(frame);
					south.revalidate();
		      }
		    });
		}
		else if(e.getSource().equals(emailText) || e.getSource().equals(newUsernameText) || e.getSource().equals(newPasswordText) || e.getSource().equals(verifyPasswordText) || e.getSource().equals(newcreateAccount)){
			if (DEBUG) System.out.println("Got text entry");
			if(!(emailText.getText().equals("") || newUsernameText.getText().equals("") || newPasswordText.getText().equals("") || verifyPasswordText.getText().equals(""))){
				if(newPasswordText.getText().equals(verifyPasswordText.getText())){
					sendText("--accountCreation " + emailText.getText() + " " + newUsernameText.getText() + " " + newPasswordText.getText());
					if (DEBUG) System.out.println("Waiting for confirmation on creating an account");
				}
			} else {
				if (DEBUG) System.out.println("Didn't verify: |" + newPasswordText.getText() + "| |" + verifyPasswordText.getText() + "|");
			}
		} 
		else if(e.getSource().equals(createGame)) {
			sendText("--createGame " + username);
		}
		else if(e.getSource().equals(refresh)) {
			sendText("--refresh");
		}
		else if(e.getSource().equals(play)){
			removeAll();
			//I'm not sure how it does it, but I've checked that the refresh is from here. That equals play button is going to have to be examined.
			add(refresh);
			sendText("--refresh"); 
		}
	}

	/**
	 * Sends the specified text to the other user.
	 * 
	 * @param line The text to send.
	 */
	private static void sendText(String line) {
		/* Output the data and flush the output stream to force
		 * the data to send.
		 */
		output.println(line);
		output.flush();

	}
	private boolean accountCreationConfirmation(){
		while(true){
			if(currentline.startsWith("AccountConfirmed")){
				break;
			}
		}
		return true;
	}
	private static Player loginConfirmation(){
		Player player;
		while(true){
			if(currentline.startsWith("--loginaccepted")) {
				String args = currentline.substring(17);
				ArrayList<Card> collection = new ArrayList<Card>();
				ArrayList<String> collectionStrings = new ArrayList<String>(Arrays.asList((args.substring(args.indexOf("cardCollection=") + 15, args.indexOf(", decks") - 1)).split(",")));
				for(String cardname:collectionStrings){
					collection.add(Card.fromName(cardname));
				}			
				player = new Player(args.substring(args.indexOf("email=") + 6, args.indexOf(", username") - 1), 
						args.substring(args.indexOf("username=") + 9, args.indexOf(", password") - 1), 
						args.substring(args.indexOf("password=") + 9, args.indexOf(", cardCollection") - 1), 
						collection, 
						getDecksFromString((args.substring(args.indexOf("decks=") + 6, args.indexOf(", rank") - 1))), 
						Integer.parseInt(args.substring(args.indexOf("rank=") + 5, args.indexOf(", friends") - 1)), 
						new ArrayList<String>(Arrays.asList((args.substring(args.indexOf("friends=") + 8, args.indexOf(", gold") - 1)).split(","))), 
						Integer.parseInt(args.substring(args.lastIndexOf("gold=") + 5, args.indexOf("]") - 1))
						);
				break;
			}
		}
		return player;
	}
	public static void doLogin(String username, String password){
		sendText("--login " + username + " " + password);
		menu(loginConfirmation());
	}
	
	public static void menu(Player player) {
		
	}
	public static Card[][] getDecksFromString(String string){
		return new Card[10][40];
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

