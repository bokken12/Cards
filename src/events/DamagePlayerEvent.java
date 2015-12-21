package events;

import clientStuff.BoardState;
import uselessSubclasses.DamageableEntity;

public class DamagePlayerEvent extends DamageEvent {

	public DamagePlayerEvent(BoardState gs, int dmg, DamageableEntity target) {
		super(gs, dmg, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
