package messaging;

import java.util.ArrayList;
import java.util.HashMap;

import player.Player;

public class LoginAcceptedMessage extends Message {

	public LoginAcceptedMessage() {
		
	}
	
	public LoginAcceptedMessage(String name, String pass, String mail, int goldMoney, int ranking, ArrayList<Integer> cardList, HashMap<String, ArrayList<Integer>> deckList, ArrayList<String> friendList){
		super();
		StringableString username = new StringableString(name);
		StringableString password = new StringableString(pass);
		StringableString email = new StringableString(mail);
		StringableInteger gold = new StringableInteger(goldMoney);
		StringableInteger rank = new StringableInteger(ranking);

		StringableArrayList<StringableInteger> cards = new StringableArrayList<StringableInteger>(StringableInteger.class, cardList);
		StringableArrayList<StringableString> friends = new StringableArrayList<StringableString>(StringableString.class, friendList);
		//StringableHashMap...
		
		data.add("username", username);
		data.add("password", password);
		data.add("gold", gold);
		data.add("rank", rank);
		data.add("email", email);
		data.add("cards", cards);
		data.add("friends", friends);
	}
	
	public LoginAcceptedMessage(Player player) {
		super();
		Player p = player;
		
		data.add("player", p);
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
		return (ArrayList<Integer>) getMirror(data.get("cards"));
	}
	
	public ArrayList<String> getFriends(){
		return (ArrayList<String>) getMirror(data.get("friends"));
	}
	
	public Player getPlayer() {
		return ((Player) data.get("player"));
	}
	
	public HashMap<String, ArrayList<Integer>> getDecks() {
		return (HashMap<String, ArrayList<Integer>>) data.get("decks");
	}
}
