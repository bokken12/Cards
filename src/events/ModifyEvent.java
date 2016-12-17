package events;

import clientStuff.Board;
import cards.InPlayCreature;

public class ModifyEvent extends GameEvent{

	InPlayCreature target;
	int health;
	int power;
	
	@Override
	public void fireEvent() {
		target.AddHealth(health);
		target.AddPower(power);
		
	}
	
	public ModifyEvent(Board gs, InPlayCreature target, int power, int health) {
	    super(gs);
		this.power = power;
		this.health = health;
		this.target = target;
	}

}
