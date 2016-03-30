package cards;

import java.awt.Point;

import uselessSubclasses.DamageableEntity;

public class InPlayCreature implements DamageableEntity {

	int power;
	private int health;
	String type;
	int maxHealth;
	CreatureCard card;
	
	int startX;
	int startY;
	int endX;
	int endY;
	
	boolean green = false;
	boolean red = false;
	
	
	@Override
	public String toString() {
		return "InPlayCreature [power=" + power + ", health=" + health
				+ ", type=" + type + ", maxHealth=" + maxHealth + ", card=" + card + "]";
	}
	
//	public InPlayCreature fromString() {
//		return "InPlayCreature [power=" + power + ", health=" + health
//				+ ", type=" + type + ", maxHealth=" + maxHealth + ", lane="
//  			+ lane + ", card=" + card + "]";
//m	}

	@Override
	public void dealDamage(int dmg) {
		health = health - dmg;
	}
	
	public InPlayCreature(CreatureCard card) {
		power = card.getPower();
		health = card.getToughness();
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
	
	public boolean isGreen() {
		return green;
	}
	
	public void setGreen(boolean g) {
		green = g;
	}

	public void AddHealth(int health) {
		this.health += health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getType() {
		return type;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public CreatureCard getCard() {
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

	public boolean isRed() {
		return red;
	}
	
	public void setRed(boolean red) {
		this.red = red;
	}

}
