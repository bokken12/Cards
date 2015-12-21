package events;

import player.GamePlayer;
import clientStuff.BoardState;

public class TurnEndedEvent extends GameEvent {

	GamePlayer ender;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnEndedEvent(BoardState gs, GamePlayer e) {
	    super(gs);
		ender = e;
	}
	
	public GamePlayer getPlayer() {
		return ender;
	}

}
