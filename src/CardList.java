import java.awt.Graphics;

import javax.swing.JScrollPane;

import cards.Card;
import Player.Player;


public class CardList extends JScrollPane {
	
	public Player player;
	public Card[] deck = null;

	public CardList(Player playyer) {
		player = playyer;

		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(deck == null) {
		for(int i = 0; i < player.getCardCollection().size(); i++) {
			player.getCardCollection().get(i).getImageIcon().paintIcon(this, g, 0, 0);
		}
		}
	}
	
	public void setDeck(Card[] d) {
		deck = d;
		
		repaint();
	}
}
