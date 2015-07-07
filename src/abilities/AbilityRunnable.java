package abilities;

import java.util.ArrayList;

import events.GameEvent;

public abstract class AbilityRunnable{
	/*ArrayList<Class<?>> args;
	public AbilityRunnable(ArrayList<Class<?>> args){
		this.args = args;
	}*/
	GameEvent event;
	public void run(GameEvent event){
		this.event = event;
	}
}
