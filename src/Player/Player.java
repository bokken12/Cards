package Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cards.Card;

public class Player {
	String email;
	String username;
	String password;
	ArrayList<Integer> cardCollection;
	HashMap<String, int[]> decks = new HashMap<String, int[]>();
	int rank;

	ArrayList<String> friends = new ArrayList<String>();
	int gold;
	public void addCardToCollection(int card){
		cardCollection.add(card);
	}
	@Override
	public String toString() {

		Set<String> a = decks.keySet();
		Iterator<String> y = a.iterator();
		String s = "";
		for(int i = 0; i < a.size(); i++) {
			if(y.hasNext()) {
			String buh = y.next();
			s = s + buh + "|";   
			s = s + Arrays.toString(decks.get(buh)) + "|";
			}
		
		}


		return "Player [email=" + email + ", username=" + username
				+ ", password=" + password + ", cardCollection="
				+ cardCollection + ", decks=" + s
				+ ", rank=" + rank + ", friends=" + friends
				+ ", gold=" + gold + "]";
	}
	public void addFriend(String friend){
		friends.add(friend);
	}
	public void removeFriend(String friend){
		friends.remove(friend);
	}
	public void removeCardFromCollection(Card card){
		cardCollection.remove(card);
	}
	public void setDeck(String name, int[] deck){
		decks.remove(name);
		decks.put(name, deck);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Integer> getCardCollection() {
		return cardCollection;
	}
	public void setCardCollection(ArrayList<Integer> cardCollection) {
		this.cardCollection = cardCollection;
	}
	public HashMap<String, int[]> getDecks() {
		return decks;
	}
	public void setDecks(HashMap<String, int[]> decks) {
		this.decks = decks;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public ArrayList<String> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public Player(String email, String username, String password,
			ArrayList<Integer> cardCollection, HashMap<String, int[]> decks, int rank,
			ArrayList<String> friends, int gold) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.cardCollection = cardCollection;
		this.decks = decks;
		this.rank = rank;
		this.friends = friends;
		this.gold = gold;
	}
}
