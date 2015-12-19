package clientStuff;

import java.io.PrintWriter;
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
	
	GameState gs;
	Display d;
	PrintWriter output;
	
    public SimplePlayerProfile autoMatch() {

        output.println("--Playing " + gs.getPlayer().getRank() + " " + gs.getPlayer().getUsername());
        output.flush();

        System.out.println("Automatching");

        return null;
    }
    public void handleMessage(String m) {
        if(m.startsWith("--match")) {
            gs.setMatch(new SimplePlayerProfile(m.substring(7, m.indexOf(",")), Integer.parseInt(m.substring(m.indexOf(",") + 1, m.length() - 2))));
            int turn = Integer.parseInt(m.substring(m.length() - 1));
            if(turn == 1) {
                gs.setStartTurn(true);
                gs.setTurn(true);
                gs.setYou(new GamePlayer(1));
                gs.setTurnNum(gs.getTurnNum() + 1);

            } else {
                gs.setStartTurn(false);
                gs.setTurn(false);
            }
            System.out.println("found a match!");
            d.matchScreen();
        } else if(m.startsWith("--wait")) {
            System.out.println("Waiting...");
            d.setWait(new JLabel("Finding a match..."));
            d.add(Box.createHorizontalGlue());
            d.add(d.getWait());
            d.add(Box.createHorizontalGlue());
            d.revalidate();
        } else if(m.startsWith("--block")) {
            System.out.println("Handling Blocking");
            StringTokenizer st = new StringTokenizer(m, "|");
            ArrayList<InPlayCreature> notBlocked = (ArrayList<InPlayCreature>) gs.getAttacking().clone();
            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                Integer attacker = null;
                Integer blocker = null;
                for(int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if(Character.isDigit(c)) {
                        if(attacker == null) {
                            attacker = Integer.parseInt(Character.toString(c)); 
                            notBlocked.remove(gs.getMyCreatures().get(attacker));
                        } else {
                            blocker = Integer.parseInt(Character.toString(c));
                            gs.fightCreatures(attacker, blocker);
                        }
                    }
                    
                
                }
            }
            
            for(InPlayCreature c : notBlocked) {
                gs.setEnemyHealth(gs.getEnemyHealth() - c.getPower());
            }
            System.out.println("Enemy health is " + gs.getEnemyHealth());
            gs.getAttacking().clear();
            d.repaint();
            gs.setTurn(true);

        } else if(m.startsWith("--turn")) {
            gs.setTurn(true);
            gs.setTurnNum(gs.getTurnNum() + 1);
            System.out.println("My Turn! :)");
            gs.drawCard();
            gs.getBus().callEvent(new TurnStartedEvent(gs, gs.getYou()));
            d.repaint();
            for(int i = 0; i < gs.getArrivalCreatures().size(); i = 0) {
                gs.getBus().callEvent(new CreaturePlayedEvent(gs, gs.getArrivalCreatures().get(i), gs.getArrivalLanes().get(i).getNumber()));
            }

        } else if(m.startsWith("--myBoard")) {

            try {
                Card c;
//              if(m.charAt(11) == '|') {
//                  c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 11)));
//              } else {
//                  c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 12)));
//              }
                
                c = gs.getCardsData().getCardFromID(Integer.parseInt(m.substring(10, m.indexOf("|"))));
                
                int l = Integer.parseInt(m.substring(m.indexOf("|") + 1));
                if(l == Constants.LANE_1) {
                    InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane1);
                    gs.getEnemyCreatures().add(ic);
                    gs.getCardsInPlay().add(ic);
                    gs.getLane(Constants.LANE_1).addEnemy(ic);
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
