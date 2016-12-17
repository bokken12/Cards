package events;

import clientStuff.Board;
import cards.InPlayCreature;
import clientStuff.Content;

public class CreatureKilledEvent extends GameEvent {
	
	boolean yours;
	Content c;
	InPlayCreature dead;
	public void fireEvent() {
		if(yours) {
			c.myCreatures.remove(dead);
		} else {
			c.enemyCreatures.remove(dead);
		}
		c.cardsInPlay.remove(dead);
	}
	
<<<<<<< HEAD
	public CreatureKilledEvent(Board gs, InPlayCreature c) {
		super(gs);
=======
	public CreatureKilledEvent(InPlayCreature dead, Content c, boolean yours) {
		this.dead = dead;
		this.c = c;
		this.yours = yours;
				
>>>>>>> master
	}

}
