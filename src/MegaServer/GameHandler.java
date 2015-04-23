package MegaServer;

import MegaServer.MegaServer.Handler;
import Player.GamePlayer;

public class GameHandler extends Thread{
	Handler player1;
	Handler player2;
	GamePlayer playerone;
	GamePlayer playertwo;
	public GameHandler(Handler firstPlayer, Handler secondPlayer){
		player1 = firstPlayer;
		player2 = secondPlayer;
	}
}
