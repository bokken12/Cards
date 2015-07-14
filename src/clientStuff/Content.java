package clientStuff;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.InPlayCreature;
import events.EventBus;
import events.TurnEndedEvent;
import Player.GamePlayer;
import Player.Player;
import Player.SimplePlayerProfile;


public class Content extends JPanel implements ActionListener {

	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	ImageIcon screen = new ImageIcon("CardScreen.png");
	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	JButton endTurn = new JButton("End Turn");
	JButton attack = new JButton("Attack");
	JPanel foo = new JPanel();
	JPanel buttons = new JPanel();
	JPanel field = new JPanel();
	JPanel handPanel = new JPanel();
	JScrollPane decklist = new JScrollPane();
	ArrayList<Card> hand = new ArrayList<Card>();
	PrintWriter output;
	boolean startTurn;
	ArrayList<JButton> deckButtons = new ArrayList<JButton>();
	Player player;
	Game game;
	JLabel wait = new JLabel();
	boolean clear = false;
	boolean turn;
	ArrayList<Integer> deck;
	static EventBus bus;
	public static GamePlayer you;
	public GamePlayer opponent;
	Cards cardList = new Cards();
	public static InPlayCreature selectedCard;
	static ArrayList<InPlayCreature> cardsInPlay;
	ArrayList<InPlayCreature> Attacking;

	volatile SimplePlayerProfile match;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(clear) {
			screen.setImage(screen.getImage().getScaledInstance((int) 1200, 800, Image.SCALE_DEFAULT));
			screen.paintIcon(this, g, 0, 0);
			//paintCreature( (CreatureCard) hand.get(0), g, 120, 500);
			for(int i = 0; i < hand.size(); i++) {
				paintCreature((CreatureCard) hand.get(i), g, i*120 + 50, 600);

			}
			
		} else {
			background.paintIcon(this, g, 0, 0);
		}


	}

	public Content(Game parent, Player p, PrintWriter out) {

		output = out;

		game = parent;
		player = p;

		int height = background.getIconHeight();
		int width = background.getIconWidth();

		Dimension a = new Dimension(width, height);

		setPreferredSize(a);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(Box.createHorizontalGlue());

		foo.setLayout(new BoxLayout(foo, BoxLayout.PAGE_AXIS));
		field.setLayout(new BoxLayout(field, BoxLayout.Y_AXIS));
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
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

		System.out.println("Got an event!");

		if(e.getSource().equals(play)) {
			System.out.println(":()");
			playMenu();

		} else if(e.getSource().equals(cards)) {
			CardsMenu();

		} else if(e.getSource().equals(settings)) {

		} else if(e.getSource().equals(decklist)) {

		} else if(deckButtons.contains(e.getSource())) {
			System.out.println("Got Deck Button Click");
			int[] theDeck = player.getDecks().get(((JButton) e.getSource()).getText());
			ArrayList<Integer> realDeck = new ArrayList<Integer>();
			Random rand = new Random();
			for(int i = 0; i < theDeck.length; i++) {
				int card = rand.nextInt(theDeck.length);
				if(theDeck[card] != -1) {
					int a = theDeck[card];
					theDeck[card] = -1;
					realDeck.add(a);
				} else {
					i--;
				}
			}
			deck = realDeck;
			System.out.println("-------deck is: " + deck.toString());
			clear = true;
			game.setSize(new Dimension(1200, 800));
			this.removeAll();

			add(field);
			buttons.add(endTurn);
			endTurn.addActionListener(this);
			add(buttons);

			//add(handPanel);
			bus = new EventBus();

			for(Integer i = 0; i < 5; i++) {
				Card ca = cardList.getCardFromID(deck.get(i));
				hand.add(ca);
			}
			for(int a = 0; a < 5; a++) {
				deck.remove(0);
			}

			this.revalidate();

		} else if(e.getSource().equals(endTurn)) {
			if(turn = true) {
				System.out.println("Ending Turn?");
				turn = false;
				bus.callEvent(new TurnEndedEvent(you));
				output.println("--turn");
				output.flush();
			}
		} else if(e.getSource().equals(attack)) {
			output.println("--attack " + Attacking.toString());

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

		return null;
	}

	public void handleMessage(String m) {
		if(m.startsWith("--match")) {
			match = new SimplePlayerProfile(m.substring(7, m.indexOf(",")), Integer.parseInt(m.substring(m.indexOf(",") + 1, m.length() - 2)));
			int turn = Integer.parseInt(m.substring(m.length() - 1));
			if(turn == 1) {
				startTurn = true;
				you = new GamePlayer(1);

			} else {
				startTurn = false;
			}
			System.out.println("found a match!");
			matchScreen();
		} else if(m.startsWith("--wait")) {
			System.out.println("Waiting...");
			wait = new JLabel("Finding a match...");
			add(Box.createHorizontalGlue());
			add(wait);
			add(Box.createHorizontalGlue());
			revalidate();
		} else if(m.startsWith("--block")) {

		} else if(m.startsWith("--turn")) {
			turn = true;
			System.out.println("My Turn! :)");

		} else if(m.startsWith("--continue")) {

		}
	}

	public void matchScreen() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		HashMap<String, int[]> deecks = player.getDecks();
		Object[] a = deecks.keySet().toArray();
		System.out.println(a);

		add(Box.createHorizontalGlue());
		wait.setText("Choose a deck:");
		add(wait);
		for(int  i = 0; i < a.length; i++) {
			JButton b = new JButton(a[i].toString());
			System.out.println(b.toString());
			System.out.println(deecks);
			b.addActionListener(this);
			deckButtons.add(b);

			add(b);


		}
		add(Box.createHorizontalGlue());
		revalidate();
	}

	public int drawCard() {
		return deck.get(0);
		//call drawCardEvent
	}

	public static EventBus getBus() {
		return bus;
	}

	public static ArrayList<InPlayCreature> getCards() {
		return cardsInPlay;
	}

	public static ArrayList<InPlayCreature> getCardsOfType(String type) {
		ArrayList<InPlayCreature> ret = new ArrayList<InPlayCreature>();
		for(int i = 0; i < cardsInPlay.size(); i++) {
			if(cardsInPlay.get(i).getType().equals(type)) {
				ret.add(cardsInPlay.get(i));
			}
		}
		return ret;
	}


	public void paintCreature(CreatureCard card, Graphics g, int x, int y) {
		String power = Integer.toString(card.getPower());
		String health = Integer.toString(card.getToughness());
		String text = card.getAbilityText();
		String cost = Integer.toString(card.getCost());
		ImageIcon img = card.getImageIcon();
		ImageIcon template = new ImageIcon("CreatureTemplate.png");
		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		
		template.paintIcon(handPanel, g, x, y);
		img.paintIcon(handPanel, g, x + 12, y + 25);
		System.out.println("X = " + (x + 10) + " Y = " + (y - 75));
		System.out.println("Component size: " + this.getWidth() + ", " + this.getHeight());
		g.drawString(power, x + 20, y + 163);
		g.drawString(health, x + 92, y + 163);
		g.drawString(text, x + 10, y - 40);


	}
}
