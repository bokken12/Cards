package cards;

import java.util.ArrayList;
import java.util.HashMap;

import abilities.Ability;

import events.BasicEvent;

public class Card {
	String name;
	HashMap<BasicEvent, ArrayList<Ability>> listenerMap;
	public Card(){
		
	}
	public String getName(){
		return name;
	}
	public void receiveEvent(BasicEvent event){
		if(listenerMap.containsKey(event)){
			for(Ability activatedAbility: listenerMap.get(event)){
				
			}
		}
	}
}
