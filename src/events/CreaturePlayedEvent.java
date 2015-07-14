package events;

import cards.CreatureCard;

public class CreaturePlayedEvent extends CardPlayedEvent {

	CreatureCard creature;
	int lane;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public CreaturePlayedEvent(CreatureCard creature) {
		this.creature = creature;
	}
	
	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public CreatureCard getCreature() {
		return creature;
	}
}
