package cards;

import java.util.ArrayList;
import java.util.HashMap;

import abilities.Ability;
import events.AttackEvent;
import events.GameEvent;

public class Cards {

	static ArrayList<Card> cards = new ArrayList<Card>();

	public ArrayList<Card> getCollection() {
		return cards;
	}

	public ArrayList<Integer> getStarterCards() {

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(1);

		return a; 
	}

	public static Card getCardFromID(int id) {
		return cards.get(id);

	}

	public static void Init() {

		cards.add(new CreatureCard("DwarvenKnight", 4, 3, 4, null, null, 0));
		Ability a = new Ability();
		cards.add(new CreatureCard("DwarvenFootman", 2, 3, 2, null, null, 1));   // Will not be null, is null now for ease    
	}
}
