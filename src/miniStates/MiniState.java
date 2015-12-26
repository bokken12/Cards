package miniStates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import clientStates.GameState;
import messaging.MessageListener;

public abstract class MiniState implements MessageListener, ActionListener, MouseListener
{
    private GameState game;
    public void onInititialize(GameState stater){
        game = stater;
    }
    public abstract void onBegin(GameState stater);
    public abstract void onLeave(GameState stater);
    public void setState(MiniState state){
        game.setCurrentState(state);
    }
}
