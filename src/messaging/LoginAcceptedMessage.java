package messaging;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginAcceptedMessage extends Message {

	public LoginAcceptedMessage() {
		
	}
	
	public LoginAcceptedMessage(String name, String pass, int goldMoney, int ranking, ArrayList<Integer> cardList, HashMap<String, ArrayList<Integer>> deckList, ArrayList<String> friendList){
		
		StringableString username = new StringableString(name);
		StringableString password = new StringableString(pass);
		StringableInteger gold = new StringableInteger(goldMoney);
		StringableInteger rank = new StringableInteger(ranking);
		StringableArrayList<StringableInteger> cards = new StringableArrayList<StringableInteger>(/*cardList*/);
		//decks & friends
		
		data.add("username", username);
		data.add("password", password);
		data.add("gold", gold);
		data.add("rank", rank);
		//data.add("card", cards);
		//decks & friends
	}
	
	public String getUsername() {
		return data.get("username").toString();
	}
	
	public String getPassword() {
		return data.get("password").toString();
	}
}
