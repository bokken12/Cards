package clientStuff;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import player.Player;
import messaging.Message;

public class MenuState extends State
{

    private ImageIcon background;
    private JButton play;
    private JButton settings;
    private JButton cards;
    private JScrollPane decklist;
    private JLabel wait;
    private ArrayList<JButton> deckButtons;
    
    
    public MenuState(Player p){
       
    }
    

    @Override
    public void onInitialize(StateMachine stater)
    {
    	 background  = new ImageIcon("MenuBackground.jpg");
    	 cards = new JButton("Cards");
    	 settings = new JButton("Settings");
    	 play = new JButton("Play");
    	 decklist = new JScrollPane();
    	 wait = new JLabel();
    	 deckButtons = new ArrayList<JButton>();

    }

    @Override
    public void onBegin(StateMachine stater)
    {
        stater.setSize(500, 400);
        stater.setVisible(true);
        stater.setResizable(false);
    }

    @Override
    public void onLeave(StateMachine stater)
    {

    }

    @Override
    public void onDestroy(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void MessageRecieved(Message message)
    {
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(play)) {
        	
        } else if(e.getSource().equals(cards)) {
        	
        } else if(e.getSource().equals(settings)) {
        	
        }

    }
    
    public void CardsMenu()
    {
        this.removeAll();
        this.revalidate();
        this.repaint();
        Dimension b = new Dimension(50, 50);

        add(Box.createHorizontalGlue());

        add(Box.createHorizontalGlue());
        decklist.setPreferredSize(b);
        decklist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        decklist.createVerticalScrollBar();
        decklist.getViewport().setPreferredSize(b);

        String[] decks = gs.getPlayer().getDecks().keySet().toArray(new String[10]);
        for (int i = 0; i < gs.getPlayer().getDecks().size(); i++)
        {
            JLabel a = new JLabel(decks[i]);
            decklist.add(a);
        }
        add(decklist);

        CardList cl = new CardList(gs.getPlayer());
        add(cl);

        JCheckBox a = new JCheckBox("Show all cards");
        add(a);
    }
    
    public void matchScreen()
    {
        this.removeAll();
        this.revalidate();
        this.repaint();
        output.println("--remPlay" + gs.getPlayer().getUsername() + "|"
                + gs.getPlayer().getRank());
        HashMap<String, int[]> deecks = gs.getPlayer().getDecks();
        Object[] a = deecks.keySet().toArray();

        add(Box.createHorizontalGlue());
        wait.setText("Choose a deck:");
        add(wait);
        for (int i = 0; i < a.length; i++)
        {
            JButton b = new JButton(a[i].toString());
            System.out.println(deecks);
            b.addActionListener(control);
            deckButtons.add(b);

            add(b);
        }
        add(Box.createHorizontalGlue());
        revalidate();
    }

}
