package events;

import clientStuff.Board;
import cards.CreatureCard;
import cards.InPlayCreature;
import clientStuff.Content;

public class AbilityEvent extends GameEvent {

	
	InPlayCreature card;
	public Content c;
	public InPlayCreature target;
	
	@Override
	public void fireEvent() {
		
		
	}
	
<<<<<<< HEAD
	public AbilityEvent(Board gs, InPlayCreature c) {
	    super(gs);
		card = c;
=======
	public AbilityEvent(InPlayCreature creature, InPlayCreature target, Content c) {
		card = creature;
		this.c = c;
		this.target = target;
>>>>>>> master
	}

	
	public InPlayCreature getCard() {
		return card;
	}
}
