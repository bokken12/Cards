package cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;

import clientStuff.Content;
import abilities.Ability;
import abilities.AbilityRunnable;
import abilities.AbilityTemplate;
import events.AbilityEvent;
import events.AttackEvent;
import events.CreatureKilledEvent;
import events.CreaturePlayedEvent;
import events.DamageEvent;
import events.EventBus;
import events.GameEvent;
import events.ModifyEvent;
import events.TurnEndedEvent;
import clientStuff.Board;

public class Cards {

	static ArrayList<Card> cards = new ArrayList<Card>();

	public static ArrayList<Card> getCollection() {
		return cards;
	}

	public static ArrayList<Integer> getStarterCards() {

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

	public static void init() {

<<<<<<< HEAD
=======
		Ability a0 = new Ability("", "", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {

			}
		}, false);
>>>>>>> master

		cards.add(new CreatureCard("Dwarven Knight", 4, 3, 4, new ImageIcon("dwarf knight.png"), "Dwarf", 0));
		cards.add(new CreatureCard("Dwarven Footman", 2, 3, 2, new ImageIcon("DwarvenFootman.png"), "Dwarf", 1));   
		Ability a1 = new Ability("", "3: deal 1 damage to a creature", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard().getCard() == cards.get(2)) {
					EventBus.getInstance().callEvent(new DamageEvent(1, AE.target));
				}
			}
<<<<<<< HEAD
		});
		cards.add(new CreatureCard("Dwarven Wizard", 3, 2, 4, new ImageIcon("Weezard.png"), "Dwarf", 2, a1)); 
		cards.add(new CreatureCard("Dwarven ShieldBearer", 2, 5, 4, new ImageIcon("dwarf shield.png"),"Dwarf", 3));
		cards.add(new CreatureCard("Dwarven Champion", 4, 4, 5, new ImageIcon("dwarf champ.png"),"Dwarf", 4));
		cards.add(new CreatureCard("Skeleton", 1, 1, 0, new ImageIcon("Skeleton.png"),"Undead", 5));
=======
		}, true);
		cards.add(new CreatureCard("Dwarven Wizard", 3, 2, 4, new ImageIcon("Weezard.png"), a1, "Dwarf", 2)); 
		cards.add(new CreatureCard("Dwarven ShieldBearer", 2, 5, 4, new ImageIcon("dwarf shield.png"), a0,"Dwarf", 3));
		cards.add(new CreatureCard("Dwarven Champion", 4, 4, 5, new ImageIcon("dwarf champ.png"), a0,"Dwarf", 4));
		cards.add(new CreatureCard("Skeleton", 1, 1, 0, new ImageIcon("Skeleton.png"), a0,"Undead", 5));
>>>>>>> master
		Ability a2 = new Ability("", "At the end of your turn, summon a 1/1 Skeleton", TurnEndedEvent.class, new AbilityRunnable() { 
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				TurnEndedEvent AE = (TurnEndedEvent) event;
				if(AE.getPlayer().equals(Content.you)) {
					EventBus.getInstance().callEvent(new CreaturePlayedEvent((CreatureCard) getCardFromID(5), null));
				}
			}
<<<<<<< HEAD
		});
		cards.add(new CreatureCard("Vraz, the Lich King", 4, 6, 6, new ImageIcon("Vraz.png"),"Undead", 6, a2)); 
=======
		}, false);
		cards.add(new CreatureCard("Vraz, the Lich King", 4, 6, 6, new ImageIcon("Vraz.png"), a2,"Undead", 6)); 
>>>>>>> master
		Ability a3 = new Ability("", "3: Destroy Flame Spirit: Deal 5 damage", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard().getCard() == cards.get(7)) {
					EventBus.getInstance().callEvent(new DamageEvent(5, Content.selectedCard));
				}
			}
<<<<<<< HEAD
		});
		cards.add(new CreatureCard("Flame Spirit", 4, 1, 3, new ImageIcon("Flame Spirit.png"),"Elemental", 7, a3)); 
		cards.add(new CreatureCard("Reborn Footman", 3, 2, 3, new ImageIcon("RebornFoot.png"),"Undead", 8)); 
		AbilityTemplate a4 = new AbilityTemplate("", "2: Give another creature +2/+1", AbilityEvent.class, new AbilityRunnable() {
=======
		}, true);
		cards.add(new CreatureCard("Flame Spirit", 4, 1, 3, new ImageIcon("Flame Spirit.png"), a3,"Elemental", 7)); 
		cards.add(new CreatureCard("Reborn Footman", 3, 2, 3, new ImageIcon("RebornFoot.png"), a3,"Undead", 8)); 
		Ability a4 = new Ability("", "1: Give another creature +2/+1 and destroy this creature", AbilityEvent.class, new AbilityRunnable() {
>>>>>>> master
			@Override
			public void run(GameEvent event, Board board) {
				//super.run(event);
<<<<<<< HEAD
				//AbilityEvent AE = (AbilityEvent) event;
				//if(AE.getCard().getCard() == cards.get(9)) {
				//	getBoard().getBus().callEvent(new ModifyEvent(Content.selectedCard, 2, 1));
				//}
			}
		}, 10);
		cards.add(new CreatureCard("Factory Bot", 2, 1, 2, new ImageIcon("FactoryBot.png"),"Mechanical", 9, a4)); 
=======
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard().getCard() == cards.get(9)) {		//This won't work, selected card is the fac bot
					EventBus.getInstance().callEvent(new ModifyEvent(AE.target, 2, 1));
					EventBus.getInstance().callEvent(new CreatureKilledEvent(AE.getCard(), AE.c, AE.c.myCreatures.contains(AE.c.selectedCard)));
				}
			}
		}, true);
		cards.add(new CreatureCard("Factory Bot", 2, 1, 2, new ImageIcon("FactoryBot.png"), a4,"Mechanical", 9)); 
>>>>>>> master
		Ability a5 = new Ability("", "2: put a 2/1 Factory Bot into play", AbilityEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				//super.run(event);
				AbilityEvent AE = (AbilityEvent) event;
				if(AE.getCard().getCard() == cards.get(10)) {
					EventBus.getInstance().callEvent(new CreaturePlayedEvent((CreatureCard) getCardFromID(9), AE.c));
				}
			} 
<<<<<<< HEAD
		});
		cards.add(new CreatureCard("The Factory", 0, 5, 6, new ImageIcon("Factory.png"),"Mechanical", 10, a5)); 
		CreatureCard crcd1 = new CreatureCard("Charging Stag", 5, 5, 4, new ImageIcon("ChargingStag.png"), a0,"Beast", 10);
=======
		}, false);
		cards.add(new CreatureCard("The Factory", 0, 5, 6, new ImageIcon("Factory.png"), a5,"Mechanical", 10)); 
		CreatureCard crcd1 = new CreatureCard("Charging Stag", 5, 5, 4, new ImageIcon("ChargingStag.png"), a0,"Beast", 11);
>>>>>>> master
		crcd1.setIntoPlay(new InPlayRunnable() {
			@Override
			public void run(InPlayCreature c, CreaturePlayedEvent e) {

			}
		});
		cards.add(crcd1); 

		RequirementRunnable yes = new RequirementRunnable() {

			@Override
			public boolean run(InPlayCreature target) {
				return true;
			}
		};

		RequirementRunnable lessthan4 = new RequirementRunnable() {

			@Override
			public boolean run(InPlayCreature target) {
				return target.power <= 4;
			}
		};

		Ability a6 = new Ability("", "At the end of your turn, restore 1 health to each beast", TurnEndedEvent.class, new AbilityRunnable() {
			@Override
			public void run(GameEvent event) {
				TurnEndedEvent a = (TurnEndedEvent) event;
				//super.run(event);
				if(a.getPlayer().equals(Content.you)) {
					ArrayList<InPlayCreature> c = Content.getCardsOfType("Beast");
					for(int i = 0; i < Content.getCardsOfType("Beast").size(); i++) {
						if(c.get(i).getHealth() < c.get(i).getMaxHealth()) {
							c.get(i).AddHealth(1);
						}
					}
					EventBus.getInstance().callEvent(new CreaturePlayedEvent((CreatureCard) getCardFromID(9), null));
				}
			} 
<<<<<<< HEAD
		});
		cards.add(new CreatureCard("Verdant Spring", 0, 3, 1, new ImageIcon("Factory.jpg"),"Plant", 10, a6)); 
=======
		}, false);
		cards.add(new CreatureCard("Verdant Spring", 0, 3, 1, new ImageIcon("Factory.jpg"), a6,"Plant", 12)); 
>>>>>>> master

		cards.add(new SpellCard("War Axe", 2, "Give a creature +3 attack", true, new ImageIcon("War Axe.png"), 13, new SpellRunnable() {
			@Override
			public void run(Content c) {
				EventBus.getInstance().callEvent(new ModifyEvent(c.selectedCard, 3, 0));
			}
		}, yes));

		cards.add(new SpellCard("Hired Assassin", 3, "Destroy a creature with an attack of 4 or less", true, new ImageIcon("Assassin.jpg"), 14, new SpellRunnable() {
			@Override
			public void run(Content c) {
				EventBus.getInstance().callEvent(new CreatureKilledEvent(c.selectedCard, c, c.myCreatures.contains(c.selectedCard)));
			}
		}, lessthan4));
	}
}
