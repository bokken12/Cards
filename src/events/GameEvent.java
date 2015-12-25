package events;

import java.util.ArrayList;

import clientStuff.Board;

public abstract class GameEvent {
	//private static ArrayList<GameListener> listeners = new ArrayList<GameListener>();
	protected boolean isCancelled = false;
	protected Board gs;
	public abstract void fireEvent();
	public GameEvent(Board gs){
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
