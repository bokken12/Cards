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
	public void passEvent(GameEvent event){
	}
}
