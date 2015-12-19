package events;

import clientStuff.GameState;
import cards.CreatureCard;
import cards.InPlayCreature;

public class AbilityEvent extends GameEvent {

	
	InPlayCreature card;
	
	@Override
	public void fireEvent() {
		
		
	}
	
	public AbilityEvent(GameState gs, InPlayCreature c) {
	    super(gs);
		card = c;
	}

	
	public InPlayCreature getCard() {
		return card;
	}
}
