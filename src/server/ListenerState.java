package server;

import player.Player;
import clientStuff.StateMachine;
import messaging.Message;
import messaging.MessageListener;

public abstract class ListenerState implements MessageListener
{
    protected ClientListener listener;
    public void onInitialize(ClientListener stater){
        listener = stater;
    }
    public abstract void onBegin(ClientListener stater);
    public abstract void onLeave(ClientListener stater);
    public abstract void onDestroy(ClientListener stater);
    public void send(Message m){
        listener.send(m);
    }
    public void setState(ListenerState ls){
        listener.setState(ls);
    }
    public Player getPlayer(){
        return listener.getPlayer();
    }
}
