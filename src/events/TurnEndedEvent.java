package events;

import clientStuff.GameState;
import Player.GamePlayer;

public class TurnEndedEvent extends GameEvent {

	GamePlayer ender;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnEndedEvent(GameState gs, GamePlayer e) {
	    super(gs);
		ender = e;
	}
	
	public GamePlayer getPlayer() {
		return ender;
	}

}
