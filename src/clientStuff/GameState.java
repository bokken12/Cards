package clientStuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import clientStuff.Content.BlockCardDragger;
import clientStuff.Content.HandCardDragger;
import Player.GamePlayer;
import Player.SimplePlayerProfile;
import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import events.EventBus;
import uselessSubclasses.Lane;

public class GameState
{
    private boolean startTurn;
    private boolean clear = false;
    private boolean turn;
    private boolean blocking;
    private boolean isMtn1Full = false;
    private boolean isMtn2Full = false;
    private boolean isMtn3Full = false;
    private Integer mana = 0;
    private Integer maxMana = 0;
    private Integer turnNum = 0;
    private int health = 20;
    private int enemyHealth = 20;
    private EventBus bus = EventBus.getInstance();
    private GamePlayer you;
    private GamePlayer opponent;
    private Cards cardsData = new Cards();
    private InPlayCreature selectedCard;
    private ArrayList<InPlayCreature> cardsInPlay = new ArrayList<InPlayCreature>();
    private List<InPlayCreature> myCreatures = Collections
            .synchronizedList(new ArrayList<InPlayCreature>());
    private List<InPlayCreature> enemyCreatures = Collections
            .synchronizedList(new ArrayList<InPlayCreature>());
    private ArrayList<Lane> arrivalLanes = new ArrayList<Lane>();
    private ArrayList<CreatureCard> arrivalCreatures = new ArrayList<CreatureCard>();
    private ArrayList<InPlayCreature> attacking = new ArrayList<InPlayCreature>();
    private ArrayList<InPlayCreature> attackingEnemys = new ArrayList<InPlayCreature>();
    private ArrayList<Integer> attackingEnemyNums = new ArrayList<Integer>();
    private ArrayList<Integer> deck;
    HashMap<InPlayCreature, InPlayCreature> blockers = new HashMap<InPlayCreature, InPlayCreature>();
    ArrayList<HandCard> handCards = new ArrayList<HandCard>();
    HandCard selectedHandCard;
    Point selecCardPoint = new Point();
    HandCardDragger cd = new HandCardDragger();
    Point blockCardPoint = new Point();
    BlockCardDragger bd = new BlockCardDragger();
    InPlayCreature blocker;
    Lane lane1 = new Lane(this, 1);
    Lane lane2 = new Lane(this, 2);
    Lane lane3 = new Lane(this, 3);
    public static ArrayList<Lane> lanes = new ArrayList<Lane>();
    volatile SimplePlayerProfile match;
    
    public Lane[] getLanes()
    {
        return null;
    }

    public Lane getLane(int i)
    {
        return getLanes()[i];
    }

    public List<HandCard> getHandCards()
    {
        return null;
    }
}
