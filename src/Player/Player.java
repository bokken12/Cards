package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import messaging.Stringable;
import messaging.StringableArrayList;
import messaging.StringableHashMap;
import messaging.StringableInteger;
import messaging.StringableString;
import messaging.Stringer;
import cards.Card;

public class Player implements Stringable<Player>{
    private static String seperator = "|||";//"I'm such a great player, aren't I great. I must be amazing. Wow, I'm a wonderful player, don't you think that my username is fabulous?";
    StringableString email;
    StringableString username;
    StringableString password;
    StringableArrayList<StringableInteger> cardCollection;
    StringableHashMap<StringableString, StringableArrayList<StringableInteger>> decks = new StringableHashMap<StringableString, StringableArrayList<StringableInteger>>();
    StringableInteger rank;
    StringableArrayList<StringableString> friends = new StringableArrayList<StringableString>();
    StringableInteger gold;
    public void addCardToCollection(int card){
        cardCollection.add(new StringableInteger(card));
    }
    @Override
    public String toString() {

        /*Set<String> a = decks.keySet();
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
                + ", gold=" + gold + "]";*/
        /*String str = "";
        str += Stringer.printStringable(new StringableString(email));
        str += ", ";
        str += Stringer.printStringable(new StringableString(username));
        str += ", ";
        str += Stringer.printStringable(new StringableString(password));
        str += ", ";
        //StringableArrayList<StringableInteger> stringableCardCollection = new StringableArrayList<StringableInteger>();
        //stringableCardCollection.fromMirror(cardCollection);
        str += Stringer.printStringable(new StringableArrayList<StringableInteger>(StringableInteger.class, cardCollection));
        str += ", ";*/
        //StringableHashMap<StringableString, StringableArrayList<StringableInteger>> stringableDecks = new StringableHashMap<StringableString, StringableArrayList<StringableInteger>>(decks);
        String str = "";
        str += Stringer.printStringable(email);
        str += seperator;
        str += Stringer.printStringable(username);
        str += seperator;
        str += Stringer.printStringable(password);
        str += seperator;
        str += Stringer.printStringable(cardCollection);
        str += seperator;
        str += Stringer.printStringable(decks);
        str += seperator;
        str += Stringer.printStringable(rank);
        str += seperator;
        str += Stringer.printStringable(friends);
        str += seperator;
        str += Stringer.printStringable(gold);
        return str;
    }
    public Player(String str){
        this.fromString(str);
    }
    public void addFriend(String friend){
        friends.add(new StringableString(friend));
    }
    public void removeFriend(String friend){
        friends.remove(new StringableString(friend));
    }
    public void setDeck(String name, ArrayList<Integer> deck){
        //decks.remove(name);
        decks.put(new StringableString(name), new StringableArrayList<StringableInteger>(StringableInteger.class, deck));
    }
    public String getEmail() {
        return email.toString();
    }
    public void setEmail(String email) {
        this.email = new StringableString(email);
    }
    public String getUsername() {
        return username.toString();
    }
    public void setUsername(String username) {
        this.username = new StringableString(username);
    }
    public String getPassword() {
        return password.toString();
    }
    public void setPassword(String password) {
        this.password = new StringableString(password);
    }
    public ArrayList<Integer> getCardCollection() {
        return cardCollection.getMirror();
    }
    public void setCardCollection(ArrayList<Integer> cardCollection) {
        this.cardCollection = new StringableArrayList<StringableInteger>(StringableInteger.class, cardCollection);
    }
    public HashMap<String, ArrayList<Integer>> getDecks() {
        return decks.getMirror();
    }
    public void setDecks(HashMap<String, ArrayList<Integer>> decks){
        for(String key: decks.keySet()){
            setDeck(key, decks.get(key));
        }
    }
    public int getRank() {
        return rank.getInt();
    }
    public void setRank(int rank) {
        this.rank = new StringableInteger(rank);
    }
    public ArrayList<String> getFriends() {
        return friends.getMirror();
    }
    public void setFriends(ArrayList<String> friends) {
        this.friends = new StringableArrayList<StringableString>(StringableString.class, friends);
    }
    public int getGold() {
        return gold.getInt();
    }
    public void setGold(int gold) {
        this.gold = new StringableInteger(gold);
    }
    public Player(String email, String username, String password,
            ArrayList<Integer> cardCollection, HashMap<String, ArrayList<Integer>> decks, int rank,
            ArrayList<String> friends, int gold) {
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setCardCollection(cardCollection);
        setDecks(decks);
        setRank(rank);
        setFriends(friends);
        setGold(gold);
    }
    @Override
    public void fromString(String str)
    {
        StringTokenizer st = new StringTokenizer(str, seperator);
        email = (StringableString) Stringer.fromString(st.nextToken());
        username = (StringableString) Stringer.fromString(st.nextToken());
        password = (StringableString) Stringer.fromString(st.nextToken());
        cardCollection = (StringableArrayList<StringableInteger>) Stringer.fromString(st.nextToken());
        decks = (StringableHashMap<StringableString, StringableArrayList<StringableInteger>>) Stringer.fromString(st.nextToken());
        rank = (StringableInteger) Stringer.fromString(st.nextToken());
        friends = (StringableArrayList<StringableString>) Stringer.fromString(st.nextToken());
        gold = (StringableInteger) Stringer.fromString(st.nextToken());
    }
    @Override
    public Player getMirror()
    {
        return this;
    }
    @Override
    public void fromMirror(Player e)
    {
        setEmail(e.getEmail());
        setUsername(e.getUsername());
        setPassword(e.getPassword());
        setCardCollection(e.getCardCollection());
        setDecks(e.getDecks());
        setRank(e.getRank());
        setFriends(e.getFriends());
        setGold(e.getGold());
    }
}
