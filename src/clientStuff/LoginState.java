package clientStuff;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import messaging.AccountConfirmationMessage;
import messaging.AccountCreationMessage;
import messaging.LoginAcceptedMessage;
import messaging.LoginMessage;
import messaging.Message;
import player.Player;

public class LoginState extends State
{

	private JTextField usernameText;
	private JTextField passwordText;
	private JButton login;
	private JButton createAccount;
	private JTextField emailText;
	private JTextField newUsernameText;
	private JTextField newPasswordText;
	private JTextField verifyPasswordText;
	private JButton newcreateAccount;
	static JLabel error;
	private JPanel south;
	String username = "";
	JButton play;
	JButton cards;
	String currentline;
	java.util.Timer timer = new java.util.Timer();
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(passwordText) || e.getSource().equals(login)) {
			if(!(usernameText.getText().equals(""))){
				StateMachine.sendMessage(new LoginMessage(usernameText.getText(), passwordText.getText()));
			}
		}	
		else if(e.getSource().equals(createAccount)){

			//If this doesn't work, use SwingUtilities.invokeLater to run it in a runnable
			south.removeAll();
			south.add(emailText);
			emailText.addActionListener(this);
			south.add(newUsernameText);
			newUsernameText.addActionListener(this);
			south.add(newPasswordText);
			newPasswordText.addActionListener(this);
			south.add(verifyPasswordText);
			verifyPasswordText.addActionListener(this);
			south.add(newcreateAccount);
			newcreateAccount.addActionListener(this);

		}
		else if(e.getSource().equals(emailText) || e.getSource().equals(newUsernameText) 
				|| e.getSource().equals(newPasswordText) || e.getSource().equals(verifyPasswordText) || e.getSource().equals(newcreateAccount)){
			if(!(emailText.getText().equals("") || newUsernameText.getText().equals("") || newPasswordText.getText().equals("") || verifyPasswordText.getText().equals(""))){
				if(newPasswordText.getText().equals(verifyPasswordText.getText())){
					StateMachine.sendMessage(new AccountCreationMessage(newUsernameText.getText(), newPasswordText.getText(), emailText.getText()));
				}
			}else {

			}
		} 


	}

	@Override
	public void onInitialize(StateMachine stater)
	{
		// TODO Auto-generated method stub
		login = new JButton("Login");
		emailText = new JTextField("Enter Email");
		newUsernameText  = new JTextField("Enter Username");
		newPasswordText = new JTextField("Enter Password");
		verifyPasswordText = new JTextField("Verify Password");
		newcreateAccount = new JButton("Create Account");
		error = new JLabel("");
		play = new JButton("Play");
		cards = new JButton("Cards");
		currentline = "";
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

	@Override
	public void onBegin(StateMachine stater)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(StateMachine stater)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy(StateMachine stater)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void MessageRecieved(Message message)
	{
		if(message instanceof LoginAcceptedMessage) {
			String name = ((LoginAcceptedMessage) message).getUsername();
			String password = ((LoginAcceptedMessage) message).getPassword();
			String email = ((LoginAcceptedMessage) message).getEmail();
			int gold = ((LoginAcceptedMessage) message).getGold();
			int rank = ((LoginAcceptedMessage) message).getRank();
			ArrayList<Integer> cards = ((LoginAcceptedMessage) message).getCards();
			ArrayList<String> friends = ((LoginAcceptedMessage) message).getFriends();
			HashMap<String, int[]> decks = null;
			
			Player player = new Player(email, name, password, cards, decks, rank, friends, gold);
		
		} else if(message instanceof AccountConfirmationMessage) {
			if(((AccountConfirmationMessage) message).isConfirmed()) {
				StateMachine.sendMessage(new LoginMessage(newUsernameText.getText(), newPasswordText.getText()));
			} else {
				error.setText("Account not Confirmed");
			}
		}

	}

}
