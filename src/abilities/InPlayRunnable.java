package abilities;

import java.util.ArrayList;

import cards.InPlayCreature;
import events.CreaturePlayedEvent;
import events.GameEvent;

public abstract class InPlayRunnable{
	/*ArrayList<Class<?>> args;
	public AbilityRunnable(ArrayList<Class<?>> args){
		this.args = args;
	}*/
	InPlayCreature card;
	public void run(InPlayCreature card, CreaturePlayedEvent e){
		this.card = card;
	}
}
