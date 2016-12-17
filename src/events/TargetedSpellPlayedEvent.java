package events;

import clientStuff.Content;
import clientStuff.Board;
import cards.InPlayCreature;
import cards.SpellCard;



public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
<<<<<<< HEAD
	public TargetedSpellPlayedEvent(SpellCard card, Board gs, InPlayCreature target) {
		super(gs, card);
		// TODO Auto-generated constructor stub
=======
	
	InPlayCreature target;
	
	public TargetedSpellPlayedEvent(SpellCard card, Content c, InPlayCreature target) {
		super(card, c);
		this.target = target;
		
>>>>>>> master
	}
	
	@Override
	public void fireEvent() {
		c.selectedCard = target;
		card.getEffect().run(c);
	}
}
