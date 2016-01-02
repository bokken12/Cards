package server;

import java.util.ArrayList;

import clientStuff.Constants;
import messaging.Message;

public class PlayingState extends ListenerState implements Constants
{
    private PlayingHandler handler;
    private ArrayList<Integer> deck;
    public PlayingState(PlayingHandler p, ArrayList<Integer> deck){
        handler = p;
        this.deck = deck;
    }
    @Override
    public void MessageRecieved(Message message)
    {
        handler.MessageRecieved(message, this);
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
    public ArrayList<Integer> getStartingHand(){
        ArrayList<Integer> sh = new ArrayList<Integer>();
        for(int i = 0; i < STARTING_HAND_SIZE; i++){
            sh.add(deck.remove((int)(Math.random() * deck.size())));
        }
        return sh;
    }
}
