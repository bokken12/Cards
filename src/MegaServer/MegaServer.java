package MegaServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Handler;

public class MegaServer {

	public static void main(String[] args) {
		
		ServerSocket listener = null;
		try {
		listener = new ServerSocket(554);
        
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
	 }
}
