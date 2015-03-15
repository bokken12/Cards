package events;

import cards.Card;

public abstract class CardComeIntoPlayEvent extends GameEvent{
	Card card;
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	public Card getCardPlayed(){
		return card;
	}

}
