package events;

import clientStuff.GameState;
import Player.GamePlayer;

public class TurnStartedEvent extends GameEvent {

	GamePlayer starter;
	
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public TurnStartedEvent(GameState gs, GamePlayer e) {
	    super(gs);
		starter = e;
	}
	
	public GamePlayer getPlayer() {
		return starter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((starter == null) ? 0 : starter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnStartedEvent other = (TurnStartedEvent) obj;
		if (starter == null) {
			if (other.starter != null)
				return false;
		} else if (!starter.equals(other.starter))
			return false;
		return true;
	}

}
