package events;

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
	
	public ModifyEvent(InPlayCreature target, int power, int health) {
		this.power = power;
		this.health = health;
	}

}
