package events;

import cards.CreatureCard;
import cards.InPlayCreature;

public class AbilityEvent extends GameEvent {

	
	InPlayCreature card;
	
	@Override
	public void fireEvent() {
		
		
	}
	
	public AbilityEvent(InPlayCreature c) {
		card = c;
	}

	
	public InPlayCreature getCard() {
		return card;
	}
}
