package events;

import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;

public class CreaturePlayedEvent extends CardPlayedEvent {

	CreatureCard creature;
	int lane;
	
	@Override
	public void fireEvent() {
		InPlayCreature c = new InPlayCreature(creature, Content.lanes.get(lane));
		Content.lanes.get(lane).content().addCreature(c);
		Content.lanes.get(lane).addCard(c);
		Content.lanes.get(lane).content().arrivalLanes.remove(Content.lanes.get(lane).content().arrivalCreatures.indexOf(creature));   
		Content.lanes.get(lane).content().arrivalCreatures.remove(creature);
			}
	
	public CreaturePlayedEvent(CreatureCard creature, int lane) {
		this.creature = creature;
		this.lane = lane;
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
