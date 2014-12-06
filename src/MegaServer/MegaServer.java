package MegaServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import Player.Player;


public class MegaServer {
	
	static final List<PrintWriter> writers = Collections.synchronizedList(new ArrayList<PrintWriter>());
	static final Map<String, String> users = Collections.synchronizedMap(new HashMap<String, String>());
	static final List<Handler> players = Collections.synchronizedList(new ArrayList<Handler>());
	static final List<Player> playerdata = Collections.synchronizedList(new ArrayList<Player>());
	static int PORT_NUMBER = 5002;
	public static void main(String[] args) {

		ServerSocket listener = null;
		//TODO Load player data from file
		try {
			listener = new ServerSocket(PORT_NUMBER);
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
	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		/**
		 * Constructs a handler thread, squirreling away the socket.
		 * All the interesting work is done in the run method.
		 */
		public Handler(Socket socket) {
			this.socket = socket;
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
			// Request a name from this client.  Keep requesting until
			// a name is submitted that is not already used.  Note that 
			// checking for the existence of a name and adding the name
			// must be done while locking the set of names.
			while (true) {
				String line = "";
				try {
					line = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(line);
				if(line == null) break;
				else if(line.startsWith("--login")){
					doLogin(line, out);

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
					playerdata.add(new Player(email, username, password));
					users.put(username, password);
					doLogin("--login " + username + " " + password, out);
				}
			}
		}
	}
	public static Player doLogin(String params, PrintWriter output){
		output.println("--loginaccepted");
		output.flush();
		System.out.println("got a login");
		return null;
	}
	public static Player getPlayer(){
		return null;
	}
}
