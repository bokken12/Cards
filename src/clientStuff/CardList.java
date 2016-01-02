package clientStuff;
import java.awt.Graphics;

import javax.swing.JScrollPane;

import cards.Card;
import cards.Cards;
import player.Player;


public class CardList extends JScrollPane {
	
	public Player player;
	public int[] deck = null;

	public CardList(Player playyer) {
		player = playyer;

		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(deck == null) {
		for(int i = 0; i < player.getCardCollection().size(); i++) {
			
			int a = player.getCardCollection().get(i);
			
			Card b = Cards.getCardFromID(a);
			b.getImageIcon().paintIcon(this, g, 0, 0);
		}
		}
	}
	
	public void setDeck(int[] d) {
		deck = d;
		
		repaint();
	}


	public static Card getCardFromID(int c4) {
		// TODO Auto-generated method stub
		return null;
	}
}
