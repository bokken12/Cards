package server;

import java.io.BufferedReader;
import java.io.PrintWriter;

import server.Server.Handler;
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
	int p1;
	int p2;
	
	
	int turn = 2;
	//EventBus bus;
	public GameHandler(Handler firstPlayer, Handler secondPlayer){
		player1 = firstPlayer;
		player2 = secondPlayer;
		in1 = firstPlayer.in;
		out1 = firstPlayer.out;
		in2 = secondPlayer.in;
		out2 = secondPlayer.out;
		p1 = player1.me;
		p2 = player2.me;
							   
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
		} else if(m.startsWith("--myBoard")) {
			System.out.println("Handling playing creatures");
			
			if(Integer.parseInt(m.substring(m.length() - 1)) == p1) {
				player2.send(m.substring(0, m.length() - 1));
			} else {
				player1.send(m.substring(0, m.length() - 1));
			}
		} else if(m.startsWith("--attack")) {
			System.out.println("gh handling attack");
			if(Integer.parseInt(m.substring(m.length() - 1)) == p1) {
				player2.send(m.substring(0, m.length() - 1));
			} else {
				player1.send(m.substring(0, m.length() - 1));
			}
		} else if(m.startsWith("--block")) {
			if(Integer.parseInt(m.substring(m.length() - 1)) == p1) {
				player2.send(m.substring(0, m.length() - 1));
			} else {
				player1.send(m.substring(0, m.length() - 1));
			}
		}
	}
}