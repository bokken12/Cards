package cards;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import uselessSubclasses.DamageableEntity;
import events.GameEvent;
import events.GameListener;

public class CreatureCard extends Card {
	int power;
	int toughness;
	int cost;
	String name;
	
	public CreatureCard(String n, int p, int t, int c, ImageIcon img, HashMap<GameEvent, ArrayList<Runnable>> a, int id) {
		
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
