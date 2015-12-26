package miniStates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clientStates.GameState;
import messaging.MessageListener;

public abstract class MiniState implements MessageListener, ActionListener
{
    public abstract void onBegin(GameState stater);
    public abstract void onLeave(GameState stater);
}
