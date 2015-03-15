package cards;

import java.util.ArrayList;
import java.util.HashMap;

import abilities.Ability;

import events.GameEvent;

public class Card {
	String name;
	HashMap<GameEvent, ArrayList<Ability>> listenerMap;
	public Card(){
		
	}
	public String getName(){
		return name;
	}
	public void receiveEvent(GameEvent event){
		if(listenerMap.containsKey(event)){
			for(Ability activatedAbility: listenerMap.get(event)){
				
			}
		}
	}
}
