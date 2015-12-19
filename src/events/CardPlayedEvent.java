package events;

import clientStuff.GameState;

public class CardPlayedEvent extends GameEvent{

	public CardPlayedEvent(GameState gs)
    {
        super(gs);
    }

    @Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}

}
