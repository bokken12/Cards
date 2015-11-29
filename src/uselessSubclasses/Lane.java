package uselessSubclasses;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import clientStuff.Content;
import cards.InPlayCreature;

public class Lane {
	int startX;
	int endX;
	int startY;
	int endY;
	
	int number;
	
	public static final int CARD_WIDTH = 120;
	public static final int CARD_HEIGHT = 170;

	Content content;
	ArrayList<InPlayCreature> creatures = new ArrayList<InPlayCreature>();
	ArrayList<InPlayCreature> enemyCreatures = new ArrayList<InPlayCreature>();

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

	public ArrayList<InPlayCreature> getCreatures() {
		return creatures;
	}

	public Lane(Content c, int num) {
		content = c;
		number = num;
	}

	public boolean containsPoint(Point p) {
		double x = p.getX();
		double y = p.getY();

		if(x > startX && x < endX) {
			if(y > startY && y < endY) {
				return true;
			}
		}
		return false;
	}

	public boolean addCard(InPlayCreature creature) {
		if(creatures.size() == 2) {
			return false;
		}
		//Dimension d = new Dimension()
		creatures.add(creature);
		return true;
	}

	public boolean addEnemy(InPlayCreature creature) {
		if(creatures.size() == 2) {
			return false;
		}
		enemyCreatures.add(creature);
		return true;
	}

	public ArrayList<InPlayCreature> getEnemyCreatures() {
		return enemyCreatures;
	}

	public InPlayCreature getClick(Point click) {
		System.out.println("getClick at " + click);
		for(int i = 0; i < creatures.size(); i++) {
			int sX = i*115 + startX;
			int sY = 400;
			int eX = sX + CARD_WIDTH;
			int eY = sY + CARD_HEIGHT;
			System.out.println("Card: x is " + sX + " to " + eX + ", y is " + sY + " to " + eY);
			if(click.x > sX && click.x < eX) {
				if(click.y > sY && click.y < eY) {
					return creatures.get(i);
				}
			}
		}
		
		for(int i = 0; i < enemyCreatures.size(); i++) {
			int sX = i*115 + startX;
			int sY = 180;
			int eX = sX + CARD_WIDTH;
			int eY = sY + CARD_HEIGHT;
			System.out.println("Enemy Card: x is " + sX + " to " + eX + ", y is " + sY + " to " + eY);
			if(click.x > sX && click.x < eX) {
				if(click.y > sY && click.y < eY) {
					return enemyCreatures.get(i);
				}
			}
		}
		System.out.println("Warning: getClick found no creature at " + click + "!!!");
		return null;
	}
	
	@Override
	public String toString() {
		return Integer.toString(number);
	}
	public Content content(){
		return content;
	}
}