package clientStuff;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import events.EventBus;
import events.TurnEndedEvent;
import Player.GamePlayer;
import Player.Player;
import Player.SimplePlayerProfile;
import uselessSubclasses.Lane;


public class Content extends JPanel implements ActionListener, MouseListener {

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
	Graphics g;
	PrintWriter output;
	ArrayList<JButton> deckButtons = new ArrayList<JButton>();
	Player player;
	Game game;
	JLabel wait = new JLabel();

	boolean startTurn;
	boolean clear = false;
	boolean turn;
	boolean blocking;

	ArrayList<Integer> deck;
	static EventBus bus;
	public static GamePlayer you;
	public GamePlayer opponent;
	public Cards cardsData = new Cards();
	public static InPlayCreature selectedCard;

	static ArrayList<InPlayCreature> cardsInPlay = new ArrayList<InPlayCreature>();
	static ArrayList<InPlayCreature> myCreatures = new ArrayList<InPlayCreature>();
	static ArrayList<InPlayCreature> enemyCreatures = new ArrayList<InPlayCreature>();
	

	ArrayList<InPlayCreature> attacking = new ArrayList<InPlayCreature>();
	ArrayList<HandCard> handCards = new ArrayList<HandCard>();
	HandCard selectedHandCard;
	Point selecCardPoint = new Point();
	CardDragger cd = new CardDragger();

	Lane lane1 = new Lane(this);
	Lane lane2 = new Lane(this);
	Lane lane3 = new Lane(this);

	volatile SimplePlayerProfile match;

	public void paintComponent(Graphics g) {


		

		//System.out.println("Repainting");
		super.paintComponent(g);
		this.g = g;

		if(clear) {
			screen.setImage(screen.getImage().getScaledInstance((int) 1200, 800, Image.SCALE_DEFAULT));
			screen.paintIcon(this, g, 0, 0);
			//paintCreature( (CreatureCard) hand.get(0), g, 120, 500);

			paintInPlayCreatures(g);

			paintHandCards(g);
			
		} else {
			background.paintIcon(this, g, 0, 0);
		}
	}

	public Content(Game parent, Player p, PrintWriter out) {

		output = out;

		game = parent;
		player = p;

		cardsData.Init();

		int height = background.getIconHeight();
		int width = background.getIconWidth();

		Dimension a = new Dimension(width, height);
		addMouseListener(this);


		lane1.setStartX(game.getLocation().x + 51);
		lane1.setStartY(game.getLocation().y + 67);
		lane1.setEndX(game.getLocation().x + 305);
		lane1.setEndY(game.getLocation().y + 600);

		lane2.setStartX(game.getLocation().x + 473);
		lane2.setStartY(game.getLocation().y + 110);
		lane2.setEndX(game.getLocation().x + 700);
		lane2.setEndY(game.getLocation().y + 600);

		lane3.setStartX(game.getLocation().x + 855);
		lane3.setStartY(game.getLocation().y + 115);
		lane3.setEndX(game.getLocation().x + 1113);
		lane3.setEndY(game.getLocation().y + 600);



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
			int x;
			int y;
			for(Integer i = 0; i < 5; i++) {
				Card ca = cardsData.getCardFromID(deck.get(i));
				x = i*120 + 50;
				y = 600;
				HandCard c = new HandCard(x, y, x + 120, y + 170,ca,i);
				handCards.add(c);
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
			output.println("--attack " + attacking.toString());
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
				this.turn = true;
				you = new GamePlayer(1);

			} else {
				startTurn = false;
				this.turn = false;
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

		} else if(m.startsWith("--myBoard")) {

			try {
				Card c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 11)));
				int l = Integer.parseInt(m.substring(12));
				if(l == Constants.LANE_1) {
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane1);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane1.addEnemy(ic);
					System.out.println("Got Lane");
				} else if(l == Constants.LANE_2) {
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane2);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane2.addEnemy(ic);
					System.out.println("Got Lane");
				} else if(l == Constants.LANE_3) {
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane3);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane3.addEnemy(ic);
					System.out.println("Got Lane");
				}
				repaint();

			} catch(Exception e) {
				if(e instanceof NumberFormatException) {
					System.out.println("NumberFormatException");
				}
				if(e instanceof IndexOutOfBoundsException) {
					System.out.println("IndexOutOfBoundsException");
				}
				System.out.println("There was an exception");
			}
		}
	}

	public void matchScreen() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		output.println("--remPlay" + player.getUsername() + "|" + player.getRank());
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

	public void paintCreature(Card card, Graphics g, int x, int y) {

		ImageIcon img = card.getImageIcon();
		ImageIcon template = new ImageIcon("CreatureTemplate.png");
		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		String text = card.getText();
		String cost = Integer.toString(card.getCost());
		template.paintIcon(handPanel, g, x, y);
		img.paintIcon(handPanel, g, x + 12, y + 25);
		g.drawString(text, x + 10, y - 40);
		g.drawString(cost, x + 100, y + 23);

		if(card.getClass().equals(CreatureCard.class)) {
			CreatureCard cc = (CreatureCard) card;
			String power = Integer.toString(cc.getPower());
			String health = Integer.toString(cc.getToughness());
			g.drawString(power, x + 20, y + 163);
			g.drawString(health, x + 92, y + 163);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point a = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(a, game);
		System.out.println("Got a click at " + a);


		for(int i = 0; i < handCards.size(); i++) {
			if(handCards.get(i).containsPoint(a)) {
				if(turn == true || blocking == true) {
					cd = new CardDragger();
					System.out.println("Clicking a Hand Card! :D");
					selectedHandCard = handCards.get(i);
					cd.execute();
				}
			}
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {

		if(!(cd.isStopped())) {
			if(selectedHandCard.getCard() instanceof CreatureCard) {
				System.out.println("Playing Creature: " + selectedHandCard);
				if(lane1.containsPoint(selecCardPoint)) {
					InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane1);;
					lane1.addCard(n);
					handCards.remove(selectedHandCard);
					addCreature(n);

				} else if(lane2.containsPoint(selecCardPoint)) {
					InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane2);
					lane2.addCard(n);
					handCards.remove(selectedHandCard);
					addCreature(n);

				} else if(lane3.containsPoint(selecCardPoint)) {
					InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane3);
					lane3.addCard(n);
					handCards.remove(selectedHandCard);
					addCreature(n);

				}
				repaint();
			}
		}
		cd.stop();
		selectedHandCard = null;
	}


	private class CardDragger extends SwingWorker<String, Point> {

		volatile boolean stop = true;

		@Override
		protected String doInBackground() {
			System.out.println("Starting CardDragger...");
			stop = false;
			while(stop == false) {
				Point a = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(a, game);
				int x = a.x + 5;
				int y = a.y - 25;
				a.setLocation(x, y);
				publish(a);
			}
			return ":{D";

		}

		public void stop() {
			stop = true;
		}

		public boolean isStopped() {
			return stop;
		}

		@Override
		protected void process(List<Point> moves) {
			selecCardPoint = moves.get(0);
			System.out.println(moves.get(0));
			repaint();
		}

		protected void done() {

		}
	}

	public void addCreature(InPlayCreature n) {
		cardsInPlay.add(n);
		myCreatures.add(n);
		int lane = 1;
		if(n.getLane().equals(lane1)) lane = 1;
		if(n.getLane().equals(lane2)) lane = 2;
		if(n.getLane().equals(lane3)) lane = 3;

		String send = "--myBoard " + cardsData.getCollection().indexOf(n.getCard()) + "|" + lane;



		//should send something like --myBoard 1|2
		System.out.println("Sending " + send);
		output.println(send);
		output.flush();
	}
	
	public void paintInPlayCreatures(Graphics g) {
		
		ArrayList<InPlayCreature> k = lane1.getCreatures();
		for(int i = 0; i < k.size(); i++) {
			paintCreature(k.get(i).getCard(), g, 50 + i*115, 400);
		}

		ArrayList<InPlayCreature> j = lane2.getCreatures();
		for(int i = 0; i < j.size(); i++) {
			paintCreature(j.get(i).getCard(), g, 455 + i*115, 400);
		}

		ArrayList<InPlayCreature> p = lane3.getCreatures();
		for(int i = 0; i < p.size(); i++) {
			paintCreature(p.get(i).getCard(), g, 855 + i*115, 400);
		}

		ArrayList<InPlayCreature> b = lane1.getEnemyCreatures();
		for(int i = 0; i < b.size(); i++) {
			paintCreature(b.get(i).getCard(), g, 50 + i*115, 180);
		}

		ArrayList<InPlayCreature> u = lane2.getEnemyCreatures();
		for(int i = 0; i < u.size(); i++) {
			paintCreature(u.get(i).getCard(), g, 455 + i*115, 180);
		}

		ArrayList<InPlayCreature> l = lane3.getEnemyCreatures();
		for(int i = 0; i < l.size(); i++) {
			paintCreature(l.get(i).getCard(), g, 855 + i*115, 180);
		}
	}
	
	public void paintHandCards(Graphics g) {
		
		int x;
		int y;

		for(int i = 0; i < handCards.size(); i++) {
			x = i*120 + 50;
			y = 600;
			HandCard h = handCards.get(i);
			h.setStartX(x);
			h.setStartY(y);
			h.setEndX(x + 120);
			h.setEndY(y + 170);
			handCards.set(i, h);
		}
		
		for(int i = 0; i < handCards.size(); i++) {

			x = i*120 + 50;
			y = 600;
			if(!(selectedHandCard == null)) {
				if(!(selectedHandCard.equals(handCards.get(i)))) {

					HandCard h = handCards.get(i);
					h.setStartX(x);
					h.setStartY(y);
					h.setEndX(x + 120);
					h.setEndY(y + 170);
					handCards.set(i, h);
					paintCreature(handCards.get(i).getCard(), g, x, y);
				} else {
					//System.out.println("Painting selected card at: " + selecCardPoint);
					paintCreature(handCards.get(i).getCard(), g, (int) selecCardPoint.getX(), (int) selecCardPoint.getY());
				}
			} else {
				x = i*120 + 50;
				y = 600;
				paintCreature(handCards.get(i).getCard(), g, x, y);
			}
		}
	}
}
