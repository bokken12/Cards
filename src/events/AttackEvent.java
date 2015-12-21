package events;

import player.PlayerRep;
import clientStuff.BoardState;
import cards.CreatureCard;

public class AttackEvent extends GameEvent {

	public AttackEvent(BoardState gs)
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
