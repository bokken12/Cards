package abilities;

import clientStuff.Content;
import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener{
	String name;
	String description;
	Class<? extends GameEvent> activation;
	AbilityRunnable a;
	private boolean hasTarget;
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
		EventBus.getInstance().addGameListener(10, activation, this);
	}

	public String getText() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String toString() {
		return "Ability [name=" + name + ", description=" + description
				+ ", activation=" + activation + ", a=" + a + "]";
	}
	
	public boolean hasTarget() {
		return hasTarget;
	}
	
	
}
