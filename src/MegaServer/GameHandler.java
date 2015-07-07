package MegaServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;

import events.EventBus;
import MegaServer.MegaServer.Handler;
import Player.GamePlayer;

public class GameHandler extends Thread{
	Handler player1;
	Handler player2;
	BufferedReader in1;
	BufferedReader in2;
	PrintWriter out1;
	PrintWriter out2;
	GamePlayer playerone;
	GamePlayer playertwo;
	int turn = 2;
	//EventBus bus;
	public GameHandler(Handler firstPlayer, Handler secondPlayer){
		player1 = firstPlayer;
		player2 = secondPlayer;
		in1 = firstPlayer.in;
		out1 = firstPlayer.out;
		in2 = secondPlayer.in;
		out2 = secondPlayer.out;
							   
		//bus = new EventBus();
	}

	//	public EventBus getBus() {
	//		return bus;
	//	}

	public void handleMessage(String m) {
		if(m.equals("--turn")) {
			if(turn == 2) {
				turn = 1;
				out1.println("--turn");
			}
			else {
				turn = 2;
				out2.println("--turn");
			}
		}
	}
}