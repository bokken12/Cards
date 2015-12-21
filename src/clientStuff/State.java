package clientStuff;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import messaging.MessageListener;

public abstract class State extends JPanel implements ActionListener, MessageListener
{
    public abstract void onInitialize();
    public abstract void onBegin();
    public abstract void onLeave();
    public abstract void onDestroy();
}
