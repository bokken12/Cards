package miniStates;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import clientStates.GameState;
import clientStuff.StateMachine;
import messaging.BlockingMessage;
import messaging.Message;

import cards.InPlayCreature;

public class BlockingState extends MiniState {

	
	ArrayList<InPlayCreature> attacking;
	HashMap<Integer, Integer> blocking;
	InPlayCreature selectedBlocker;
	
	public BlockingState() {
		
	}

	@Override
	public void MessageRecieved(Message message) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("block")) {
			StateMachine.sendMessage(new BlockingMessage(blocking));
		}
		
	}

	@Override
	public void onBegin(GameState stater) {
		
		
	}

	@Override
	public void onLeave(GameState stater) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(game.getBoard().getLane(game.getCurrentLane()).getClick(e.getPoint()) != null) {
			if(game.getBoard().getMyCreatures().contains(game.getBoard().getLane(game.getCurrentLane()).getClick(e.getPoint()))) {
				selectedBlocker = game.getBoard().getLane(game.getCurrentLane()).getClick(e.getPoint());
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
