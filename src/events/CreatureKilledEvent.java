package events;

import clientStuff.GameState;
import cards.InPlayCreature;

public class CreatureKilledEvent extends GameEvent {
	
	public void fireEvent() {
		
	}
	
	public CreatureKilledEvent(GameState gs, InPlayCreature c) {
		super(gs);
	}

}
