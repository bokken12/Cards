package cards;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import uselessSubclasses.DamageableEntity;
import events.GameEvent;
import events.GameListener;

public class CreatureCard extends Card {
	int power;
	int toughness;
	int cost;
	String name;
	
	public CreatureCard(String n, int p, int t, int c, HashMap<GameEvent, ArrayList<Runnable>> a) {
		
		power = p;
		name = n;
		toughness = t;
		cost = c; 
	}

	public int getPower() {
		return power;
	}
	
	public void setPower(int a) {
		power = a;
	}
	
	public int getToughness() {
		return toughness;
	}
	
	public void setToughness(int a) {
		toughness = a;
	}
	
}
