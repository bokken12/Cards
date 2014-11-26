package cards;

import java.util.ArrayList;
import java.util.HashMap;

import abilities.Ability;

import events.TagEvent;

public class Card {
	String name;
	HashMap<TagEvent, ArrayList<Ability>> listenerMap;
	public Card(){
		
	}
	public String getName(){
		return name;
	}
	public void receiveEvent(TagEvent event){
		if(listenerMap.containsKey(event)){
			for(Ability activatedAbility: listenerMap.get(event)){
				
			}
		}
	}
}
