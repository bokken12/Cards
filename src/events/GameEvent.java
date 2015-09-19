package events;

import java.util.ArrayList;

public abstract class GameEvent {
	//private static ArrayList<GameListener> listeners = new ArrayList<GameListener>();
	boolean isCancelled = false;
	public abstract void fireEvent();
	public void setCancelled(boolean cancelled){
		isCancelled = cancelled;
	}
	public boolean isEventCancelled(){
		return isCancelled;
	}
	public static void addListener(){
	}
}
