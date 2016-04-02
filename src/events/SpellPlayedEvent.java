package events;

import clientStuff.Content;
import cards.SpellCard;

public class SpellPlayedEvent extends CardPlayedEvent {

	SpellCard card;
	Content c;
	
	@Override
	public void fireEvent() {
		card.getEffect().run(c);
	}
	
	public SpellPlayedEvent(SpellCard card, Content c) {
		this.card = card;
	}
}
