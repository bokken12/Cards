package player;

import java.util.ArrayList;

import cards.Card;

public class GamePlayer {
	ArrayList<Card> hand = new ArrayList<Card>();
	int health = 20;
	
	public GamePlayer(int p) {
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
