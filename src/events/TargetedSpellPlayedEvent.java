package events;

import clientStuff.Content;
import cards.InPlayCreature;
import cards.SpellCard;



public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
	
	InPlayCreature target;
	
	public TargetedSpellPlayedEvent(SpellCard card, Content c, InPlayCreature target) {
		super(card, c);
		this.target = target;
		
	}
	
	@Override
	public void fireEvent() {
		c.selectedCard = target;
		card.getEffect().run(c);
	}
}
