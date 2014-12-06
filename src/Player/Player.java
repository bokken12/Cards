package Player;

import java.util.ArrayList;

import cards.Card;

public class Player {
	String email;
	String username;
	String password;
	ArrayList<Card> cardCollection;
	public Player(String inputEmail, String inputUsername, String inputPassword){
		
	}
	public String getEmail(){
		return email;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public ArrayList<Card> getCards(){
		return cardCollection;
	}
	public String toString(){
		return "";
	}
}
