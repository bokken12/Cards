package events;

import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;

public class CreaturePlayedEvent extends CardPlayedEvent {

	CreatureCard creature;
	Content content;
	
	@Override
	public void fireEvent() {
		InPlayCreature c = new InPlayCreature(creature);
		content.addCreature(c); 
		content.arrivalCreatures.remove(creature);
	}
	
	public CreaturePlayedEvent(CreatureCard creature) {
		this.creature = creature;
	}

	public CreatureCard getCreature() {
		return creature;
	}
}
