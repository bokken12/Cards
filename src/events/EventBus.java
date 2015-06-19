package events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventBus {
	static EventBus currentbus;
	HashMap<Class<GameEvent>, HashMap<Integer, ArrayList<GameListener>>> listening = new HashMap<Class<GameEvent>, HashMap<Integer, ArrayList<GameListener>>>();
	public void addGameListener(Integer priority, Class<GameEvent> e, GameListener l){
		if(priority.intValue() > 20 || priority.intValue() < 0){
			throw new IllegalArgumentException("You may only have priorities between 0 and 20");
		}
		for(Class<GameEvent> c = e; !(c.equals(GameEvent.class)); c = (Class<GameEvent>) c.getSuperclass()){
			if(listening.containsKey(c)){
				if(listening.get(c).containsKey(priority)){
					listening.get(c).get(priority).add(l);
				} else {
					ArrayList<GameListener> a = new ArrayList<GameListener>();
					a.add(l);
					listening.get(c).put(priority, a);
				}
			} else {
				ArrayList<GameListener> a = new ArrayList<GameListener>();
				a.add(l);
				HashMap<Integer, ArrayList<GameListener>> h = new HashMap<Integer, ArrayList<GameListener>>();
				h.put(priority, a);
				listening.put(c, h);
			}
		}
	}
	public void callEvent(GameEvent e){
		if(listening.containsKey(e.getClass())){
			for(int i = 0; i <= 20; i++){
				if(listening.get(e).containsKey(i)){
					for(GameListener listener: listening.get(e).get(i)){
						listener.passEvent(e);
					}
				}
			}
		}
		e.fireEvent();
	}
	public static EventBus getBus(){
		return currentbus;
	}
}
