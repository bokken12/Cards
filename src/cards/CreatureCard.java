package cards;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import abilities.Ability;
import clientStuff.Content;
import server.GameHandler;
import server.Server;
import uselessSubclasses.DamageableEntity;
import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class CreatureCard extends Card implements GameListener{
	int power;
	int toughness;
	int cost;
	String name;
	Ability ability;
	boolean haste = false;
	
	public CreatureCard(String n, int p, int t, int c, ImageIcon img, Ability a, int id) {
		
		power = p;
		name = n;
		toughness = t;
		cost = c; 
		ability = a;
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
	
	public void registerListeners() {
		ability.RegisterListeners();
	}

	@Override
	public void passEvent(GameEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void giveHaste() {
		haste = true;
	}
	
	public boolean hasHaste() {
		return haste;
	}
	
}
