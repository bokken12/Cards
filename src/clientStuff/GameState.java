package clientStuff;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import clientStuff.Content.BlockCardDragger;
import clientStuff.Content.HandCardDragger;
import Player.GamePlayer;
import Player.SimplePlayerProfile;
import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import events.DamageEvent;
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
    private HashMap<InPlayCreature, InPlayCreature> blockers = new HashMap<InPlayCreature, InPlayCreature>();
    private ArrayList<HandCard> handCards = new ArrayList<HandCard>();
    private HandCard selectedHandCard;
    private Point selectedCardPoint = new Point();
    private HandCardDragger cd = new HandCardDragger();
    private Point blockCardPoint = new Point();
    private BlockCardDragger bd = new BlockCardDragger();
    private InPlayCreature blocker;
    private volatile SimplePlayerProfile match;
    private Lane[] lanes;

    public GameState()
    {
        Lane lane1 = new Lane(this, 1);
        Lane lane2 = new Lane(this, 2);
        Lane lane3 = new Lane(this, 3);
        lanes = new Lane[]
        { lane1, lane2, lane3 };
    }

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

    public void fightCreatures(int c1, int c2)
    {
        System.out.println("Creatures are fighting");

        int a1 = myCreatures.get(c1).getPower();
        int a2 = enemyCreatures.get(c2).getPower();
        // System.out.println("My, enemy power is " + a1 + ", " + a2);
        // System.out.println("My health was " +
        // myCreatures.get(c1).getHealth());
        bus.callEvent(new DamageEvent(a2, myCreatures.get(c1)));
        bus.callEvent(new DamageEvent(a1, enemyCreatures.get(c2)));
        repaint();
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
        repaint();
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

    public static ArrayList<InPlayCreature> getCards()
    {
        return cardsInPlay;
    }

    public static ArrayList<InPlayCreature> getCardsOfType(String type)
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
        if (n.getLane().equals(lane1))
            lane = 1;
        if (n.getLane().equals(lane2))
            lane = 2;
        if (n.getLane().equals(lane3))
            lane = 3;

        String send = "--myBoard "
                + cardsData.getCollection().indexOf(n.getCard()) + "|" + lane;

        isMtn1Full = false;
        isMtn2Full = false;

        // should send something like --myBoard 1|2
        System.out.println("Sending " + send);
        output.println(send);
        output.flush();
    }
}
