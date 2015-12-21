package events;

import java.util.ArrayList;

import clientStuff.BoardState;

public abstract class GameEvent {
	//private static ArrayList<GameListener> listeners = new ArrayList<GameListener>();
	protected boolean isCancelled = false;
	protected BoardState gs;
	public abstract void fireEvent();
	public GameEvent(BoardState gs){
	    this.gs = gs;
	}
	public void setCancelled(boolean cancelled){
		isCancelled = cancelled;
	}
	public boolean isEventCancelled(){
		return isCancelled;
	}
	public static void addListener(){
	}
}
