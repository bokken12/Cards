package events;

import clientStuff.Content;
import cards.SpellCard;

public class UntargetedSpellPlayedEvent extends SpellPlayedEvent {

	public UntargetedSpellPlayedEvent(SpellCard card, Content c) {
		super(card, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
