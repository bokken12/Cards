package events;

import Player.GamePlayer;

public class TurnEndedEvent extends GameEvent {

	GamePlayer ender;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnEndedEvent(GamePlayer e) {
		ender = e;
	}
	
	public GamePlayer getPlayer() {
		return ender;
	}

}
