package events;

import clientStuff.Content;
import clientStuff.BoardState;
import cards.SpellCard;

public class UntargetedSpellPlayedEvent extends SpellPlayedEvent {

	public UntargetedSpellPlayedEvent(SpellCard card, BoardState gs) {
		super(gs, card);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
