package events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventBus {

	static HashMap<Class<? extends GameEvent>, HashMap<Integer, ArrayList<GameListener>>> listening = new HashMap<Class<? extends GameEvent>, HashMap<Integer, ArrayList<GameListener>>>();
	@SuppressWarnings("unchecked")

	public void addGameListener(Integer priority, Class<? extends GameEvent> e, GameListener l){
		if(priority.intValue() > 20 || priority.intValue() < 0){
			throw new IllegalArgumentException("You may only have priorities between 0 and 20");
		}
		for(Class<? extends GameEvent> c = e; !(c.equals(GameEvent.class)); c = (Class<? extends GameEvent>) c.getSuperclass()){
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
			if(c.getSuperclass().equals(GameEvent.class)){
				break;
			}
		}
	}
	public void callEvent(GameEvent e) {
		System.out.println("Event Call");
		for(Class<? extends GameEvent> g : listening.keySet()) {
			if(g.equals(e.getClass())) {
				for(int i = 0; i <= 20; i++){
					if(listening.get(e.getClass()).containsKey(i)){
						for(GameListener listener: listening.get(e.getClass()).get(i)){
							listener.passEvent(e);
						}
					}
				}
			}
		}
		e.fireEvent();
	}
}
