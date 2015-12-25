package server;

import messaging.Message;
import player.Player;
import player.SimplerProfile;
import server.Server.Handler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.AccountConfirmationMessage;
import messaging.AccountCreationMessage;
import messaging.LoginAcceptedMessage;
import messaging.LoginMessage;

public class LoginState extends ListenerState {

	static final List<PrintWriter> writers = Collections.synchronizedList(new ArrayList<PrintWriter>());
	//players in the waiting for game list
	static final List<SimplerProfile> playing = Collections.synchronizedList(new ArrayList<SimplerProfile>());
	//all players, online or not
	static final Map<String, String> users = Collections.synchronizedMap(new HashMap<String, String>());
	//all online players
	static final HashMap<String, ClientListener> players = new HashMap<String, ClientListener>();

	static HashMap<String, Player> userdata = new HashMap<String, Player>();


	@Override
	public void MessageRecieved(Message message) {
		// TODO Auto-generated method stub
		if(message instanceof LoginMessage) {
			if(players.get(((LoginMessage) message).getUsername()) == null && users.get(((LoginMessage) message).getUsername()) != null) {
				send(new LoginAcceptedMessage(userdata.get(((LoginMessage) message).getUsername())));
				players.put(((LoginMessage) message).getUsername(), listener/*Don't have no handler no more*/);
			} else if(users.get(((LoginMessage) message).getUsername()) == null) {
				//Send LoginDeniedMessage -- no account
			} 

		} else if(message instanceof AccountCreationMessage) {
			if(users.get(((AccountCreationMessage) message).getUsername()) == null) {
				send(new AccountConfirmationMessage(true));
				String username = ((AccountCreationMessage) message).getUsername();
				users.put(username, ((AccountCreationMessage) message).getPassword());
				userdata.put(username, new Player(((AccountCreationMessage) message).getEmail(), username, ((AccountCreationMessage) message).getPassword(), getStarterCards(), getStarterDeck(), 0, new ArrayList<String>(), 0));

			} else {
				send(new AccountConfirmationMessage(false));
			}
		}
	}

	@Override
	public void onBegin(ClientListener stater) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(ClientListener stater) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy(ClientListener stater) {
		// TODO Auto-generated method stub

	}

	public HashMap<String, ArrayList<Integer>> getStarterDeck() {
		HashMap<String, ArrayList<Integer>> x = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> e = new ArrayList<Integer>();
		for(int f = 0; f < 11; f++) {
			for(int i = 0; i < 2; i++) {
				e.add(f);
			}
		}
		x.put("Starter", e);
		return x;
	}

	public ArrayList<Integer> getStarterCards() {
		
		ArrayList<Integer> e = new ArrayList<Integer>();

		for(int f = 0; f < 11; f++) {
			for(int i = 0; i < 2; i++) {
				e.add(f);
			}
		}
		return e;
	}
}
