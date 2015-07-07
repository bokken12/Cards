package abilities;

import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	Class<? extends GameEvent> activation;
	GameEvent callevent;
	public Ability(String name, String desc, Class<? extends GameEvent> activation, AbilityRunnable a) {

		this.name = name;
		description = desc;
		this.activation = activation;
		EventBus.addGameListener(10, activation, this);
	}

	public void passEvent(GameEvent event){
		EventBus.callEvent(callevent);
	}
}
