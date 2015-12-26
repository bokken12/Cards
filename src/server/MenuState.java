package server;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import messaging.MatchMessage;
import messaging.Message;
import messaging.PlayingMessage;

public class MenuState extends ListenerState
{
    public static final int NUM_RANKS = 25;
    public static final int STARTING_HAND_SIZE = 3;
    private PlayingMessage message;
    //public static List<String> playing = Collections.synchronizedList(new ArrayList<String>(NUM_RANKS));
    //public static List<List<Integer>> decks = Collections.synchronizedList(new ArrayList<List<Integer>>(NUM_RANKS));
    public static List<MenuState> listeners = Collections.synchronizedList(new ArrayList<MenuState>(NUM_RANKS));
    @Override
    public void MessageRecieved(Message message)
    {
        if(message instanceof PlayingMessage){
            int rank = ((PlayingMessage) message).getRank();
            this.message = (PlayingMessage) message;
            if(listeners.get(rank) != null){
                MenuState opponent = listeners.set(rank, null);
                opponent.goToGameState(this);
                goToGameState(opponent);
            } else {
                //playing.set(rank, ((PlayingMessage) message).getName());
                //decks.set(rank, ((PlayingMessage) message).getDeck());
                listeners.set(rank, this);
            }
        }
    }

    @Override
    public void onBegin(ClientListener stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLeave(ClientListener stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy(ClientListener stater)
    {
        // TODO Auto-generated method stub
    }
    
    public void goToGameState(MenuState opponent){
        ArrayList<Integer> sh = new ArrayList<Integer>();
        for(int i = 0; i < STARTING_HAND_SIZE; i++){
            sh.add(message.getDeck().remove((int)(Math.random() * message.getDeck().size())));
        }
        send(new MatchMessage(sh, opponent.getPlayer().getUsername()));
    }

}
