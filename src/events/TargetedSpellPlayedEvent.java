package events;

import clientStuff.Content;
import cards.InPlayCreature;
import cards.SpellCard;

public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
	public TargetedSpellPlayedEvent(SpellCard card, Content c, InPlayCreature target) {
		super(card, c);
		// TODO Auto-generated constructor stub
	}

	T target;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
