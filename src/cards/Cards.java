package cards;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import abilities.Ability;
import abilities.AbilityRunnable;
import events.AbilityEvent;
import events.AttackEvent;
import events.DamageEvent;
import events.EventBus;
import events.GameEvent;

public class Cards {

	static ArrayList<Card> cards = new ArrayList<Card>();

	public ArrayList<Card> getCollection() {
		return cards;
	}

	public ArrayList<Integer> getStarterCards() {

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(0);
		a.add(1);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);

		return a; 
	}

	public static Card getCardFromID(int id) {
		return cards.get(id);

	}

	public static void Init() {

		cards.add(new CreatureCard("Dwarven Knight", 4, 3, 4, new ImageIcon("dwarf knight.tiff"), null, 0));
		cards.add(new CreatureCard("Dwarven Footman", 2, 3, 2, new ImageIcon("DwarvenFootman.png"), null, 1));   
		Ability a = new Ability("dweeble", "What it says", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(2)) {
					EventBus.callEvent(new DamageEvent());
				}
			}
		});
		cards.add(new CreatureCard("Dwarven Wizard", 3, 2, 4, new ImageIcon("dwarf wizard.tiff"), a, 2)); 
		cards.add(new CreatureCard("Dwarven ShieldBearer", 2, 5, 4, new ImageIcon("dwarf shield.png"), null, 3));
		cards.add(new CreatureCard("Dwarven Champion", 4, 3, 4, new ImageIcon("dwarf champ.png"), null, 4)); 
	}
	
	public Cards() {
		Init();
		
	}     
}
