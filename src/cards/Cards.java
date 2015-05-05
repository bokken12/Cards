package cards;

import java.util.ArrayList;

public class Cards {

	ArrayList<Card> cards = new ArrayList<Card>();
	
	public Cards() {
		
		
		cards.add(new CreatureCard("DwarvenKnight", 4, 3, 4, null));
		cards.add(new CreatureCard("DwarvenFootman", 2, 3, 2, null)); // Will not be null, is null now for ease
	}
	
	public ArrayList<Card> getCollection() {
		return cards;
	}
	
	public ArrayList<Card> getStarterCards() {
		return cards; 
		//will be different later, for now we only have 2 cards in list :O
	}
}
