package abilities;

import events.BasicEvent;

public class Ability {
	String name;
	String description;
	BasicEvent activation;
	BasicEvent callevent;
	public Ability(){
		
	}
	public boolean passEvent(BasicEvent event){
		return false;
	}
}
