package events;

import clientStuff.Content;
import clientStuff.Board;
import cards.SpellCard;

public class SpellPlayedEvent extends CardPlayedEvent {

	SpellCard card;
	Content c;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		card.getEffect().run(c);
		
	}
	
	public SpellPlayedEvent(Board gs, SpellCard card) {
	    super(gs);
		this.card = card;
	}
}
