package messaging;

import java.util.ArrayList;

public class MatchMessage extends Message {

	public MatchMessage(ArrayList<Integer> startingHand, String opponent) {
	    super();
	    StringableArrayList<StringableInteger> sh = new StringableArrayList<StringableInteger>(StringableInteger.class, startingHand);
		data.add("startingHand", sh);
		StringableString o = new StringableString(opponent);
		data.add("opponent", o);
	}
	public ArrayList<Integer> getStartingHand(){
	    return (ArrayList<Integer>) data.get("startingHand").getMirror();
	}
	public String getOpponent(){
	    return (String) data.get("opponent").getMirror();
	}
}
