package events;

import clientStuff.Board;
import uselessSubclasses.DamageableEntity;

public class DamageEvent extends GameEvent{
	
	int dmg;
	DamageableEntity target;
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		target.dealDamage(dmg);
	}
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public DamageableEntity getTarget() {
		return target;
	}
	public void setTarget(DamageableEntity target) {
		this.target = target;
	}
	
	public DamageEvent(Board gs, int dmg, DamageableEntity target) {
	    super(gs);
		this.dmg = dmg;
		this.target = target;
	}

}
