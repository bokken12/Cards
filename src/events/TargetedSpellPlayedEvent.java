package events;

import clientStuff.Content;
import cards.InPlayCreature;
import cards.SpellCard;

public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
	public TargetedSpellPlayedEvent(SpellCard card, Content c, InPlayCreature target) {
		super(card, c);
	}
	
	@Override
	public void fireEvent() {
		card.getEffect().run(c);
	}
}
