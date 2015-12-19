package events;

import clientStuff.GameState;
import cards.CreatureCard;
import Player.PlayerRep;

public class AttackEvent extends GameEvent {

	public AttackEvent(GameState gs)
    {
        super(gs);
    }

    CreatureCard[] attacking;
	PlayerRep target;
	
	public CreatureCard[] getAttacking() {
		return attacking;
	}

	public void setAttacking(CreatureCard[] attacking) {
		this.attacking = attacking;
	}

	public PlayerRep getTarget() {
		return target;
	}

	public void setTarget(PlayerRep target) {
		this.target = target;
	}

	@Override
	public void fireEvent() {
		
		
	}

}
