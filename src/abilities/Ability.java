package abilities;

import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	Class<GameEvent> activation;
	GameEvent callevent;
	public Ability(){
		
	}
	public void passEvent(GameEvent event){
		EventBus.getBus().callEvent(callevent);
	}
}
