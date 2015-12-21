package events;

import clientStuff.Content;
import clientStuff.BoardState;
import cards.InPlayCreature;
import cards.SpellCard;

public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
	public TargetedSpellPlayedEvent(SpellCard card, BoardState gs, InPlayCreature target) {
		super(gs, card);
		// TODO Auto-generated constructor stub
	}

	T target;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
}
