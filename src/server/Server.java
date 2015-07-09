package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Handler;

import cards.Card;
import cards.Cards;
import Player.GamePlayer;
import Player.Player;
import Player.SimplePlayerProfile;
import Player.SimplerProfile;


public class Server {

	static final List<PrintWriter> writers = Collections.synchronizedList(new ArrayList<PrintWriter>());
	static final List<SimplerProfile> playing = Collections.synchronizedList(new ArrayList<SimplerProfile>());

	static final Map<String, String> users = Collections.synchronizedMap(new HashMap<String, String>());
	static final HashMap<String, Handler> players = new HashMap<String, Handler>();
	//	static final List<Player> userdata = Collections.synchronizedList(new ArrayList<Player>());
	static HashMap<String, Player> userdata = new HashMap<String, Player>();
	static final Map<Integer, Handler> waitingForGames = Collections.synchronizedMap(new HashMap<Integer, Handler>());
	public static int PORT_NUMBER = 5002;
	static Cards c = new Cards();

	public static final String HOSTNAME = /*"10.0.1.13"*/ "127.0.0.1";

	public static void main(String[] args) {

		Cards.Init();
		ServerSocket listener = null;
		//TODO Load player data from file
		try {
			listener = new ServerSocket(PORT_NUMBER, 100, InetAddress.getByName(HOSTNAME));
			System.out.println("Waiting for a connection.");

			while (true) {
				new Handler(listener.accept()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} 
	static class Handler extends Thread {
		private String name;
		private Socket socket;
		private Player pllayer;
		BufferedReader in;
		PrintWriter out;
		GameHandler gh;
		/**
		 * Constructs a handler thread, squirreling away the socket.
		 * All the interesting work is done in the run method.
		 */
		public Handler(Socket socket) {
			this.socket = socket;
			System.out.println(socket.getInetAddress().toString());
		}


		public void send(String s) {
			out.println(s);
			out.flush();
		}

		public void run() {
			System.out.println("Got a connection");
			// Create character streams for the socket.
			while(true){
				try {
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while(true){
				try {
					out = new PrintWriter(socket.getOutputStream(), true);
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			writers.add(out);

			while (true) {
				String line = "";
				try {
					line = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(line);
				if(line == null) {
					if(players.containsKey(name)) {
						players.remove(name);
					}
					if(playing.size() > 0) {
						if(playing.get(0).getName().equals(name)) {
							playing.remove(0);
						}
					}

					break;
				}
				else if(line.startsWith("--login")){
					System.out.println(line);
					doLogin(line, out, this);


				} else if(line.startsWith("--accountCreation")){
					boolean atemail = false;
					String email = "";
					boolean atusername = false;
					String username = "";
					boolean atpassword = false;
					String password = "";
					for(int i = 0; i < line.length(); i++){
						if(atpassword){
							password += line.charAt(i);
						} else if(atusername){
							if(line.charAt(i) == ' '){
								atpassword = true;
							} else {
								username += line.charAt(i);
							}
						} else if(atemail){
							if(line.charAt(i) == ' '){
								atusername = true;
							} else {
								email += line.charAt(i);
							}
						} else if(line.charAt(i) == ' '){
							atemail = true;
						}
					}
					Player player = new Player(email, username, password, starterCards(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0);
					pllayer = player;
					name = username;
					HashMap<String, int[]> dacks = new HashMap<String, int[]>();
					int[] a = new int[7];
					a[0] = 0;
					a[1] = 0;
					a[2] = 1;
					a[3] = 1;
					a[4] = 2;
					a[5] = 3;
					a[6] = 4;
					player.setDecks(dacks);
					if(!(users.containsKey(username))) {
						userdata.put(username, player);
						players.put(username, this);
						System.out.println("Username: " + username + " Password: " + password);
						users.put(username, password);
						out.println("AccountConfirmed ");
						System.out.println("Account got some confirmation");
						out.flush();
					} else {
						System.out.println("Username " + username + " already taken, account not confirmed");
						out.println("--nameTaken");
					}
					//doLogin("--login " + username + " " + password, out);
				} else if(line.startsWith("--Playing")) {
					String[] items = line.split(" ");
					int rank = Integer.parseInt(items[1]);
					String username = items[2]; 
					if(playing.size() == 0) {
						SimplerProfile prof = new SimplerProfile(username, rank);
						playing.add(prof);
						System.out.println("Someone Joined Playing");
						out.println("--wait");
						out.flush();
					} else {
						SimplePlayerProfile profile1 = new SimplePlayerProfile(username, rank);
						SimplePlayerProfile profile2 = new SimplePlayerProfile(playing.get(0).getName(), playing.get(0).getRank());

						Handler h = players.get(playing.get(0).getName());
						System.out.println("Matching new " + profile1 + " to existing " + profile2);
						h.send("--match " + profile1.toString() + " 1");
						send("--match " + profile2.toString() + " 2");
						gh = new GameHandler(this, h);
						h.setGH(this);
						playing.remove(new SimplerProfile(playing.get(0).getName(), playing.get(0).getRank()));
					}
				} else if(line.startsWith("--turn")) {
					System.out.println("-------------");
					gh.handleMessage(line);
				}


			}

		}
		public void setGH(Handler h) {
			gh = new GameHandler(h, this);

		}
	}

	public static Player doLogin(String params, PrintWriter output, Handler h){
		//System.out.println("--loginaccepted " + (new Player("email", "username", "password", new ArrayList<Integer>(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0)).toString());
		//output.println("--loginaccepted " + (new Player("email", "username", "password", new ArrayList<Integer>(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0)).toString());

		StringTokenizer a = new StringTokenizer(params, " ");
		a.nextToken();
		String b = a.nextToken();
		String c = a.nextToken();
		System.out.println("Username is " + b + " and is it in data? " + users.containsKey(b));
		if(users.containsKey(b)) System.out.println(" is Pasword in data? " + users.get(b).equals(c));
		System.out.println("Sending Login Acceptance to player");
		if(users.containsKey(b) && users.get(b).equals(c)) {
			System.out.println("Actually Sending Login Acceptance to player");

			output.println("--loginaccepted " + userdata.get(b).toString());
		}
		output.flush();
		System.out.println("got a login");
		players.put(b, h);
		return null;
	}
	public static Player getPlayer(String s){
		return null;
	}

	public static ArrayList<Integer> starterCards() {
		return c.getStarterCards();
	}

}
