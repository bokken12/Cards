package abilities;

import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	Class<? extends GameEvent> activation;
	AbilityRunnable a;
	public Ability(String name, String desc, Class<? extends GameEvent> activation, AbilityRunnable a) {

		this.a = a;
		this.name = name;
		description = desc;
		this.activation = activation;
	}

	public void passEvent(GameEvent event){
		a.run(event);
	}
	
	public void RegisterListeners() {
		EventBus.addGameListener(10, activation, this);
	}

	public String getText() {
		// TODO Auto-generated method stub
		return description;
	}
}
