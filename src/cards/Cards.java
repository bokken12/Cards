package cards;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import clientStuff.Content;
import abilities.Ability;
import abilities.AbilityRunnable;
import events.AbilityEvent;
import events.AttackEvent;
import events.CreaturePlayedEvent;
import events.DamageEvent;
import events.EventBus;
import events.GameEvent;
import events.ModifyEvent;
import events.TurnEndedEvent;

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

		cards.add(new CreatureCard("Dwarven Knight", 4, 3, 4, new ImageIcon("dwarf knight.tiff"), null, "Dwarf", 0));
		cards.add(new CreatureCard("Dwarven Footman", 2, 3, 2, new ImageIcon("DwarvenFootman.png"), null, "Dwarf", 1));   
		Ability a1 = new Ability("", "3: deal 1 damage to a creature", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(2)) {
					EventBus.callEvent(new DamageEvent(1, Content.selectedCard));
				}
			}
		});
		cards.add(new CreatureCard("Dwarven Wizard", 3, 2, 4, new ImageIcon("dwarf wizard.tiff"), a1, "Dwarf", 2)); 
		cards.add(new CreatureCard("Dwarven ShieldBearer", 2, 5, 4, new ImageIcon("dwarf shield.png"), null,"Dwarf", 3));
		cards.add(new CreatureCard("Dwarven Champion", 4, 3, 4, new ImageIcon("dwarf champ.png"), null,"Dwarf", 4));
		cards.add(new CreatureCard("Skeleton", 1, 1, 0, new ImageIcon("Skeleton.png"), null,"Undead", 5));
		Ability a2 = new Ability("", "At the end of your turn, summon a 1/1 Skeleton", TurnEndedEvent.class, new AbilityRunnable() { 
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				TurnEndedEvent AE = (TurnEndedEvent) event;
				if(AE.getPlayer().equals(Content.you)) {
					EventBus.callEvent(new CreaturePlayedEvent((CreatureCard) getCardFromID(5)));
				}
			}
		});
		cards.add(new CreatureCard("Vraz, the Lich King", 4, 6, 6, new ImageIcon("Vraz.jpg"), a2,"Undead", 6)); 
		Ability a3 = new Ability("", "3: Destroy Flame Spirit: Deal 5 damage", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(7)) {
					EventBus.callEvent(new DamageEvent(5, Content.selectedCard));
				}
			}
		});
		cards.add(new CreatureCard("Flame Spirit", 4, 1, 3, new ImageIcon("Vraz.jpg"), a3,"Elemental", 7)); 
		cards.add(new CreatureCard("Reborn Footman", 3, 2, 3, new ImageIcon("Vraz.jpg"), a3,"Undead", 8)); 
		Ability a4 = new Ability("", "2: Give another creature +2/+1", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(9)) {
					EventBus.callEvent(new ModifyEvent(Content.selectedCard, 2, 1));
				}
			}
		});
		cards.add(new CreatureCard("Factory Bot", 2, 1, 2, new ImageIcon("FactoryBot.png"), a4,"Mechanical", 9)); 
		Ability a5 = new Ability("", "2: Give another creature +2/+1", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard() == cards.get(10)) {
					EventBus.callEvent(new CreaturePlayedEvent((CreatureCard) getCardFromID(9)));
				}
			}
		});
		cards.add(new CreatureCard("The Factory", 0, 5, 6, new ImageIcon("Factory.jpg"), a5,"Mechanical", 10)); 
	}
	
	public Cards() {
		Init();
		
	}     
}
