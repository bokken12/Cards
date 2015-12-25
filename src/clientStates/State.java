package clientStates;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import clientStuff.StateMachine;
import messaging.MessageListener;

public abstract class State extends JPanel implements ActionListener, MessageListener
{
    public abstract void onInitialize(StateMachine stater);
    public abstract void onBegin(StateMachine stater);
    public abstract void onLeave(StateMachine stater);
    public abstract void onDestroy(StateMachine stater);
}
