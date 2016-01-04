package clientStuff;

import server.Server;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import messaging.LoginAcceptedMessage;
import messaging.LoginMessage;
import messaging.Message;
import messaging.MessageListener;
import messaging.Messager;
import messaging.PlainTextListener;
import player.Player;

public class Launcher extends JFrame implements ActionListener, MessageListener, PlainTextListener {

	private static final boolean DEBUG = true;




	/* Our preferred size. */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 600;

	/* How many columns the input field should have. */
	private static final int NUM_COLUMNS = 30;

	/* Input and output streams. */
	//private static BufferedReader input;
	//private static PrintWriter output;

	static Launcher frame;
	private JTextField usernameText;
	private static JTextField passwordText;
	private JButton login = new JButton("Login");
	private JButton createAccount;
	private JTextField emailText = new JTextField("Enter Email");
	private static JTextField newUsernameText  = new JTextField("Enter Username");
	private static JTextField newPasswordText = new JTextField("Enter Password");
	private JTextField verifyPasswordText = new JTextField("Verify Password");
	private JButton newcreateAccount = new JButton("Create Account");
	static JLabel error = new JLabel("");

	private JPanel south;
	String username = "";
	JButton play = new JButton("Play");
	JButton cards = new JButton("Cards");
	//static String currentline = "";
	java.util.Timer timer = new java.util.Timer();

	Messager m;

	static Game game;

	public static void main(String[] args){
		frame = new Launcher();
		//frame.run();
		System.out.println("We're done initializing!");

	}

	public void init() {
		//Socket s = connect();
	    m = new Messager(this);
	    m.addPlainTextListener(this);
	    m.start();
		System.out.println("=== Connection Established! ===");

		/* Extract the input and output streams from the socket. */
		//try {
		//	input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//	output = new PrintWriter(s.getOutputStream());
		//} catch (IOException e) {
		//	System.out.println("AAaaAAAAaaaaaAAAAA");
		//}

		south = new JPanel();
		south.setLayout(new FlowLayout());
		usernameText = new JTextField("Username", 10);
		usernameText.addActionListener(this);
		south.add(usernameText);
		passwordText = new JTextField("Password", 10);
		passwordText.addActionListener(this);
		south.add(passwordText);
		login.addActionListener(this);
		south.add(login);
		createAccount = new JButton("Create Account");
		createAccount.addActionListener(this);
		south.add(createAccount);
		south.add(error);
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

	//public void run() {
		//try {
			/* Continuously read messages from the source. */
			//while (true) {
				//currentline = input.readLine();
				//if (currentline == null) break;

				
			/*}
		} catch (IOException e) {
			System.out.println("AAAAAaaaAAaaAaaa");
		}
		System.out.println("=== Connection Closed ===");
	}



	private Socket connect() {
		while (true) {
			try {
				Socket s = new Socket(InetAddress.getByName(Server.HOSTNAME), Server.PORT_NUMBER);
				System.out.println("Socket achieved");
				return s;
			} catch (IOException e) {
				System.out.println("no connection");
				//fatalError("Error connecting to the server, try again later.");
			}
		}  
	}*/
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
		if (e.getSource().equals(passwordText) || e.getSource().equals(login)) {
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

					frame.pack();
				}
			});
		}
		else if(e.getSource().equals(emailText) || e.getSource().equals(newUsernameText) 
				|| e.getSource().equals(newPasswordText) || e.getSource().equals(verifyPasswordText) || e.getSource().equals(newcreateAccount)){
			if (DEBUG) System.out.println("Got text entry");
			if(!(emailText.getText().equals("") || newUsernameText.getText().equals("") || newPasswordText.getText().equals("") || verifyPasswordText.getText().equals(""))){
				if(newPasswordText.getText().equals(verifyPasswordText.getText())){
					sendText("--accountCreation " + emailText.getText() + " " + newUsernameText.getText() + " " + newPasswordText.getText());
					if (DEBUG) System.out.println("Waiting for confirmation on creating an account");
				}
			}else {
				if (DEBUG) System.out.println("Didn't verify: |" + newPasswordText.getText() + "| |" + verifyPasswordText.getText() + "|");
			}
		} 


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
		m.sendPlainText(line);

	}
//	private boolean accountCreationConfirmation(){
		//while(true){
			//if(currentline.startsWith("AccountConfirmed")){
			//	break;
			//}
	//	}
	//	return true;
	//}
	private static Player loginConfirmation(){
		Player player;
		while(true){
		    
			/*if(currentline.startsWith("--loginaccepted")) {
				System.out.println("Parsing login acceptance");
				String args = currentline.substring(17);
				ArrayList<Integer> collection = new ArrayList<Integer>();
				ArrayList<String> collectionStrings = new ArrayList<String>(Arrays.asList((args.substring(args.indexOf("cardCollection=") + 15, args.indexOf(", decks") - 1)).split(",")));
				for(int i = 0; i < collectionStrings.size(); i++) {
					collection.add(Integer.parseInt(collectionStrings.get(i)));
				}

				System.out.println((args.substring(args.indexOf("decks=") + 6, args.indexOf(", rank") - 1)));
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
			}*/
		    break;
		}
		return null;
	}
	public void doLogin(String username, String password){
		//sendText("--login " + username + " " + password);

		m.send(new LoginMessage(username, password));
	}

	public void menu(Player player) {

		game = new Game(player, m);
		frame.dispose();
	}


	public static HashMap<String, ArrayList<Integer>> getDecksFromString(String string) {
		HashMap<String, ArrayList<Integer>> ret = new HashMap<String, ArrayList<Integer>>();

		StringTokenizer t = new StringTokenizer(string, "|");

		while(t.hasMoreTokens()) {
			String a = t.nextToken();
			ArrayList<Integer> list = new ArrayList<Integer>();

			int[] arr = getArray(a.substring(a.indexOf("[") - 1 ));

			for(int i : arr) {
				list.add(i);
			}


			ret.put(a.substring(0, a.indexOf("[")), list);
		}

		return ret;

	}

	public static int[] getArray(String ar) {

		String[] digitwords = ar.substring(1, ar.length() - 1).split(", ");
		int[] result = new int[digitwords.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(digitwords[i]);
		}
		return result;
	}

	public void showErrorMessage(String message){

	}

	@Override
	public void MessageRecieved(Message message) {
		if(message instanceof LoginAcceptedMessage) {
			Player player = ((LoginAcceptedMessage) message).getPlayer();
			menu(player);
		}

	}

    @Override
    public void messageRecieved(String currentline)
    {
      System.out.println("Launcher got " + currentline);
        /*else */
        if(currentline.startsWith("--refresh")) {
            currentline.substring(10);
        } else if (currentline.startsWith("AccountConfirmed")){
            System.out.println("Account confirmed! Yay!");
            doLogin(newUsernameText.getText(), newPasswordText.getText());
        } else if(currentline.startsWith("--loginaccepted")) {
            System.out.println("Started Logging in");

            String subbedLine = currentline.substring(16);
            System.out.println(subbedLine);
            Player player = new Player(subbedLine);
            //ArrayList<Integer> collection = new ArrayList<Integer>();
            //ArrayList<String> collectionStrings = new ArrayList<String>(Arrays.asList((subbedLine.substring(subbedLine.indexOf("cardCollection=")
            //      + 16, subbedLine.indexOf(", decks") - 1)).split(", ")));
            //for(int i = 0; i < collectionStrings.size() - 1; i++) {
            //  collection.add(Integer.parseInt(collectionStrings.get(i)));
            //}


            //                  player = new Player(subbedLine.substring(subbedLine.indexOf("email=") + 6, subbedLine.indexOf(", username")), 
            //                          subbedLine.substring(subbedLine.indexOf("username=") + 9, subbedLine.indexOf(", password")), 
            //                          subbedLine.substring(subbedLine.indexOf("password=") + 9, subbedLine.indexOf(", cardCollection")), 
            //                          collection, 
            //                          getDecksFromString((subbedLine.substring(subbedLine.indexOf("decks=") + 6, subbedLine.indexOf(", rank")))), 
            //                          Integer.parseInt(subbedLine.substring(subbedLine.indexOf("rank=") + 5, subbedLine.indexOf(", friends"))), 
            //                          new ArrayList<String>(Arrays.asList((subbedLine.substring(subbedLine.indexOf("friends=") + 8, subbedLine.indexOf(", gold"))).split(","))), 
            //                          Integer.parseInt(subbedLine.substring(subbedLine.indexOf("gold=") + 5, subbedLine.lastIndexOf("]")))
            //                          );
            System.out.println("Finished Logging in");
            menu(player);
        } else if(currentline.startsWith("--match")) {
            game.toContent(currentline);
        } else if(currentline.startsWith("--wait")) {
            game.toContent(currentline);
        } else if(currentline.startsWith("--nameTaken")) {
            error.setText("Username already taken");
            frame.repaint();
            frame.pack();

        } else if(currentline.startsWith("--myBoard")) {
            game.toContent(currentline);
        } else if(currentline.startsWith("--turn")) {
            game.toContent(currentline);
        } else if(currentline.startsWith("--attack")) {
            game.toContent(currentline);
        } else if(currentline.startsWith("--block")) {
            game.toContent(currentline);
        }
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

