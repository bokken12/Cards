package clientStates;

import java.awt.event.ActionEvent;

import clientStuff.Board;
import clientStuff.BoardDisplay;
import clientStuff.StateMachine;
import messaging.Message;

public class GameState extends State
{
    private Board board;
    private BoardDisplay display;
    private MiniState currentState;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentState.actionPerformed(e);
    }

    @Override
    public void onInitialize(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBegin(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLeave(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void MessageRecieved(Message message)
    {
        currentState.MessageRecieved(message);
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public BoardDisplay getDisplay()
    {
        return display;
    }

    public void setDisplay(BoardDisplay display)
    {
        this.display = display;
    }

    public MiniState getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(MiniState currentState)
    {
        this.currentState.onLeave(this);
        this.currentState = currentState;
        currentState.onBegin(this);
    }
    
}
