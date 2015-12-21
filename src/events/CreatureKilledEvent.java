package events;

import clientStuff.BoardState;
import cards.InPlayCreature;

public class CreatureKilledEvent extends GameEvent {
	
	public void fireEvent() {
		
	}
	
	public CreatureKilledEvent(BoardState gs, InPlayCreature c) {
		super(gs);
	}

}
