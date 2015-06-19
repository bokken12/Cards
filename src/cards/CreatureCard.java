package cards;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import clientStuff.Content;
import MegaServer.GameHandler;
import MegaServer.MegaServer;
import uselessSubclasses.DamageableEntity;
import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class CreatureCard extends Card implements GameListener{
	int power;
	int toughness;
	int cost;
	String name;
	HashMap<Class<GameEvent>, ArrayList<Runnable>> listeners;
	
	public CreatureCard(String n, int p, int t, int c, ImageIcon img, HashMap<Class<GameEvent>, ArrayList<Runnable>> a, int id) {
		
		power = p;
		name = n;
		toughness = t;
		cost = c; 
		listeners = a;
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
		for(Class<GameEvent> event : listeners.keySet()){
			Content.getBus().addGameListener(new Integer(1), event, this);
			//event.addListener(myPriority, this)
		}
	}

	@Override
	public void passEvent(GameEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
