package events;

import player.GamePlayer;
import clientStuff.Board;

public class TurnEndedEvent extends GameEvent {

	GamePlayer ender;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnEndedEvent(Board gs, GamePlayer e) {
	    super(gs);
		ender = e;
	}
	
	public GamePlayer getPlayer() {
		return ender;
	}

}
