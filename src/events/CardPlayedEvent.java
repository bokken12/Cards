package events;

import clientStuff.Board;

public class CardPlayedEvent extends GameEvent{

	public CardPlayedEvent(Board gs)
    {
        super(gs);
    }

    @Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}

}
