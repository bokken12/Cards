package messaging;

import java.util.ArrayList;

public class PlayingMessage extends Message {

	public PlayingMessage() {
		
	}
	
	public PlayingMessage(String name, int rank, ArrayList<Integer> deck) {
		StringableString nam = new StringableString(name);
		StringableInteger r = new StringableInteger(rank);
		StringableArrayList<StringableInteger> ar = new StringableArrayList<StringableInteger>();
		ar.fromMirror(deck);
		data.add("name", nam);
		data.add("rank", r);
		data.add("deck", ar);
	}
	
	
	public String getName() {
		return data.get("name").toString();
	}
	
	public Integer getRank() {
		return ((StringableInteger) data.get("rank")).getInt();
	}
	
	public ArrayList<Integer> getDeck(){
	    return (ArrayList<Integer>) data.get("deck").getMirror();
	}
}
