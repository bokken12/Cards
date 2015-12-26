package miniStates;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import cards.InPlayCreature;
import clientStates.GameState;
import clientStuff.StateMachine;
import messaging.AttackingMessage;
import messaging.Message;

public class AttackingState extends MiniState {

	public AttackingState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MessageRecieved(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("attack")) {
			
			ArrayList<Integer> attacking = new ArrayList<Integer>();
			for(InPlayCreature i : game.getBoard().getAttacking()) {
				attacking.add(game.getBoard().getMyCreatures().indexOf(i));
			}
			
			StateMachine.sendMessage(new AttackingMessage(attacking));
		}
		
	}

	@Override
	public void onBegin(GameState stater) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeave(GameState stater) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(game.getBoard().getLane(game.getCurrentLane()).getClick(e.getPoint()) != null) {
			
			InPlayCreature c = game.getBoard().getLane(game.getCurrentLane()).getClick(e.getPoint());
			game.getBoard().getAttacking().add(c);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
