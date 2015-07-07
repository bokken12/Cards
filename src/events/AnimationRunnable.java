package events;

public abstract class AnimationRunnable implements Runnable{
	int i;
	public AnimationRunnable(int i){
		this.i = i;
	}
	
	public abstract void run();
}