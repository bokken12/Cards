
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;

public class Main extends GraphicsProgram{


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

	public void init() {
		Socket s = connect();
		println("=== Connection Established! ===");

		/* Extract the input and output streams from the socket. */
		try {
			input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			output = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			throw new ErrorException(e);
		}

		/* Add the text field to the bottom so that we can chat. */
		usernameText = new JTextField("Username", 10);
		usernameText.addActionListener(this);
		//usernameText.addKeyListener(this);
		add(usernameText, SOUTH);
		passwordText = new JTextField("Password", 10);
		passwordText.addActionListener(this);
		//passwordText.addKeyListener(this);
		add(passwordText, SOUTH);
	}
	public void run() {
		try {
			/* Continuously read messages from the source. */
			while (true) {
				String line = input.readLine();
				if (line == null) break;

				println(">>> " + line);
			}
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		println("=== Connection Closed ===");
	}

	private Socket connect() {
		while (true) {
			try {
				Socket s = new Socket("Joels-iMac.local", PORT_NUMBER);

				return s;
			} catch (IOException e) {
				fatalError("Error connecting to the server, try again later.");
			}
		}  
	}
	public void fatalError(String errorMessage){
		GLabel error = new GLabel(errorMessage);
		error.setFont("Dialog-20");
		add(error, getWidth()/2 - error.getWidth()/2, getHeight()/2 - error.getHeight()/2);
		pause(5000);
		closeGame();
	}
	public void closeGame(){
		exit();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == passwordText) {
			if(!(usernameText.getText().equals(""))){
				sendText("--login " + usernameText.getText() + passwordText.getText());

				/* Clear the text box so that we can send another
				 * message.
				 */
				passwordText.setText("");
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
		output.println(line);
		output.flush();

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
}
