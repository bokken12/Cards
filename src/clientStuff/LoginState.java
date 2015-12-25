package clientStuff;

import java.awt.Dimension;
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

		    StateMachine.setState(new AccountCreationState());
		}


	}

	@Override
	public void onInitialize(StateMachine stater)
	{
		// TODO Auto-generated method stub
		login = new JButton("Login");
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
		add(south);
	}

	@Override
	public void onBegin(StateMachine stater)
	{
		stater.setSize(520, 70);
		//stater.setPreferredSize(new Dimension(500, 300));
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
			
			if(((LoginAcceptedMessage) message).getCards() != null) {
				String name = ((LoginAcceptedMessage) message).getUsername();
				String password = ((LoginAcceptedMessage) message).getPassword();
				String email = ((LoginAcceptedMessage) message).getEmail();
				int gold = ((LoginAcceptedMessage) message).getGold();
				int rank = ((LoginAcceptedMessage) message).getRank();
				ArrayList<Integer> cards = ((LoginAcceptedMessage) message).getCards();
				ArrayList<String> friends = ((LoginAcceptedMessage) message).getFriends();
				HashMap<String, ArrayList<Integer>> decks = ((LoginAcceptedMessage) message).getDecks();
				Player player = new Player(email, name, password, cards, decks, rank, friends, gold);
				StateMachine.setState(new MenuState(player));
			} else {
				Player player = ((LoginAcceptedMessage) message).getPlayer();
				StateMachine.setState(new MenuState(player));
			}
		}
	}
}