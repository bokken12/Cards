package clientStuff;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import player.GamePlayer;
import player.Player;
import player.SimplePlayerProfile;
import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import events.DamageEvent;
import events.EventBus;
import uselessSubclasses.Lane;

public class BoardState implements Constants
{
    private boolean startTurn;
    private boolean clear = false;
    private boolean turn;
    private boolean blocking;
    private boolean isMtn1Full = false;
    private boolean isMtn2Full = false;
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
    private HashMap<InPlayCreature, InPlayCreature> blockers = new HashMap<InPlayCreature, InPlayCreature>();
    private ArrayList<HandCard> handCards = new ArrayList<HandCard>();
    private HandCard selectedHandCard;
    private Point selectedCardPoint = new Point();
    private Point blockCardPoint = new Point();
    private InPlayCreature blocker;
    private volatile SimplePlayerProfile match;
    private Lane[] lanes;
    private Player player;

    public BoardState()
    {
        Lane lane1 = new Lane(this, 1);
        Lane lane2 = new Lane(this, 2);
        Lane lane3 = new Lane(this, 3);
        lanes = new Lane[]
        { lane1, lane2, lane3 };
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public boolean isStartTurn()
    {
        return startTurn;
    }

    public void setStartTurn(boolean startTurn)
    {
        this.startTurn = startTurn;
    }

    public boolean isClear()
    {
        return clear;
    }

    public void setClear(boolean clear)
    {
        this.clear = clear;
    }

    public boolean isTurn()
    {
        return turn;
    }

    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }

    public boolean isBlocking()
    {
        return blocking;
    }

    public void setBlocking(boolean blocking)
    {
        this.blocking = blocking;
    }

    public boolean isMtn1Full()
    {
        return isMtn1Full;
    }

    public void setMtn1Full(boolean isMtn1Full)
    {
        this.isMtn1Full = isMtn1Full;
    }

    public boolean isMtn2Full()
    {
        return isMtn2Full;
    }

    public void setMtn2Full(boolean isMtn2Full)
    {
        this.isMtn2Full = isMtn2Full;
    }

    public Integer getMana()
    {
        return mana;
    }

    public void setMana(Integer mana)
    {
        this.mana = mana;
    }

    public Integer getMaxMana()
    {
        return maxMana;
    }

    public void setMaxMana(Integer maxMana)
    {
        this.maxMana = maxMana;
    }

    public Integer getTurnNum()
    {
        return turnNum;
    }

    public void setTurnNum(Integer turnNum)
    {
        this.turnNum = turnNum;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getEnemyHealth()
    {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth)
    {
        this.enemyHealth = enemyHealth;
    }

    public EventBus getBus()
    {
        return bus;
    }

    public void setBus(EventBus bus)
    {
        this.bus = bus;
    }

    public GamePlayer getYou()
    {
        return you;
    }

    public void setYou(GamePlayer you)
    {
        this.you = you;
    }

    public GamePlayer getOpponent()
    {
        return opponent;
    }

    public void setOpponent(GamePlayer opponent)
    {
        this.opponent = opponent;
    }

    public Cards getCardsData()
    {
        return cardsData;
    }

    public void setCardsData(Cards cardsData)
    {
        this.cardsData = cardsData;
    }

    public InPlayCreature getSelectedCard()
    {
        return selectedCard;
    }

    public void setSelectedCard(InPlayCreature selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    public ArrayList<InPlayCreature> getCardsInPlay()
    {
        return cardsInPlay;
    }

    public void setCardsInPlay(ArrayList<InPlayCreature> cardsInPlay)
    {
        this.cardsInPlay = cardsInPlay;
    }

    public List<InPlayCreature> getMyCreatures()
    {
        return myCreatures;
    }

    public void setMyCreatures(List<InPlayCreature> myCreatures)
    {
        this.myCreatures = myCreatures;
    }

    public List<InPlayCreature> getEnemyCreatures()
    {
        return enemyCreatures;
    }

    public void setEnemyCreatures(List<InPlayCreature> enemyCreatures)
    {
        this.enemyCreatures = enemyCreatures;
    }

    public ArrayList<Lane> getArrivalLanes()
    {
        return arrivalLanes;
    }

    public void setArrivalLanes(ArrayList<Lane> arrivalLanes)
    {
        this.arrivalLanes = arrivalLanes;
    }

    public ArrayList<CreatureCard> getArrivalCreatures()
    {
        return arrivalCreatures;
    }

    public void setArrivalCreatures(ArrayList<CreatureCard> arrivalCreatures)
    {
        this.arrivalCreatures = arrivalCreatures;
    }

    public ArrayList<InPlayCreature> getAttacking()
    {
        return attacking;
    }

    public void setAttacking(ArrayList<InPlayCreature> attacking)
    {
        this.attacking = attacking;
    }

    public ArrayList<InPlayCreature> getAttackingEnemys()
    {
        return attackingEnemys;
    }

    public void setAttackingEnemys(ArrayList<InPlayCreature> attackingEnemys)
    {
        this.attackingEnemys = attackingEnemys;
    }

    public ArrayList<Integer> getAttackingEnemyNums()
    {
        return attackingEnemyNums;
    }

    public void setAttackingEnemyNums(ArrayList<Integer> attackingEnemyNums)
    {
        this.attackingEnemyNums = attackingEnemyNums;
    }

    public ArrayList<Integer> getDeck()
    {
        return deck;
    }

    public void setDeck(ArrayList<Integer> deck)
    {
        this.deck = deck;
    }

    public HashMap<InPlayCreature, InPlayCreature> getBlockers()
    {
        return blockers;
    }

    public void setBlockers(HashMap<InPlayCreature, InPlayCreature> blockers)
    {
        this.blockers = blockers;
    }

    public ArrayList<HandCard> getHandCards()
    {
        return handCards;
    }

    public void setHandCards(ArrayList<HandCard> handCards)
    {
        this.handCards = handCards;
    }

    public HandCard getSelectedHandCard()
    {
        return selectedHandCard;
    }

    public void setSelectedHandCard(HandCard selectedHandCard)
    {
        this.selectedHandCard = selectedHandCard;
    }

    public Point getSelectedCardPoint()
    {
        return selectedCardPoint;
    }

    public void setSelectedCardPoint(Point selectedCardPoint)
    {
        this.selectedCardPoint = selectedCardPoint;
    }

    public Point getBlockCardPoint()
    {
        return blockCardPoint;
    }

    public void setBlockCardPoint(Point blockCardPoint)
    {
        this.blockCardPoint = blockCardPoint;
    }

    public InPlayCreature getBlocker()
    {
        return blocker;
    }

    public void setBlocker(InPlayCreature blocker)
    {
        this.blocker = blocker;
    }

    public SimplePlayerProfile getMatch()
    {
        return match;
    }

    public void setMatch(SimplePlayerProfile match)
    {
        this.match = match;
    }

    public Lane[] getLanes()
    {
        return lanes;
    }

    public void setLanes(Lane[] lanes)
    {
        this.lanes = lanes;
    }

    public Lane getLane(int i)
    {
        return getLanes()[i];
    }

    public void fightCreatures(int c1, int c2)
    {
        System.out.println("Creatures are fighting");

        int a1 = myCreatures.get(c1).getPower();
        int a2 = enemyCreatures.get(c2).getPower();
        // System.out.println("My, enemy power is " + a1 + ", " + a2);
        // System.out.println("My health was " +
        // myCreatures.get(c1).getHealth());
        bus.callEvent(new DamageEvent(this, a2, myCreatures.get(c1)));
        bus.callEvent(new DamageEvent(this, a1, enemyCreatures.get(c2)));
        // System.out.println("My Creature's health is " +
        // myCreatures.get(c1).getHealth());
        if (myCreatures.get(c1).getHealth() <= 0)
        {
            myCreatures.get(c1).getLane().getCreatures()
                    .remove(myCreatures.get(c1));
            myCreatures.remove(c1);

        }
        if (enemyCreatures.get(c2).getHealth() <= 0)
        {
            enemyCreatures.get(c2).getLane().getEnemyCreatures()
                    .remove(enemyCreatures.get(c2));
            enemyCreatures.remove(c2);
        }
    }

    public int drawCard()
    {
        if (!(deck.isEmpty()))
        {
            int i = deck.get(0);
            deck.remove(0);
            int x = handCards.get(handCards.size() - 1).getEndX() + 50;
            HandCard draw = new HandCard(x + 50, 600, x + 170, 770,
                    cardsData.getCardFromID(i), handCards.size());
            handCards.add(draw);
            return i;
        }
        else
        {
            return 1;
        }
        // call drawCardEvent
    }

    public ArrayList<InPlayCreature> getCardsOfType(String type)
    {
        ArrayList<InPlayCreature> ret = new ArrayList<InPlayCreature>();
        for (int i = 0; i < cardsInPlay.size(); i++)
        {
            if (cardsInPlay.get(i).getType().equals(type))
            {
                ret.add(cardsInPlay.get(i));
            }
        }
        return ret;
    }

    public void arrivalHelper(Lane lane)
    {
        arrivalLanes.add(lane);
        arrivalCreatures.add((CreatureCard) selectedHandCard.getCard());
        handCards.remove(selectedHandCard);

    }

    public void addCreature(InPlayCreature n)
    {
        cardsInPlay.add(n);
        myCreatures.add(n);
        // arrivalLanes.remove(arrivalCreatures.indexOf(n));
        // arrivalCreatures.remove(n);
        n.getCard().registerListeners();
        int lane = 1;
        if (n == null)
        {
            System.out.println("n is null in addCreature");
        }
        else if (n.getLane() == null)
        {
            System.out.println("n.getLane() is null in addCreature");
        }
        if (n.getLane().equals(lanes[LANE_1]))
            lane = 1;
        if (n.getLane().equals(lanes[LANE_2]))
            lane = 2;
        if (n.getLane().equals(lanes[LANE_3]))
            lane = 3;

        String send = "--myBoard "
                + cardsData.getCollection().indexOf(n.getCard()) + "|" + lane;

        isMtn1Full = false;
        isMtn2Full = false;

        // should send something like --myBoard 1|2
        System.out.println("Sending " + send);
        // TODO communicate with networker to send data
        // output.println(send);
        // output.flush();
    }
}
