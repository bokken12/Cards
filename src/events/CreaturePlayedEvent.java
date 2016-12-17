package events;

import uselessSubclasses.Lane;
import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;
import clientStuff.Board;

<<<<<<< HEAD
public class CreaturePlayedEvent extends CardPlayedEvent
{

    CreatureCard creature;
    int lane;

    @Override
    public void fireEvent()
    {
        Lane l = gs.getLane(lane);
        InPlayCreature c = new InPlayCreature(creature, l);
        gs.addCreature(c);
        l.addCard(c);
        gs.getArrivalLanes().remove(Content.lanes
                .get(lane).content().arrivalCreatures.indexOf(creature));
        gs.getArrivalCreatures().remove(creature);
    }

    public CreaturePlayedEvent(Board gs, CreatureCard creature, int lane)
    {
        super(gs);
        this.creature = creature;
        this.lane = lane;
    }

    public int getLane()
    {
        return lane;
    }

    public void setLane(int lane)
    {
        this.lane = lane;
    }

    public CreatureCard getCreature()
    {
        return creature;
    }
=======
public class CreaturePlayedEvent extends CardPlayedEvent {

	CreatureCard creature;
	Content content;
	
	@Override
	public void fireEvent() {
		InPlayCreature c = new InPlayCreature(creature);
		content.addCreature(c); 
		content.arrivalCreatures.remove(creature);
	}
	
	public CreaturePlayedEvent(CreatureCard creature, Content c) {
		this.creature = creature;
		content = c;
	}

	public CreatureCard getCreature() {
		return creature;
	}
>>>>>>> master
}
