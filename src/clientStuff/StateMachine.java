package clientStuff;

import java.util.Stack;
import javax.swing.JFrame;

public class StateMachine extends JFrame
{
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 600;
    private Stack<State> state;
    private static StateMachine frame;
    public static void main(String[] args){
        frame = new StateMachine();
    }
    public StateMachine(){
        state = new Stack<State>();
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
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
}
