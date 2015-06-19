package events;

public abstract class GameEvent {
	boolean isCancelled = false;
	public abstract void fireEvent();
	public void setCancelled(boolean cancelled){
		isCancelled = cancelled;
	}
	public boolean isEventCancelled(){
		return isCancelled;
	}
}
