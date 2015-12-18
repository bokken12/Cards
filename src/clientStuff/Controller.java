package clientStuff;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

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

public class Controller
{
    public void actionPerformed(ActionEvent e)
    {

        System.out.println("Got an event!");

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
            int[] theDeck = player.getDecks().get(
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
            deck = realDeck;
            System.out.println("-------deck is: " + deck.toString());
            if (startTurn == true)
            {
                opponent = new GamePlayer(2);
                you = new GamePlayer(1);
            }
            else
            {
                opponent = new GamePlayer(1);
                you = new GamePlayer(2);
            }

            clear = true;
            game.setSize(new Dimension(1200, 800));
            this.removeAll();

            add(field);
            buttons.add(endTurn);
            endTurn.addActionListener(this);
            buttons.add(attack);
            attack.addActionListener(this);
            buttons.add(block);
            block.addActionListener(this);
            buttons.add(manaLabel);
            add(buttons);
            // add(handPanel);
            int x;
            int y;
            for (Integer i = 0; i < 5; i++)
            {
                Card ca = cardsData.getCardFromID(deck.get(i));
                x = i * 120 + 50;
                y = 600;
                HandCard c = new HandCard(x, y, x + 120, y + 170, ca, i);
                handCards.add(c);
            }
            for (int a = 0; a < 5; a++)
            {
                deck.remove(0);
            }
            add(manaLabel);
            this.revalidate();

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

                    if (turnNum == 1 || turnNum == 2 || turnNum == 3
                            || turnNum == 5 || turnNum == 8
                            || turnNum == 13 || turnNum == 21
                            || turnNum == 34)
                    {
                        maxMana++;
                    }
                    mana = maxMana;
                }
            });

            addMana.RegisterListeners();

        }
        else if (e.getSource().equals(endTurn))
        {
            if (turn == true)
            {
                System.out.println("Ending Turn?");
                turn = false;
                bus.callEvent(new TurnEndedEvent(you));
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
            for (int i = 0; i < myCreatures.size(); i++)
            {
                if (attacking.contains(myCreatures.get(i)))
                {
                    s = s + i;
                    if (i < myCreatures.size() - 1)
                    {
                        s = s + ",";
                    }
                    myCreatures.get(i).setGreen(false);
                }
            }
            s = s + "]";
            System.out.println("Attacking with " + s);
            output.println("--attack " + s);
            output.flush();
            turn = false;

        }
        else if (e.getSource().equals(block))
        {
            System.out.println(blockers);
            // System.out.println(blocking);

            String s = "";
            for (int i = 0; i < myCreatures.size(); i++)
            {
                if (blockers.containsKey(myCreatures.get(i)))
                {
                    s = s + i + "," + attackingEnemyNums.get(i) + "|";
                    fightCreatures(i, attackingEnemyNums.get(i));
                }
            }

            for (int i = 0; i < attackingEnemys.size(); i++)
            {
                health = health - attackingEnemys.get(i).getPower();
            }
            System.out.println("Your health is " + health);

            // s = s + "]";
            System.out.println("Blocking with " + s);
            output.println("--block " + s);
            output.flush();
            turn = false;

            blockers.clear();
            blocker = null;
            blocking = false;

            for (int i = 0; i < enemyCreatures.size(); i++)
            {
                enemyCreatures.get(i).setRed(false);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point a = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(a, game);
        repaint();
        InPlayCreature c = lane1.getClick(a);
        if (c != null && myCreatures.contains(c))
        {
            attacking.add(c);
            selectedCard = c;
            System.out.println("adding");

            int i = myCreatures.indexOf(c);
            int b = c.getLane().getCreatures().indexOf(c);
            c.setGreen(true);
            myCreatures.set(i, c);
            c.getLane().getCreatures().set(b, c);

        }
        else
        {
            InPlayCreature cr = lane2.getClick(a);
            if (cr != null && myCreatures.contains(cr))
            {
                attacking.add(cr);
                System.out.println("adding");
                selectedCard = cr;
                int i = myCreatures.indexOf(cr);
                int b = cr.getLane().getCreatures().indexOf(cr);
                cr.setGreen(true);
                myCreatures.set(i, cr);
                cr.getLane().getCreatures().set(b, cr);

            }
            else
            {
                InPlayCreature cre = lane3.getClick(a);
                if (cre != null && myCreatures.contains(cre))
                {
                    attacking.add(cre);
                    System.out.println("adding");
                    selectedCard = cre;
                    int i = myCreatures.indexOf(cre);
                    int b = cre.getLane().getCreatures().indexOf(cre);
                    cre.setGreen(true);
                    myCreatures.set(i, cre);
                    cre.getLane().getCreatures().set(b, cre);
                }
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


        for(int i = 0; i < handCards.size(); i++) {
            if(handCards.get(i).containsPoint(a)) {
                if(turn == true || blocking == true) {
                    cd = new HandCardDragger();
                    System.out.println("Clicking a Hand Card! :D");
                    selectedHandCard = handCards.get(i);
                    cd.execute();
                }
            }
        }

        if(blocking) {
            InPlayCreature c = lane1.getClick(a);
            if(c != null) {
                bd = new BlockCardDragger();
                blocker = c;
                bd.execute();
            } else {
                InPlayCreature cr = lane2.getClick(a);
                if(cr != null) {
                    bd = new BlockCardDragger();
                    blocker = cr;
                    bd.execute();
                } else {
                    InPlayCreature cre = lane3.getClick(a);
                    if(cre != null) {
                        bd = new BlockCardDragger();
                        blocker = cre;
                        bd.execute();
                    }
                }
            }
        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {

        Point a = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(a, game);

        if(!(cd.isStopped())) {
            if(selectedHandCard.getCard() instanceof CreatureCard/* && mana >= selectedHandCard.getCard().getCost()*/) {
                System.out.println("Playing Creature: " + selectedHandCard);
                if(lane1.containsPoint(selecCardPoint)) {
                    /*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane1);;
                    arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane1);
                    arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
                    handCards.remove(selectedHandCard);*/
                    arrivalHelper(lane1);

                } else if(lane2.containsPoint(selecCardPoint)) {
                    /*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane2);
                    arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane2);
                    arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
                    handCards.remove(selectedHandCard);*/
                    arrivalHelper(lane2);

                } else if(lane3.containsPoint(selecCardPoint)) {
                    /*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane3);
                    arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane3);
                    arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
                    handCards.remove(selectedHandCard);*/
                    arrivalHelper(lane3);
                }
                repaint();
            } else if(selectedHandCard.getCard() instanceof SpellCard) {
                SpellCard sc = (SpellCard) selectedHandCard.getCard();
                if(sc.hasTarget()) {
                    EventBus.getInstance().callEvent(new TargetedSpellPlayedEvent(sc, this, selectedCard));
                } else {
                    EventBus.getInstance().callEvent(new UntargetedSpellPlayedEvent(sc, this));
                }
                handCards.remove(selectedHandCard);
            }
        }
        cd.stop();
        selectedHandCard = null;



        if(!(bd.isStopped())) {

            System.out.println("isStopped? " + bd.isStopped() + "Blocker lane is" + blocker.getLane() + 
                    "Enemy2 is " + lane2.getClick(a));

            if(attackingEnemys.contains(lane1.getClick(a)) && blocker.getLane() == lane1) {
                blockers.put(blocker, lane1.getClick(a));
                System.out.println("Blocking with creature: " + blocker + " in lane 1");
            }
            if(attackingEnemys.contains(lane2.getClick(a)) && blocker.getLane() == lane2) {
                blockers.put(blocker, lane2.getClick(a));
                System.out.println("Blocking with creature: " + blocker + " in lane 2");
            }
            if(attackingEnemys.contains(lane3.getClick(a)) && blocker.getLane() == lane3) {
                blockers.put(blocker, lane3.getClick(a));
                System.out.println("Blocking with creature: " + blocker + " in lane 3");
            }
            System.out.println(blockers);

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
        if(e.getKeyCode() == KeyEvent.VK_SPACE && selectedCard != null) {
            System.out.println("Calling AbilityEvent");
            bus.callEvent(new AbilityEvent(selectedCard));
            attacking.remove(selectedCard);
            
        }
    }
}
