package events;

import java.util.ArrayList;
import java.util.HashMap;

import cards.Card;

public class TagEvent {
	public static HashMap<Integer, ArrayList<Card>> listeners = new HashMap<Integer, ArrayList<Card>>();
	boolean isCancelled = false;
	public TagEvent(){
		
	}
	public void setCancelled(boolean shouldCancel){
		isCancelled = shouldCancel;
	}
	public void AddListener(Card card){
		
	}
}
