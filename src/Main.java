
import java.io.IOException;
import java.net.Socket;

import acm.*;
import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	private BufferedReader input;
	private PrintWriter output;
	
	
	public void run() {
		Socket s = setupClient();
		
		try {
			input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			output = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	
	private Socket setupClient() {

		while (true) {
			try {
				Socket s = new Socket("Our MegaServer", 554/*random number*/);

				return s;
			} catch (IOException e) {
				
			}
		}
	}
}
