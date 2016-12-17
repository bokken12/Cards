package clientStates;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import cards.Card;
import cards.CreatureCard;
import cards.InPlayCreature;
import cards.SpellCard;
import clientStuff.Board;
import clientStuff.BoardDisplay;
import clientStuff.Constants;
import clientStuff.StateMachine;
import messaging.Message;
import miniStates.MiniState;
import miniStates.YourTurnState;
import uselessSubclasses.Lane;

public class GameState extends State implements Constants
{
    private Board board;
    private ImageIcon myscreen;
    private ImageIcon template;
    private JButton attack;
    private JButton block;
    //private BoardDisplay display;
    private MiniState currentState;
    private ArrayList<InPlayCreature> myCreatures;
    private int currentLane;

    public GameState(){
        
    }
    
    ImageIcon screen;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentState.actionPerformed(e);
    }

    @Override
    public void onInitialize(StateMachine stater)
    {
        myscreen = new ImageIcon("CardScreen.png");
        screen = new ImageIcon("CardScreen.png");
        template = new ImageIcon("CreatureTemplate.png");
        template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
        board = new Board();
        //display = new BoardDisplay(board);
        //display.setSize(1200, 800);
        setCurrentState(new YourTurnState());
        //add(display);
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

    /*public BoardDisplay getDisplay()
    {
        return display;
    }

    public void setDisplay(BoardDisplay display)
    {
        this.display = display;
    }*/

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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myscreen.paintIcon(this, g, 0, 0);
    }

    public void paintCreatures(Graphics g){
        Lane cl = board.getLane(currentLane);
        paintSide(g, cl.getCreatures(), 100);
        paintSide(g, cl.getEnemyCreatures(), 600);
    }
    public void paintSide(Graphics g, ArrayList<InPlayCreature> a, int y){
        int sideWidth = a.size() * (CARD_WIDTH + CARD_SPACING) - CARD_SPACING;
        int width = this.getWidth();
        int x = (width - sideWidth)/2;
        for(int i = 0; i < a.size(); i++){
            paintInPlayCreature(a.get(i), g, x, y);
            x += CARD_WIDTH + CARD_SPACING;
        }
    }
    
    public void paintCreature(Card card, Graphics g, int x, int y) {
        ImageIcon img = card.getImageIcon();
        img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
        String text = card.getText();
        String cost = Integer.toString(card.getCost());


        template.paintIcon(this, g, x, y);
        img.paintIcon(this, g, x + 12, y + 25);
        g.drawString(text, x + 10, y - 40);
        g.drawString(cost, x + 100, y + 23);

        if(card.getClass().equals(CreatureCard.class)) {
            CreatureCard cc = (CreatureCard) card;
            String name = cc.getName();
            String power = Integer.toString(cc.getPower());
            String health = Integer.toString(cc.getToughness());
            g.setFont(new Font("Helvetica", Font.PLAIN, 12)); 
            g.drawString(power, x + 20, y + 163);
            g.drawString(health, x + 92, y + 163);
            g.setFont(new Font("Helvetica", Font.PLAIN, 8)); 
            g.drawString(name, x + 25, y + 23);
            //System.out.println("Painting " + name + " with health " + health);
        }
        if(card.getClass().equals(SpellCard.class)) {
            SpellCard sc = (SpellCard) card;
            String name = sc.getName();
            //g.setFont(new Font("Helvetica", Font.PLAIN, 11)); 
            g.setFont(g.getFont().deriveFont (64.0f));
            g.drawString(name, x + 25, y + 23);
        }
    }

    public void paintInPlayCreature(InPlayCreature c, Graphics g, int x, int y) {


        if(c.isGreen()) {
            ImageIcon i = new ImageIcon("green.png");
            i.setImage(i.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
            i.paintIcon(this, g, x, y);
        }
        if(c.isRed()) {
            ImageIcon i = new ImageIcon("red.png");
            i.setImage(i.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
            i.paintIcon(this, g, x, y);
        }
        ImageIcon img = c.getCard().getImageIcon();
        ImageIcon template = new ImageIcon("CreatureTemplate.png");
        template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
        img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
        String text = c.getCard().getText();
        String cost = Integer.toString(c.getCard().getCost());


        template.paintIcon(this, g, x, y);
        img.paintIcon(this, g, x + 12, y + 25);
        //g.drawString(text, x + 10, y - 40);
        g.drawString(cost, x + 100, y + 23);

        String name = c.getCard().getName();
        String power = Integer.toString(c.getPower());
        String health = Integer.toString(c.getHealth());
        g.setFont(new Font("Helvetica", Font.PLAIN, 12)); 
        g.drawString(power, x + 20, y + 163);
        g.drawString(health, x + 92, y + 163);
        g.setFont(new Font("Helvetica", Font.PLAIN, 8)); 
        g.drawString(name, x + 25, y + 23);
        //System.out.println("Painting " + name + " with health " + health);



    }

    public void paintCloseUpCreature() {
        //TODO
    }

    public void paintInPlayCreatures(Graphics g) {
        for(int i = 0; i < board.getLane(board.getCurrentLane()).getCreatures().size(); i++) {
            paintInPlayCreature(board.getLane(board.getCurrentLane()).getCreatures().get(i), g, 234, 199);
        }
    }
    
    public void paintHandCards(Graphics g) {
        
    }
}
