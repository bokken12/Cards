package clientStuff;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import uselessSubclasses.Lane;
import Player.GamePlayer;
import abilities.Ability;
import abilities.AbilityRunnable;
import cards.Card;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import cards.SpellCard;
import events.AbilityEvent;
import events.EventBus;
import events.GameEvent;
import events.TargetedSpellPlayedEvent;
import events.TurnEndedEvent;
import events.TurnStartedEvent;
import events.UntargetedSpellPlayedEvent;

public class Controller implements MouseListener, KeyListener, ActionListener, Constants
{
    private GameState gs;
    Display display;
    
    public Controller(GameState gs, Display d) {
    	this.gs = gs;
    	display = d;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {

        System.out.println("Got an event!");
        //TODO use action commands instead of source
        if (e.getSource().equals(play))
        {
            System.out.println(":()");
            playMenu();

        }
        else if (e.getSource().equals(cards))
        {
            CardsMenu();

        }
        else if (e.getSource().equals(settings))
        {

        }
        else if (e.getSource().equals(decklist))
        {

        }
        else if (deckButtons.contains(e.getSource()))
        {
            System.out.println("Got Deck Button Click");
            int[] theDeck = gs.getPlayer().getDecks().get(
                    ((JButton) e.getSource()).getText());
            ArrayList<Integer> realDeck = new ArrayList<Integer>();
            Random rand = new Random();
            for (int i = 0; i < theDeck.length; i++)
            {
                int card = rand.nextInt(theDeck.length);
                if (theDeck[card] != -1)
                {
                    int a = theDeck[card];
                    theDeck[card] = -1;
                    realDeck.add(a);
                }
                else
                {
                    i--;
                }
            }
            gs.setDeck(realDeck);
            System.out.println("-------deck is: " + realDeck.toString());
            if (gs.isStartTurn() == true)
            {
                gs.setOpponent(new GamePlayer(2));
                gs.setYou(new GamePlayer(1));
            }
            else
            {
                gs.setOpponent(new GamePlayer(1));
                gs.setYou(new GamePlayer(2));
            }

            gs.setClear(true);
            
            display.initializeGameboard();
            
            // add(handPanel);
            int x;
            int y;
            for (Integer i = 0; i < 5; i++)
            {
                Card ca = gs.getCardsData().getCardFromID(gs.getDeck().get(i));
                x = i * 120 + 50;
                y = 600;
                HandCard c = new HandCard(x, y, x + 120, y + 170, ca, i);
                gs.getHandCards().add(c);
            }
            for (int a = 0; a < 5; a++)
            {
                gs.getDeck().remove(0);
            }
            

            // Ability playCards = new Ability("Play Creatures",
            // "puts the creatures into play", TurnStartedEvent.class, new
            // AbilityRunnable() {
            // @Override
            // public void run(GameEvent event) {
            // System.out.println("Trying to put in creature");
            // TurnStartedEvent e = (TurnStartedEvent) event;
            // for(int i = 0; i < arrivalCreatures.size(); i++) {
            // if (arrivalLanes.get(i) == null) {
            // System.out.println("actionPerformed: lane is null for " +
            // arrivalCreatures.get(i) + "!");
            // System.out.println("arrivalCreatures is " + arrivalCreatures);
            // }
            // InPlayCreature c = new InPlayCreature(arrivalCreatures.get(i),
            // arrivalLanes.get(i));
            // addCreature(c);
            // if(c.getLane().equals(lane1)) {
            // lane1.addCard(c);
            // }
            // if(c.getLane().equals(lane2)) {
            // lane2.addCard(c);
            // }
            // if(c.getLane().equals(lane3)) {
            // lane3.addCard(c);
            // }
            // arrivalLanes.remove(i);
            // arrivalCreatures.remove(i);
            // }
            // }
            // });
            //
            // playCards.RegisterListeners();
            Ability addMana = new Ability("Add Mana", "adds to your mana",
                    TurnStartedEvent.class, new AbilityRunnable()
            {
                @Override
                public void run(GameEvent event)
                {
                    System.out.println("Updating mana");
                    TurnStartedEvent e = (TurnStartedEvent) event;

                    if (isFib(gs.getTurnNum()))
                    {
                        gs.setMaxMana(gs.getMaxMana() + 1);
                    }
                    gs.setMana(gs.getMaxMana());
                }
            });

            addMana.RegisterListeners();

        }
        else if (e.getSource().equals(endTurn))
        {
            if (gs.isTurn())
            {
                System.out.println("Ending Turn?");
                gs.setTurn(false);
                gs.getBus().callEvent(new TurnEndedEvent(gs.getYou()));
                //TODO eliminate networking I think
                output.println("--turn");
                output.flush();
            }
        }
        else if (e.getSource().equals(attack))
        {
            // System.out.println("Attacking with " + attacking.toString());
            // output.println("--attack " + attacking.toString());
            // output.flush();
            // attacking.clear();
            // turn = false;
            String s = "[";
            for (int i = 0; i < gs.getMyCreatures().size(); i++)
            {
                if (gs.getAttacking().contains(gs.getMyCreatures().get(i)))
                {
                    s = s + i;
                    if (i < gs.getMyCreatures().size() - 1)
                    {
                        s = s + ",";
                    }
                    gs.getMyCreatures().get(i).setGreen(false);
                }
            }
            s = s + "]";
            System.out.println("Attacking with " + s);
            output.println("--attack " + s);
            output.flush();
            gs.setTurn(false);

        }
        else if (e.getSource().equals(block))
        {
            System.out.println(gs.getBlockers());
            // System.out.println(blocking);

            String s = "";
            for (int i = 0; i < gs.getMyCreatures().size(); i++)
            {
                if (gs.getBlockers().containsKey(gs.getMyCreatures().get(i)))
                {
                    s = s + i + "," + gs.getAttackingEnemyNums().get(i) + "|";
                    gs.fightCreatures(i, gs.getAttackingEnemyNums().get(i));
                }
            }

            for (int i = 0; i < gs.getAttackingEnemys().size(); i++)
            {
                gs.setHealth(gs.getHealth() - gs.getAttackingEnemys().get(i).getPower());
            }
            System.out.println("Your health is " + gs.getHealth());

            // s = s + "]";
            //TODO we should discuss whether controller is allowed to network
            System.out.println("Blocking with " + s);
            output.println("--block " + s);
            output.flush();
            gs.setTurn(false);

            gs.getBlockers().clear();
            gs.setBlocker(null);
            gs.setBlocking(false);

            for (int i = 0; i < gs.getEnemyCreatures().size(); i++)
            {
                gs.getEnemyCreatures().get(i).setRed(false);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point a = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(a, game);
        //TODO its much cleaner now than it was before, but its possible it should be somewhere else like in an event
        for(Lane l: gs.getLanes()){
            InPlayCreature c = l.getClick(a);
            if (c != null && gs.getMyCreatures().contains(c))
            {
                gs.getAttacking().add(c);
                gs.setSelectedCard(c);
                System.out.println("adding");

                int i = gs.getMyCreatures().indexOf(c);
                int b = c.getLane().getCreatures().indexOf(c);
                c.setGreen(true);
                gs.getMyCreatures().set(i, c);
                c.getLane().getCreatures().set(b, c);
                break;
            } 
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point a = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(a, game);
        System.out.println("Got a click at " + a);


        for(int i = 0; i < gs.getHandCards().size(); i++) {
            if(gs.getHandCards().get(i).containsPoint(a)) {
                if(gs.isTurn() || gs.isBlocking()) {
                    cd = new HandCardDragger();
                    System.out.println("Clicking a Hand Card! :D");
                    gs.setSelectedHandCard(gs.getHandCards().get(i));
                    cd.execute();
                }
            }
        }

        if(gs.isBlocking()) {
            for(Lane l: gs.getLanes()){
                InPlayCreature c = l.getClick(a);
                if(c != null) {
                    bd = new BlockCardDragger();
                    gs.setBlocker(c);
                    bd.execute();
                    break;
                }
            }
        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {

        Point a = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(a, game);

        if(!(cd.isStopped())) {
            if(gs.getSelectedHandCard().getCard() instanceof CreatureCard/* && mana >= selectedHandCard.getCard().getCost()*/) {
                System.out.println("Playing Creature: " + selectedHandCard);
                for(Lane l: gs.getLanes()){
                    if(l.containsPoint(gs.getSelectedCardPoint())) {
                        gs.arrivalHelper(l);
                        break;
                    }
                }
            } else if(gs.getSelectedHandCard().getCard() instanceof SpellCard) {
                SpellCard sc = (SpellCard) gs.getSelectedHandCard().getCard();
                if(sc.hasTarget()) {
                    EventBus.getInstance().callEvent(new TargetedSpellPlayedEvent(sc, this, gs.getSelectedCard()));
                } else {
                    EventBus.getInstance().callEvent(new UntargetedSpellPlayedEvent(sc, this));
                }
                gs.getHandCards().remove(gs.getSelectedHandCard());
            }
        }
        cd.stop();
        gs.setSelectedHandCard(null);
        if(!(bd.isStopped())) {

            System.out.println("isStopped? " + bd.isStopped() + "Blocker lane is" + gs.getBlocker().getLane() + 
                    "Enemy2 is " + gs.getLane(LANE_3).getClick(a));
            for(Lane l: gs.getLanes()){
                if(gs.getAttackingEnemys().contains(l.getClick(a)) && gs.getBlocker().getLane() == l) {
                    gs.getBlockers().put(gs.getBlocker(), l.getClick(a));
                    System.out.println("Blocking with creature: " + gs.getBlocker());
                }
            }
            System.out.println(gs.getBlockers());

            //          for(Lane l : lanes) {
            //              if(attackingEnemys.contains(l.getClick(a)) && blocker.getLane() == l) {
            //                  blockers.put(blocker, l.getClick(a));
            //              }
            //          }
        }
        bd.stop();
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override 
    public void keyTyped(KeyEvent e) {
        System.out.println("Meanie!" + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_SPACE && gs.getSelectedCard() != null) {
            System.out.println("Calling AbilityEvent");
            gs.getBus().callEvent(new AbilityEvent(gs.getSelectedCard()));
            gs.getAttacking().remove(gs.getSelectedCard());

        }
    }
    public boolean isFib(int i){
        //TODO implement me
        return false;
    }
}
