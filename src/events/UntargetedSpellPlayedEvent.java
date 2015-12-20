package events;

import clientStuff.Content;
import clientStuff.GameState;
import cards.SpellCard;

public class UntargetedSpellPlayedEvent extends SpellPlayedEvent {

	public UntargetedSpellPlayedEvent(SpellCard card, GameState gs) {
		super(gs, card);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
