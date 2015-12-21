package clientStuff;

import java.util.Stack;

import javax.swing.JFrame;

import messaging.Message;
import messaging.Messager;
import events.EventBus;
import events.GameEvent;

public class StateMachine extends JFrame
{
    private Stack<State> state;
    private EventBus bus;
    private Messager messager;
    private static StateMachine frame;
    public static void main(String[] args){
        frame = new StateMachine();
        frame.setState(new LoginState());
    }
    public StateMachine(){
        state = new Stack<State>();
        bus = new EventBus();
        messager = new Messager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public State getCurrentState(){
        return state.peek();
    }
    public void back(){
        remove(getCurrentState());
        getCurrentState().onDestroy(this);
        state.pop();
        if(!(state.isEmpty())){
            getCurrentState().onBegin(this);
            add(getCurrentState());
        }
    }
    public void setState(State s){
        if(!(state.isEmpty())){
            getCurrentState().onLeave(this);
            remove(getCurrentState());
        }
        state.push(s);
        getCurrentState().onInitialize(this);
        add(getCurrentState());
    }
    public EventBus getBus()
    {
        return bus;
    }
    public Messager getMessager()
    {
        return messager;
    }
    public static StateMachine getFrame(){
        return frame;
    }
    public static void sendMessage(Message m){
        frame.getMessager().send(m);
    }
    public static void callEvent(GameEvent e){
        frame.getBus().callEvent(e);
    }
}
