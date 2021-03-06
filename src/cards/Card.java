package cards;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import abilities.Ability;
import events.GameEvent;

public class Card {
	String name;
	HashMap<GameEvent, ArrayList<Ability>> listenerMap;
	ImageIcon image;
	int imX;
	int imY;
	int num;

	public Card(){
		num =  Cards.cards.indexOf(this);
		
	}
	public static Card fromName(String name){
		return null;
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

	public ImageIcon getImageIcon() {
		return image;

	}

	public String getText() {
		return "";
	}

	public int getCost() {
		return 0;
	}

	public int getID() {
		return num;
	}

}
