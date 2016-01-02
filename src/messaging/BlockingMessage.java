package messaging;

import java.util.HashMap;

public class BlockingMessage extends Message {

	public BlockingMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public BlockingMessage(HashMap<Integer, Integer> blocking) {
		
		data.add("blocking", new StringableHashMap<StringableInteger, StringableInteger>());
		data.get("blocking").fromMirror(blocking);
	}

}
