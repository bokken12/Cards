package events;

import clientStuff.Board;
import cards.InPlayCreature;

public class CreatureKilledEvent extends GameEvent {
	
	public void fireEvent() {
		
	}
	
	public CreatureKilledEvent(Board gs, InPlayCreature c) {
		super(gs);
	}

}
