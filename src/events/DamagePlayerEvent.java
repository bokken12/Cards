package events;

import uselessSubclasses.DamageableEntity;

public class DamagePlayerEvent extends DamageEvent {

	public DamagePlayerEvent(int dmg, DamageableEntity target) {
		super(dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
