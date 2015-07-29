package cards;

import uselessSubclasses.DamageableEntity;
import uselessSubclasses.Lane;

public class InPlayCreature implements DamageableEntity{

	int power;
	int health;
	String type;
	int maxHealth;
	Lane lane;
	CreatureCard card;
	
	
	@Override
	public void dealDamage(int dmg) {
		// TODO Auto-generated method stub
		
	}
	
	public InPlayCreature(CreatureCard card, Lane lane) {
		this.lane = lane;
		power = card.getPower();
		maxHealth = health;
		this.card = card;
	}

	public int getPower() {
		return power;
	}

	public void AddPower(int power) {
		this.power += power;
	}
	
	public void SubtractPower(int power) {
		this.power -= power;
	}

	public int getHealth() {
		return health;
	}

	public void AddHealth(int health) {
		this.health += health;
	}
	
	public String getType() {
		return type;
	}
	
	public int getMaxHealth() {
		return maxHealth;
		
	}

	public Lane getLane() {
		return lane;
	}

	public void setLane(Lane lane) {
		this.lane = lane;
	}
	
	public Card getCard() {
		return card;
	}

}
