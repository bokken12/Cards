package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.logging.Handler;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import cards.Card;
import cards.Cards;
import Player.GamePlayer;
import Player.Player;
import Player.SimplePlayerProfile;
import Player.SimplerProfile;
import acm.program.ConsoleProgram;


public class Server extends ConsoleProgram{

	static final List<PrintWriter> writers = Collections.synchronizedList(new ArrayList<PrintWriter>());
	//players in the waiting for game list
	static final List<SimplerProfile> playing = Collections.synchronizedList(new ArrayList<SimplerProfile>());
	//all players, online or not
	static final Map<String, String> users = Collections.synchronizedMap(new HashMap<String, String>());
	//all online players
	static final HashMap<String, Handler> players = new HashMap<String, Handler>();

	static HashMap<String, Player> userdata = new HashMap<String, Player>();
	//static final Map<Integer, Handler> waitingForGames = Collections.synchronizedMap(new HashMap<Integer, Handler>());
	public static int PORT_NUMBER = 5002;

	public static final String HOSTNAME = "127.0.0.1";   
	
	public static void main(String[] args){
	    Server server = new Server();
	    server.start();
	}
	
	public void run() {

		Cards.init();
		ServerSocket listener = null;
		println("Server started. Reading existing accounts from file...");
		try {
			/* Open the file for reading. */
			BufferedReader br = new BufferedReader(new FileReader("PlayerData"));

			/* Print all lines from the file. */
			String username = null;
			String password = null;
			String email = null;
			Integer rank = null;
			Integer gold = null;
			Integer wins = null;
			Integer losses = null;
			ArrayList<Integer> cards = null;
			HashMap<String, int[]> decks = null;
			ArrayList<String> friends = null;

			while (true) {
				String line = br.readLine();
				if (line == null) break;
				System.out.println("Read line " + line);
				if(!(line.startsWith("//"))) {

					if(line.startsWith("--")) {
						username = null;
						password = null;
						rank = null;
						gold = null;
						cards = null;
						decks = null;
						friends = null;
					}
					
					
					if(line.startsWith("username")) {
						username = line.substring(9);
					}
					
					if(line.startsWith("password")) {
						password = line.substring(9);
					}
					
					if(line.startsWith("rank")) {
						rank = 	Integer.parseInt(line.substring(5));
					}

					if(line.startsWith("gold")) {
						gold = Integer.parseInt(line.substring(5));
					}
					
					if(line.startsWith("email")) {
						email = line.substring(6);
					}

					if(line.startsWith("cards")) {
						cards = getArrayList(line.substring(5));
					}

					if(line.startsWith("decks")) {
						decks = getDecksFromString(line.substring(5));
					}

					if(line.startsWith("friends")) {
						friends = new ArrayList<String>();
					}
					
					if(line.startsWith("wins")) {
						wins = 	Integer.parseInt(line.substring(5));
					}
					
					if(line.startsWith("losses")) {
						losses = Integer.parseInt(line.substring(7));
					}

					if(username != null && password != null && rank != null && gold != null && cards != null && decks != null && friends != null && email != null && wins != null && losses != null) {
						Player p = new Player(email, username, password, cards, decks, rank, friends, gold, wins, losses);
						println("Recieved a player with name " + p.getUsername());
						users.put(username, password);
						userdata.put(username, p);
					}
					//					String[] a = line.split("-");
					//					String name = a[1];
					//					String password = a[2];
					//					String email = a[7];
					//					String cards = a[4];
					//					int[] cardzs = getArray(cards);
					//					ArrayList<Integer> caards = new ArrayList<Integer>();
					//					for(int i = 0; i < cardzs.length; i++) {
					//						caards.add(cardzs[i]);
					//					}
					//					int gold = Integer.parseInt(a[5]);
					//					int rank = Integer.parseInt(a[0]);
					//					HashMap<String, int[]> decks = getDecksFromString(a[5]);
					//					String[] friends = a[6].substring(1, a[6].length() - 1).split(",");
					//					ArrayList<String> frieends = new ArrayList<String>();
					//					for(int i = 0; i < friends.length; i++) {
					//						frieends.add(friends[i]);
					//					}
					//
					//					println("Userdata "  + name + ", " + password);
					//					users.put(name, password);
					//
					//					userdata.put(name, new Player(email, name, password, caards, decks, rank, frieends, gold));

				}

			}

			/* To be nice, close the file. */
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		println("Recieved " + users.size() + " existing players. Server waiting for connections");

		try {
			listener = new ServerSocket(PORT_NUMBER, 100, InetAddress.getByName(HOSTNAME));
	
			while (true) {
				new Handler(listener.accept()).start();
			}
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
		}

	} 

	public static int[] getArray(String ar) {

		String[] digitwords = ar.substring(2, ar.length() - 1).split(", ");
		int[] result = new int[digitwords.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(digitwords[i]);
		}
		return result;
	}


	public static ArrayList<Integer> getArrayList(String ar) {

		String[] digitwords = ar.substring(2, ar.length() - 1).split(", ");
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < digitwords.length; i++) {
			result.add(Integer.parseInt(digitwords[i]));
		}
		return result;
	}

	public static ArrayList<String> getFriends(String ar) {

		String[] digitwords = ar.substring(2, ar.length() - 1).split(",");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < digitwords.length; i++) {
			result.add(digitwords[i]);
		}
		return result;
	}



	public static HashMap<String, int[]> getDecksFromString(String string){
		HashMap<String, int[]> ret = new HashMap<String, int[]>();

		StringTokenizer t = new StringTokenizer(string, "|");

		while(t.hasMoreTokens()) {
			String a = t.nextToken();
			int[] arr = null;
			arr = getArray(a.substring(a.indexOf("[") - 1 ));


			ret.put(a.substring(0, a.indexOf("[")), arr);
		}

		return ret;

	}

	class Handler extends Thread {
		public String name;
		private Socket socket;
		private Player pllayer;
		BufferedReader in;
		PrintWriter out;
		GameHandler gh;
		int me = 1;
		/**
		 * Constructs a handler thread, squirreling away the socket.
		 * All the interesting work is done in the run method.
		 */
		public Handler(Socket socket) {
			this.socket = socket;
			println(socket.getInetAddress().toString());
		}


		public void send(String s) {
			out.println(s);
			out.flush();
		}

		public void run() {
			println("Got a connection");
			// Create character streams for the socket.
			
			
			
			while(true){
				try {
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					break;
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			while(true){
				try {
					out = new PrintWriter(socket.getOutputStream(), true);
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			writers.add(out);

			while (true) {
				String line = "";
				try {
					line = in.readLine();
				} catch (IOException e) {

					e.printStackTrace();
				}
				println(line);
				if(line == null) {
					println(name + " disconnected.");
					if(players.containsKey(name)) {
						players.remove(name);
						println("removed " + name + " from players.");
					}
					if(playing.size() > 0) {
						if(playing.get(0).getName().equals(name)) {
							playing.remove(0);
						}
					}
					if(gh != null) {
						gh.handleMessage(line + me);
					}

					break;
				}
				else if(line.startsWith("--login")){
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
					Player player = new Player(email, username, password, Cards.getStarterCards(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0, 0, 0);
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
					dacks.put("Starter", a);
					player.setDecks(dacks);
					if(!(users.containsKey(username))) {
						userdata.put(username, player);
						String decks = "";
						int count = 0;
						for(Entry<String, int[]> entry : dacks.entrySet()){ 
							 decks += entry.getKey();
							 decks += Arrays.toString(entry.getValue()); 
							 if(!(count < dacks.keySet().size())) {
								 decks += "|";
							 }
							 count++;
						}
						
						String str = "\nusername " + username + "\npassword " + password + "\nemail " + email + "\nrank " + 0 + "\nfriends []\ncards " + Cards.getStarterCards() + "\ndecks " + decks + "\ngold " + 0 + "\n--";
						println(str);
						File file = new File("PlayerData");
						FileWriter fw;
						try {
							fw = new FileWriter(file.getAbsoluteFile(), true);
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(str);
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						//players.put(username, this);
						println("Username: " + username + " Password: " + password);
						users.put(username, password);
						out.println("AccountConfirmed ");
						println("Account got some confirmation");
						out.flush();
					} else {
						println("Username " + username + " already taken, account not confirmed");
						out.println("--nameTaken");
					}
					//doLogin("--login " + username + " " + password, out);
				} else if(line.startsWith("--Playing")) {
					println(playing.toString());
					String[] items = line.split(" ");
					int rank = Integer.parseInt(items[1]);
					String username = items[2]; 
					if(playing.size() == 0) {
						SimplerProfile prof = new SimplerProfile(username, rank);
						playing.add(prof);
						println("Someone Joined Playing");
						out.println("--wait");
						out.flush();
					} else {
						SimplePlayerProfile profile1 = new SimplePlayerProfile(username, rank);
						SimplePlayerProfile profile2 = new SimplePlayerProfile(playing.get(0).getName(), playing.get(0).getRank());

						Handler h = players.get(playing.get(0).getName());
						println("Matching new " + profile1 + " to existing " + profile2);
						h.send("--match " + profile1.toString() + " 1");
						send("--match " + profile2.toString() + " 2");
						me = 2;
						gh = new GameHandler(this, h);
						h.setGH(gh);
						playing.remove(0);
						//playing.remove(new SimplerProfile(pllayer.getUsername(), pllayer.getRank()));
					}
				} else if(line.startsWith("--turn")) {
					println("-------------");
					gh.handleMessage(line);
				} else if(line.startsWith("--myBoard")) {
					println("Handling Cards");
					gh.handleMessage(line + me);
				} else if(line.startsWith("--attack")) {
					println("server handling attack");
					gh.handleMessage(line + me);
				} else if(line.startsWith("--block")) {
					gh.handleMessage(line + me);
				} else if(line.startsWith("--wi")) {
					gh.handleMessage(line + me);
				} else {
					gh.handleMessage(line + me);
				}


			}

		}
		public void setGH(GameHandler g) {
			gh = g;
			me = 1;
		}
	}

	public Player doLogin(String params, PrintWriter output, Handler h){
		//println("--loginaccepted " + (new Player("email", "username", "password", new ArrayList<Integer>(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0)).toString());
		//output.println("--loginaccepted " + (new Player("email", "username", "password", new ArrayList<Integer>(), new HashMap<String, int[]>(), 0, new ArrayList<String>(), 0)).toString());

		StringTokenizer a = new StringTokenizer(params, " ");
		a.nextToken();
		String b = a.nextToken();
		String c = a.nextToken();
		if((players.containsKey(b))) {
			println("Denying login: Account already logged in");
			return null;
		}
		if(users.containsKey(b) && users.get(b).equals(c)) {
			println("Username and password are in data");
			println("Sending login acceptance to player");

			output.println("--loginaccepted " + userdata.get(b).toString());
		} else {
			println("Incorrect username or password, login not accepted");
		}
		output.flush();
		players.put(b, h);
		h.name = b;

		return null;
	}
	public static Player getPlayer(String s){
		return null;
	}


}
