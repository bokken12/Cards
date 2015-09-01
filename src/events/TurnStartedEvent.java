package events;

import Player.GamePlayer;

public class TurnStartedEvent extends GameEvent {

	GamePlayer starter;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnStartedEvent(GamePlayer e) {
		starter = e;
	}
	
	public GamePlayer getPlayer() {
		return starter;
	}

}
