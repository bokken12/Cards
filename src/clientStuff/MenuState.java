package clientStuff;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import player.Player;
import messaging.Message;
import messaging.PlayingMessage;

public class MenuState extends State
{

    private ImageIcon background;
    private JButton play;
    private JButton settings;
    private JButton cards;
    private JScrollPane decklist;
    private JLabel wait;
    private ArrayList<JButton> deckButtons;
    private JPanel foo;
    
    Player player;
    
    
    public MenuState(Player p){
       player = p;
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
    	 foo = new JPanel();
         
       

    }
    
    @Override
    public void paintComponent(Graphics g) {
    	g.drawImage(background.getImage(), 0, 0, this);
    }

    @Override
    public void onBegin(StateMachine stater)
    {
        stater.setSize(600, 340);
        stater.setVisible(true);
        stater.setResizable(true);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
        foo.setLayout(new BoxLayout(foo, BoxLayout.PAGE_AXIS));
 		foo.add(Box.createVerticalGlue());
 		foo.add(play);
 		play.setAlignmentX(CENTER_ALIGNMENT);
 		foo.add(cards);
 		cards.setAlignmentX(CENTER_ALIGNMENT);
 		foo.add(settings);
 		settings.setAlignmentX(CENTER_ALIGNMENT);
 		foo.setOpaque(false);
 		foo.add(Box.createVerticalGlue());
 		add(foo);
 		
 		
 		add(Box.createHorizontalGlue());
        stater.repaint();
        repaint();
        
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
        	StateMachine.sendMessage(new PlayingMessage(player.getUsername(), player.getRank()));
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

        String[] decks = player.getDecks().keySet().toArray(new String[10]);
        for (int i = 0; i < player.getDecks().size(); i++)
        {
            JLabel a = new JLabel(decks[i]);
            decklist.add(a);
        }
        add(decklist);

        CardList cl = new CardList(player);
        add(cl);

        JCheckBox a = new JCheckBox("Show all cards");
        add(a);
    }
    
    public void waitScreen() {
    	this.removeAll();
    	JLabel waiting = new JLabel("Finding a match...");
    	add(waiting);
    }
    
    public void matchScreen()
    {
        this.removeAll();
        this.repaint();
       
        HashMap<String, int[]> deecks = player.getDecks();
        Object[] a = deecks.keySet().toArray();

        add(Box.createHorizontalGlue());
        wait.setText("Choose a deck:");
        add(wait);
        for (int i = 0; i < a.length; i++)
        {
            JButton b = new JButton(a[i].toString());
            System.out.println(deecks);
            b.addActionListener(this);
            deckButtons.add(b);

            add(b);
        }
        add(Box.createHorizontalGlue());
        revalidate();
    }

}
