package events;

import clientStuff.GameState;
import uselessSubclasses.DamageableEntity;

public class DamageCreatureEvent extends DamageEvent {

	public DamageCreatureEvent(GameState gs, int dmg, DamageableEntity target) {
		super(gs, dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
