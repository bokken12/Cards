package messaging;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginAcceptedMessage extends Message {

	public LoginAcceptedMessage() {
		
	}
	
	public LoginAcceptedMessage(String name, String pass, String mail, int goldMoney, int ranking, ArrayList<Integer> cardList, HashMap<String, ArrayList<Integer>> deckList, ArrayList<String> friendList){
		
		StringableString username = new StringableString(name);
		StringableString password = new StringableString(pass);
		StringableString email = new StringableString(mail);
		StringableInteger gold = new StringableInteger(goldMoney);
		StringableInteger rank = new StringableInteger(ranking);

		StringableArrayList<StringableInteger> cards = new StringableArrayList<StringableInteger>(StringableInteger.class, cardList);
		StringableArrayList<StringableString> friends = new StringableArrayList<StringableString>(StringableString.class, friendList);
		//decks & friends
		
		data.add("username", username);
		data.add("password", password);
		data.add("gold", gold);
		data.add("rank", rank);
		data.add("email", email);
		data.add("cards", cards);
		data.add("friends", friends);
	}
	
	public String getUsername() {
		return data.get("username").toString();
	}    
	
	public String getPassword() {
		return data.get("password").toString();
	}
	
	public int getGold() {
		return ((StringableInteger) data.get("gold")).getInt();
	}
	
	public int getRank() {
		return ((StringableInteger) data.get("rank")).getInt();
	}
	
	public String getEmail() {
		return data.get("email").toString();
	}
	public ArrayList<Integer> getCards(){
		return (ArrayList<Integer>) data.get("cards").getMirror();
	}
	
	public ArrayList<String> getFriends(){
		return (ArrayList<String>) data.get("friends").getMirror();
	}
}
