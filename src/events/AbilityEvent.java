package events;

import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;

public class AbilityEvent extends GameEvent {

	
	InPlayCreature card;
	public Content c;
	public InPlayCreature target;
	
	@Override
	public void fireEvent() {
		
		
	}
	
	public AbilityEvent(InPlayCreature creature, InPlayCreature target, Content c) {
		card = creature;
		this.c = c;
		this.target = target;
	}

	
	public InPlayCreature getCard() {
		return card;
	}
}
