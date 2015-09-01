package cards;

import java.awt.Point;

import uselessSubclasses.DamageableEntity;
import uselessSubclasses.Lane;

public class InPlayCreature implements DamageableEntity{

	int power;
	int health;
	String type;
	int maxHealth;
	Lane lane;
	CreatureCard card;
	
	int startX;
	int startY;
	int endX;
	int endY;
	
	
	@Override
	public String toString() {
		return "InPlayCreature [power=" + power + ", health=" + health
				+ ", type=" + type + ", maxHealth=" + maxHealth + ", lane="
				+ lane + ", card=" + card + "]";
	}

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
	
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	public boolean containsPoint(Point p) {
		double x = p.getX();
		double y = p.getY();

		if(x > this.getStartX() && x <this.getEndX()) {
			if(y > this.getStartY() && y < this.getEndY()) {
				return true;
			}
		}
		return false;
	}

}
