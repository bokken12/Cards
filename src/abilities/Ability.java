package abilities;

import events.AnimationRunnable;
import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	Class activation;
	GameEvent callevent;
	public Ability(){
		
	}
	
	public Ability(String name, String desc, Class activation, AnimationRunnable a) {
		
		this.name = name;
		description = desc;
		this.activation = activation;
		EventBus.addGameListener(10, activation, this);
	}
	
	public void passEvent(GameEvent event){
		EventBus.callEvent(callevent);
	}
}
