package messaging;

public class PlayingMessage extends Message {

	public PlayingMessage() {
		
	}
	
	public PlayingMessage(String name, int rank) {
		StringableString nam = new StringableString(name);
		StringableInteger r = new StringableInteger(rank);
		
		data.add("name", nam);
		data.add("rank", r);
	}
	
	
	public String getName() {
		return data.get("name").toString();
	}
	
	public Integer getRank() {
		return ((StringableInteger) data.get("rank")).getInt();
	}
}
