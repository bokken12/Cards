package events;

import clientStuff.BoardState;

public class CardPlayedEvent extends GameEvent{

	public CardPlayedEvent(BoardState gs)
    {
        super(gs);
    }

    @Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}

}
