package clientStuff;

import java.util.Stack;

import javax.swing.JFrame;

import messaging.Message;
import messaging.MessageListener;
import messaging.Messager;

public class StateMachine extends JFrame implements MessageListener
{
    private Stack<State> state;
    private Messager messager;
    private static StateMachine frame;
    public static void main(String[] args){
        frame = new StateMachine();
        frame.setState(new LoginState());
    }
    public StateMachine(){
        state = new Stack<State>();
        messager = new Messager(this);
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
        getCurrentState().onBegin(this);
        add(getCurrentState());
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
    @Override
    public void MessageRecieved(Message message)
    {
        if(!(state.isEmpty())){
            getCurrentState().MessageRecieved(message);
        }
    }
}