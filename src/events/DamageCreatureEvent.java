package events;

import uselessSubclasses.DamageableEntity;

public class DamageCreatureEvent extends DamageEvent {

	public DamageCreatureEvent(int dmg, DamageableEntity target) {
		super(dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
