package clientStuff;

import java.util.Stack;

import javax.swing.JFrame;

public class StateMachine extends JFrame
{
    private Stack<State> state;
    private static StateMachine frame;
    public static void main(String[] args){
        frame = new StateMachine();
    }
    public StateMachine(){
        state = new Stack<State>();
    }
    public State getCurrentState(){
        return state.peek();
    }
    public void back(){
        getCurrentState().onDestroy();
        state.pop();
        getCurrentState().onBegin();
    }
    public void setState(State s){
        getCurrentState().onLeave();
        state.push(s);
        getCurrentState().onInitialize();
    }
}
