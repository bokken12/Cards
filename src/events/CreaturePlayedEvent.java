package events;

import uselessSubclasses.Lane;
import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;
import clientStuff.BoardState;

public class CreaturePlayedEvent extends CardPlayedEvent
{

    CreatureCard creature;
    int lane;

    @Override
    public void fireEvent()
    {
        Lane l = gs.getLane(lane);
        InPlayCreature c = new InPlayCreature(creature, l);
        l.content().addCreature(c);
        l.addCard(c);
        l.content().arrivalLanes.remove(Content.lanes
                .get(lane).content().arrivalCreatures.indexOf(creature));
        l.content().arrivalCreatures.remove(creature);
    }

    public CreaturePlayedEvent(BoardState gs, CreatureCard creature, int lane)
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
}
