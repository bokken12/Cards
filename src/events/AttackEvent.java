package events;

import cards.CreatureCard;
import Player.PlayerRep;

public class AttackEvent extends GameEvent {

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
