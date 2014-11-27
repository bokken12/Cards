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
	static int PORT_NUMBER = 5002;
	public static void main(String[] args) {

		ServerSocket listener = null;
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
			try {
				System.out.println("Got a connection");
				// Create character streams for the socket.
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				writers.add(out);
				// Request a name from this client.  Keep requesting until
				// a name is submitted that is not already used.  Note that 
				// checking for the existence of a name and adding the name
				// must be done while locking the set of names.
				while (true) {
					String line = in.readLine();
					if(line == null) break;
					else if(line.substring(0, 7).equals("--login")){
						out.println("--loginaccepted" + getPlayer().toString());
					}
					for(PrintWriter pw: writers){
						pw.println(">>> " + line);
					}
				}
			} catch(Exception e) {

			}
		}
	}
	public static Player getPlayer(){
		return null;
	}
}
