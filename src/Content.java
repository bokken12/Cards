import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Player.Player;
import Player.SimplePlayerProfile;


public class Content extends JPanel implements ActionListener {

	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	JPanel foo = new JPanel();
	JScrollPane decklist = new JScrollPane();
	PrintWriter output;
	ArrayList<JButton> deckButtons = new ArrayList<JButton>();
	Player player;
	boolean paint1 = false;
	
	volatile SimplePlayerProfile match;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(paint1) {
			background.paintIcon(this, g, 0, 0);
			JLabel wait = new JLabel("Finding a match...");
			add(wait);
			paint1 = false;
		} else {
			background.paintIcon(this, g, 0, 0);
		}

	}

	public Content(Game parent, Player p, PrintWriter out) {
		
		output = out;

		player = p;

		int height = background.getIconHeight();
		int width = background.getIconWidth();

		Dimension a = new Dimension(width, height);

		setPreferredSize(a);
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

		play.addActionListener(this);
		cards.addActionListener(this);
		settings.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(play)) {
			System.out.println(":()");
			playMenu();
			
		} else if(e.getSource().equals(cards)) {
			CardsMenu();

		} else if(e.getSource().equals(settings)) {

		} else if(e.getSource().equals(decklist)) {
			
		} else if(deckButtons.contains(e.getSource())) {
			int[] theDeck = player.getDecks().get(((JButton) e.getSource()).getText());
		}


	}

	public void playMenu() {

		System.out.println("PlayMenu");

		SimplePlayerProfile a = autoMatch();
		
		this.removeAll();
		this.revalidate();
		this.repaint();
	}

	public void CardsMenu() {
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
		
		
		String[] decks =  player.getDecks().keySet().toArray(new String[10]);
		for(int i = 0; i < player.getDecks().size(); i++) {
			JLabel a = new JLabel(decks[i]);
			decklist.add(a);
		}
		add(decklist);
		
		CardList cl = new CardList(player);
		add(cl);
		
		JCheckBox a = new JCheckBox("Show all cards");
		add(a);
		

	}
	
	public SimplePlayerProfile autoMatch() {
		
		output.println("--Playing " + player.getRank() + " " + player.getUsername());
		output.flush();
		
		System.out.println("Automatching");
		
//		String l = "";
//		try {
//			l = input.readLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Line = " + l);
//		if(l.startsWith("--match")) {
//			return new SimplePlayerProfile(l.substring(7, l.indexOf(",")), Integer.parseInt(l.substring(l.indexOf(","))));
//		} else if(l.startsWith("--wait")) {
//			System.out.println("Waiting...");
//		}
		return null;
//
	}
	
	public void handleMessage(String m) {
		if(m.startsWith("--match")) {
			match = new SimplePlayerProfile(m.substring(7, m.indexOf(",")), Integer.parseInt(m.substring(m.indexOf(",") + 1)));
			System.out.println("found a match!");
			matchScreen();
		} else if(m.startsWith("--wait")) {
			System.out.println("Waiting...");
			JLabel wait = new JLabel("Finding a match...");
			paint1 = true;
			repaint();
			JLabel wait1 = new JLabel("Finding a match...");
			add(wait1);
		}
	}
	
	public void matchScreen() {
		HashMap<String, int[]> deecks = player.getDecks();
		Object[] a = deecks.keySet().toArray();
		
		for(int  i = 0; i < a.length; i++) {
			JButton b = new JButton(a[i].toString());
			b.addActionListener(this);
			deckButtons.add(b);
			add(b);
		}
	}
}
