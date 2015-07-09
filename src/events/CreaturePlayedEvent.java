package events;

import cards.CreatureCard;

public class CreaturePlayedEvent extends CardPlayedEvent {

	CreatureCard creature;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public CreaturePlayedEvent(CreatureCard creature) {
		this.creature = creature;
	}
	
	public CreatureCard getCreature() {
		return creature;
	}
}
