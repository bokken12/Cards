package clientStates;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import cards.Card;
import cards.CreatureCard;
import cards.InPlayCreature;
import cards.SpellCard;
import clientStuff.Board;
import clientStuff.BoardDisplay;
import clientStuff.StateMachine;
import messaging.Message;
import miniStates.MiniState;
import miniStates.YourTurnState;
import uselessSubclasses.Lane;

public class GameState extends State
{
    private Board board;
    private BoardDisplay display;
    private MiniState currentState;
    private ArrayList<InPlayCreature> myCreatures;

    ImageIcon screen;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentState.actionPerformed(e);
    }

    @Override
    public void onInitialize(StateMachine stater)
    {
        board = new Board();
        display = new BoardDisplay(board);
        setCurrentState(new YourTurnState());
        add(display);
    }

    @Override
    public void onBegin(StateMachine stater)
    {
        stater.setSize(new Dimension(1200, 800));
        stater.setResizable(false);
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
        if(this.currentState != null){
            this.currentState.onLeave(this);
        }
        this.currentState = currentState;
        currentState.onInititialize(this);
        currentState.onBegin(this);
    }
}
