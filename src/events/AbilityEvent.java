package events;

import cards.CreatureCard;

public class AbilityEvent extends GameEvent {

	
	CreatureCard card;
	
	@Override
	public void fireEvent() {
		
		
	}
	
	public AbilityEvent(CreatureCard c) {
		card = c;
	}

	
	public CreatureCard getCard() {
		return card;
	}
}
