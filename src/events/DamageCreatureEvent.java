package events;

import clientStuff.BoardState;
import uselessSubclasses.DamageableEntity;

public class DamageCreatureEvent extends DamageEvent {

	public DamageCreatureEvent(BoardState gs, int dmg, DamageableEntity target) {
		super(gs, dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
