package events;

import clientStuff.Board;
import uselessSubclasses.DamageableEntity;

public class DamageCreatureEvent extends DamageEvent {

	public DamageCreatureEvent(Board gs, int dmg, DamageableEntity target) {
		super(gs, dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
