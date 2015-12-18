package clientStuff;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.JLabel;

import cards.Card;
import cards.CreatureCard;
import cards.InPlayCreature;
import events.CreaturePlayedEvent;
import events.TurnStartedEvent;
import Player.GamePlayer;
import Player.SimplePlayerProfile;

public class Networker
{
    public SimplePlayerProfile autoMatch() {

        output.println("--Playing " + player.getRank() + " " + player.getUsername());
        output.flush();

        System.out.println("Automatching");

        return null;
    }
    public void handleMessage(String m) {
        if(m.startsWith("--match")) {
            match = new SimplePlayerProfile(m.substring(7, m.indexOf(",")), Integer.parseInt(m.substring(m.indexOf(",") + 1, m.length() - 2)));
            int turn = Integer.parseInt(m.substring(m.length() - 1));
            if(turn == 1) {
                startTurn = true;
                this.turn = true;
                you = new GamePlayer(1);
                turnNum++;

            } else {
                startTurn = false;
                this.turn = false;
            }
            System.out.println("found a match!");
            matchScreen();
        } else if(m.startsWith("--wait")) {
            System.out.println("Waiting...");
            wait = new JLabel("Finding a match...");
            add(Box.createHorizontalGlue());
            add(wait);
            add(Box.createHorizontalGlue());
            revalidate();
        } else if(m.startsWith("--block")) {
            System.out.println("Handling Blocking");
            StringTokenizer st = new StringTokenizer(m, "|");
            ArrayList<InPlayCreature> notBlocked = (ArrayList<InPlayCreature>) attacking.clone();
            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                Integer attacker = null;
                Integer blocker = null;
                for(int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if(Character.isDigit(c)) {
                        if(attacker == null) {
                            attacker = Integer.parseInt(Character.toString(c)); 
                            notBlocked.remove(myCreatures.get(attacker));
                        } else {
                            blocker = Integer.parseInt(Character.toString(c));
                            fightCreatures(attacker, blocker);
                        }
                    }
                    
                
                }
            }
            
            for(InPlayCreature c : notBlocked) {
                enemyHealth = enemyHealth - c.getPower();
            }
            System.out.println("Enemy health is " + enemyHealth);
            attacking.clear();
            repaint();
            turn = true;

        } else if(m.startsWith("--turn")) {
            turn = true;
            turnNum++;
            System.out.println("My Turn! :)");
            drawCard();
            bus.callEvent(new TurnStartedEvent(you));
            repaint();
            for(int i = 0; i < arrivalCreatures.size(); i=0) {
                bus.callEvent(new CreaturePlayedEvent(arrivalCreatures.get(i), lanes.indexOf(arrivalLanes.get(i))));
            }

        } else if(m.startsWith("--myBoard")) {

            try {
                Card c;
//              if(m.charAt(11) == '|') {
//                  c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 11)));
//              } else {
//                  c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 12)));
//              }
                
                c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, m.indexOf("|"))));
                
                int l = Integer.parseInt(m.substring(m.indexOf("|") + 1));
                if(l == Constants.LANE_1) {
                    InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane1);
                    enemyCreatures.add(ic);
                    cardsInPlay.add(ic);
                    lane1.addEnemy(ic);
                } else if(l == Constants.LANE_2) {
                    InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane2);
                    enemyCreatures.add(ic);
                    cardsInPlay.add(ic);
                    lane2.addEnemy(ic);
                } else if(l == Constants.LANE_3) { 
                    InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane3);
                    enemyCreatures.add(ic);
                    cardsInPlay.add(ic);
                    lane3.addEnemy(ic);
                }
                repaint();

            } catch(Exception e) {
                e.printStackTrace();
                if(e instanceof NumberFormatException) {
                    System.out.println("NumberFormatException");
                }
                if(e instanceof IndexOutOfBoundsException) {
                    System.out.println("IndexOutOfBoundsException");
                }
                System.out.println("There was an exception");
            }
        } else if(m.startsWith("--attack")) {
            System.out.println("Got attack");
            blocking = true;
            String b = m.substring(10, m.length() - 1);
            StringTokenizer t = new StringTokenizer(b, ",");
            while(t.hasMoreTokens()) {
                int i = Integer.parseInt(t.nextToken());
                InPlayCreature c = enemyCreatures.get(i);
                enemyCreatures.get(enemyCreatures.indexOf(c)).setRed(true);
                attackingEnemys.add(c);
                attackingEnemyNums.add(i);
            }
            System.out.println("Attacking enemy numbers are " + attackingEnemyNums);
            System.out.println("Attacking enemys are " + attackingEnemys);
        }
    }
}
