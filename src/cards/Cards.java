package cards;

import java.util.ArrayList;
import java.util.HashMap;

import abilities.Ability;
import abilities.AbilityRunnable;
import events.AbilityEvent;
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
		cards.add(new CreatureCard("DwarvenFootman", 2, 3, 2, null, null, 1));   // Will not be null, is null now for ease   
		Ability a = new Ability("dweeble", "What it says", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(2)) {
					
				}
			}
		});
		cards.add(new CreatureCard("DwarvenWizard", 3, 2, 4, null, null, 1)); 
	}
}
