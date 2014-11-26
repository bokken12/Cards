package abilities;

import events.TagEvent;

public class Ability {
	String name;
	String description;
	TagEvent activation;
	TagEvent callevent;
	public Ability(){
		
	}
	public boolean passEvent(TagEvent event){
		return false;
	}
}
