package cards;

import uselessSubclasses.DamageableEntity;

public class InPlayCreature implements DamageableEntity{

	int power;
	int health;
	
	
	@Override
	public void dealDamage(int dmg) {
		// TODO Auto-generated method stub
		
	}
	
	public InPlayCreature(CreatureCard card) {
		
	}

	public int getPower() {
		return power;
	}

	public void AddPower(int power) {
		this.power += power;
	}

	public int getHealth() {
		return health;
	}

	public void AddHealth(int health) {
		this.health += health;
	}

}
