package abilities;

import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	GameEvent activation;
	GameEvent callevent;
	public Ability(){
		
	}
	
	public Ability(String name, String desc, GameEvent activation, GameEvent callevent) {
		
		this.name = name;
		description = desc;
		this.activation = activation;
		this.callevent = callevent;
	}
	
	public void passEvent(GameEvent event){
	}
}
