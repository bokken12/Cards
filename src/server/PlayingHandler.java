package server;

import java.util.ArrayList;

import messaging.MatchMessage;
import messaging.Message;
import messaging.TurnMessage;
import clientStuff.Board;

public class PlayingHandler
{
    private ArrayList<PlayingState> spectators;
    private ArrayList<PlayingState> team1;
    private ArrayList<PlayingState> team2;
    private ArrayList<Boolean> finished;
    private boolean t1Turn;
    //private PlayingState player1;
    //private PlayingState player2;
    //private Board board;
    public PlayingHandler(){
        spectators = new ArrayList<PlayingState>();
        team1 = new ArrayList<PlayingState>();
        team2 = new ArrayList<PlayingState>();
        finished = new ArrayList<Boolean>();
        t1Turn = true;
    }
    public void addPlayer(ListenerState player, ArrayList<Integer> deck){
        PlayingState p = new PlayingState(this, deck);
        spectators.add(p);
        player.setState(p);
    }
    public void startGame(){
        spectators.addAll(team1);
        spectators.addAll(team2);
        team1.clear();
        team2.clear();
        int teamSize = spectators.size()/2;
        for(int i = 0; i < teamSize; i++){
            team1.add(spectators.remove((int)(Math.random() * spectators.size())));
            team2.add(spectators.remove((int)(Math.random() * spectators.size())));
        }
        for(PlayingState ps: team1){
            ps.send(new MatchMessage(ps.getStartingHand(), "Opponent"));
        }
        for(PlayingState ps: team2){
            ps.send(new MatchMessage(ps.getStartingHand(), "Opponent"));
        }
        t1Turn = true;
        resetFinished();
    }
    public void resetFinished(){
        for(int i = 0; i < currentTeam().size(); i++){
            finished.add(new Boolean(false));
        }
    }
    public void MessageRecieved(Message message, PlayingState from){
        if(message instanceof TurnMessage){
            finished.set(currentTeam().indexOf(from), new Boolean(true));
        }
    }
    public ArrayList<PlayingState> currentTeam(){
        if(t1Turn){
            return team1;
        } else {
            return team2;
        }
    }
    public boolean turnFinished(){
        for(int i = 0; i < finished.size(); i++){
            if(!(finished.get(i).booleanValue())){
                return false;
            }
        }
        return true;
    }
    public void send(Message m, ArrayList<PlayingState> team){
        for(PlayingState ps: team){
            ps.send(m);
        }
    }
}
