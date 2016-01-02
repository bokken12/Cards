package clientStuff;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

import abilities.Ability;
import abilities.AbilityRunnable;
import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import cards.SpellCard;
import events.AbilityEvent;
import events.CreaturePlayedEvent;
import events.DamageEvent;
import events.EventBus;
import events.GameEvent;
import events.SpellPlayedEvent;
import events.TargetedSpellPlayedEvent;
import events.TurnEndedEvent;
import events.TurnStartedEvent;
import events.UntargetedSpellPlayedEvent;
import messaging.Message;
import messaging.MessageListener;
import player.GamePlayer;
import player.Player;
import player.SimplePlayerProfile;
import uselessSubclasses.Lane;


public class Content extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener, MessageListener {

	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	ImageIcon screen = new ImageIcon("CardScreen.png");

	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	JButton endTurn = new JButton("End Turn");
	JButton attack = new JButton("Attack");
	JButton block = new JButton("Block");

	ArrayList<JButton> deckButtons = new ArrayList<JButton>();

	JPanel foo = new JPanel();
	JPanel buttons = new JPanel();
	JPanel field = new JPanel();
	JPanel handPanel = new JPanel();
	JPanel cardDragging = new JPanel();

	boolean startTurn;
	boolean clear = false;
	boolean turn;
	boolean blocking;
	boolean isMtn1Full = false;
	boolean isMtn2Full = false;
	boolean isMtn3Full = false;
	boolean blockDragging = false;

	Integer mana = 0;
	Integer maxMana = 0;
	Integer turnNum = 0;

	int health = 20;
	int enemyHealth = 20;

	static final int CARD_WIDTH = 115;
	static final int MOUNTAIN_1_X = 300;
	static final int MOUNTAIN_2_X = 700;
	static final int ARRIVAL_CREATURE_Y = 350;
	static final int ARRIVAL_CREATURE_Y_2 = 150;

	JScrollPane decklist = new JScrollPane();
	Graphics g;
	PrintWriter output;

	Player player;
	Game game;
	JLabel wait = new JLabel();
	JLabel manaLabel = new JLabel(mana.toString());
	static ImageIcon template = new ImageIcon("CreatureTemplate.png");

	Font f12 = new Font("Helvetica", Font.PLAIN, 12);
	Font f8 = new Font("Helvetica", Font.PLAIN, 8);
	
	public static EventBus bus = EventBus.getInstance();
	public static GamePlayer you;
	public static GamePlayer opponent;
	public Cards cardsData = new Cards();
	public static InPlayCreature selectedCard;

	static ArrayList<InPlayCreature> cardsInPlay = new ArrayList<InPlayCreature>();
	static List<InPlayCreature> myCreatures = Collections.synchronizedList(new ArrayList<InPlayCreature>());
	static List<InPlayCreature> enemyCreatures = Collections.synchronizedList(new ArrayList<InPlayCreature>());
	public static ArrayList<Lane> arrivalLanes = new ArrayList<Lane>();
	public static ArrayList<CreatureCard> arrivalCreatures = new ArrayList<CreatureCard>();
	ArrayList<InPlayCreature> attacking = new ArrayList<InPlayCreature>();
	ArrayList<InPlayCreature> attackingEnemys = new ArrayList<InPlayCreature>();
	ArrayList<Integer> attackingEnemyNums = new ArrayList<Integer>();
	ArrayList<Integer> deck;


	HashMap<InPlayCreature, InPlayCreature> blockers = new HashMap<InPlayCreature, InPlayCreature>();



	ArrayList<HandCard> handCards = new ArrayList<HandCard>();
	HandCard selectedHandCard;

	Point selecCardPoint = new Point();
	HandCardDragger cd = new HandCardDragger();

	Point blockCardPoint = new Point();
	//BlockCardDragger bd = new BlockCardDragger();
	InPlayCreature blocker;

	Lane lane1 = new Lane(this, 1);
	Lane lane2 = new Lane(this, 2);
	Lane lane3 = new Lane(this, 3);
	public static ArrayList<Lane> lanes = new ArrayList<Lane>();

	volatile SimplePlayerProfile match;

	public void paintComponent(Graphics g) {

		//System.out.println("Repainting");
		super.paintComponent(g);
		this.g = g;
		if(clear) {
			
			screen.paintIcon(this, g, 0, 0);
			paintInPlayCreatures(g);
			paintHandCards(g);
			paintArrivals(g);
			Font f = new Font("FONT", 2, 30);

			g.setFont(f);
			g.drawString(Integer.toString(enemyHealth), 500, 50);

		} else {
			background.paintIcon(this, g, 0, 0);
		}
	}

	public Content(Game parent, Player p, PrintWriter out) {

		output = out;
		game = parent;
		player = p;
		cardsData.init();
		int height = background.getIconHeight();
		int width = background.getIconWidth();
		Dimension a = new Dimension(width, height);
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);

		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		
		lane1.setStartX(/*game.getLocation().x + */51);
		lane1.setStartY(/*game.getLocation().y + */67);
		lane1.setEndX(/*game.getLocation().x + */305);
		lane1.setEndY(/*game.getLocation().y + */600);
		lane2.setStartX(/*game.getLocation().x + */473);
		lane2.setStartY(/*game.getLocation().y + */110);
		lane2.setEndX(/*game.getLocation().x + */700);
		lane2.setEndY(/*game.getLocation().y + */600);
		lane3.setStartX(/*game.getLocation().x + */855);
		lane3.setStartY(/*game.getLocation().y + */115);
		lane3.setEndX(/*game.getLocation().x + */1113);
		lane3.setEndY(/*game.getLocation().y + */600);

		lanes.add(lane1);
		lanes.add(lane2);
		lanes.add(lane3);

		screen.setImage(screen.getImage().getScaledInstance((int) 1200, 800, Image.SCALE_DEFAULT));
		
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
			ArrayList<Integer> theDeck = player.getDecks().get(((JButton) e.getSource()).getText());
			ArrayList<Integer> realDeck = new ArrayList<Integer>();
			Random rand = new Random();
			for(int i = 0; i < theDeck.size(); i++) {
				int card = rand.nextInt(theDeck.size());
				if(theDeck.get(card) != -1) {
					int a = theDeck.get(card);
					theDeck.set(card, -1);
					realDeck.add(a);
				} else {
					i--;
				}
			}
			deck = realDeck;
			System.out.println("-------deck is: " + deck.toString());
			if(startTurn == true) {
				opponent = new GamePlayer(2);
				you = new GamePlayer(1);
			} else {
				opponent = new GamePlayer(1);
				you = new GamePlayer(2);
			}

			clear = true;
			game.setSize(new Dimension(1200, 800));
			this.removeAll();

			add(field);
			buttons.add(endTurn);
			endTurn.addActionListener(this);
			buttons.add(attack);
			attack.addActionListener(this);
			buttons.add(block);
			block.addActionListener(this);
			buttons.add(manaLabel);
			add(buttons);
			//add(handPanel);
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
			add(manaLabel);
			this.revalidate();

			//			Ability playCards = new Ability("Play Creatures", "puts the creatures into play", TurnStartedEvent.class, new AbilityRunnable() {
			//				@Override
			//				public void run(GameEvent event) {
			//					System.out.println("Trying to put in creature");
			//					TurnStartedEvent e = (TurnStartedEvent) event;
			//					for(int i = 0; i < arrivalCreatures.size(); i++) {
			//						if (arrivalLanes.get(i) == null) {
			//							System.out.println("actionPerformed: lane is null for " + arrivalCreatures.get(i) + "!");
			//							System.out.println("arrivalCreatures is " + arrivalCreatures);
			//						}
			//						InPlayCreature c = new InPlayCreature(arrivalCreatures.get(i), arrivalLanes.get(i));
			//						addCreature(c);
			//						if(c.getLane().equals(lane1)) {
			//							lane1.addCard(c);
			//						}
			//						if(c.getLane().equals(lane2)) {
			//							lane2.addCard(c);
			//						}
			//						if(c.getLane().equals(lane3)) {
			//							lane3.addCard(c);
			//						}
			//						arrivalLanes.remove(i);
			//						arrivalCreatures.remove(i);
			//					}
			//				}
			//			});
			//
			//			playCards.RegisterListeners();

			Ability addMana = new Ability("Add Mana", "adds to your mana", TurnStartedEvent.class, new AbilityRunnable() {
				@Override

				public void run(GameEvent event) {
					System.out.println("Updating mana");
					TurnStartedEvent e = (TurnStartedEvent) event;

					if(turnNum == 1 || turnNum == 2 || turnNum == 3 || turnNum == 5 || turnNum == 8
							|| turnNum == 13 || turnNum == 21 || turnNum == 34) {
						maxMana++;
					}
					mana = maxMana;
				}
			});

			addMana.RegisterListeners();

		} else if(e.getSource().equals(endTurn)) {
			if(turn == true) {
				System.out.println("Ending Turn?");
				turn = false;
				bus.callEvent(new TurnEndedEvent(you));
				output.println("--turn");
				output.flush();
			}
		} else if(e.getSource().equals(attack)) {
			//			System.out.println("Attacking with " + attacking.toString());
			//			output.println("--attack " + attacking.toString());
			//			output.flush();
			//			attacking.clear();
			//			turn = false;
			String s = "[";
			for(int i = 0; i < myCreatures.size(); i++) {
				if(attacking.contains(myCreatures.get(i))) {
					s = s + i;
					if(i < myCreatures.size() - 1) {
						s = s + ",";
					}
					myCreatures.get(i).setGreen(false);
				}
			}
			s = s + "]";
			System.out.println("Attacking with " + s);
			output.println("--attack " + s);
			output.flush();
			turn = false;

		} else if(e.getSource().equals(block)) {
			System.out.println(blockers);
			//System.out.println(blocking);


			String s = "";
			for(int i = 0; i < myCreatures.size(); i++) {
				if(blockers.containsKey(myCreatures.get(i))) {
					s = s + i + "," + attackingEnemyNums.get(i) + "|";
					fightCreatures(i, attackingEnemyNums.get(i));
				}
			}

			for(int i = 0; i < attackingEnemys.size(); i++) {
				health = health - attackingEnemys.get(i).getPower();
			}
			System.out.println("Your health is " + health);

			//s = s + "]";
			System.out.println("Blocking with " + s);
			output.println("--block " + s);
			output.flush();
			turn = false;

			blockers.clear();
			blocker = null;
			blocking = false;

			for(int i = 0; i < enemyCreatures.size(); i++) {
				enemyCreatures.get(i).setRed(false);
			}
		}

	}	

	public void playMenu() {

		System.out.println("PlayMenu");

		autoMatch();

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

	public void fightCreatures(int c1, int c2) {
		System.out.println("Creatures are fighting");

		int a1 = myCreatures.get(c1).getPower();
		int a2 = enemyCreatures.get(c2).getPower();
		//System.out.println("My, enemy power is " + a1 + ", " + a2);
		//System.out.println("My health was " + myCreatures.get(c1).getHealth());
		bus.callEvent(new DamageEvent(a2, myCreatures.get(c1)));
		bus.callEvent(new DamageEvent(a1, enemyCreatures.get(c2)));
		repaint();
		//System.out.println("My Creature's health is " + myCreatures.get(c1).getHealth());
		if(myCreatures.get(c1).getHealth() <= 0) {
			myCreatures.get(c1).getLane().getCreatures().remove(myCreatures.get(c1));
			myCreatures.remove(c1);

		}
		if(enemyCreatures.get(c2).getHealth() <= 0) {
			enemyCreatures.get(c2).getLane().getEnemyCreatures().remove(enemyCreatures.get(c2));
			enemyCreatures.remove(c2);
		}
		repaint();
	}

	public void handleMessage(String m) {
		if(m.startsWith("--match")) {
			match = new SimplePlayerProfile(m.substring(7, m.indexOf(",")), Integer.parseInt(m.substring(m.indexOf(",") + 1, m.length() - 2)));
			int turn = Integer.parseInt(m.substring(m.length() - 1));
			if(turn == 1) {
				startTurn = true;
				this.turn = true;
				you = new GamePlayer(1);
				turnNum++;

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
			System.out.println("Handling Blocking");
			StringTokenizer st = new StringTokenizer(m, "|");
			ArrayList<InPlayCreature> notBlocked = (ArrayList<InPlayCreature>) attacking.clone();
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				Integer attacker = null;
				Integer blocker = null;
				for(int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if(Character.isDigit(c)) {
						if(attacker == null) {
							attacker = Integer.parseInt(Character.toString(c));	
							notBlocked.remove(myCreatures.get(attacker));
						} else {
							blocker = Integer.parseInt(Character.toString(c));
							fightCreatures(attacker, blocker);
						}
					}


				}
			}

			for(InPlayCreature c : notBlocked) {
				enemyHealth = enemyHealth - c.getPower();
			}
			System.out.println("Enemy health is " + enemyHealth);
			attacking.clear();
			repaint();
			turn = true;

		} else if(m.startsWith("--turn")) {
			turn = true;
			turnNum++;
			System.out.println("My Turn! :)");
			drawCard();
			bus.callEvent(new TurnStartedEvent(you));
			repaint();
			for(int i = 0; i < arrivalCreatures.size(); i=0) {
				bus.callEvent(new CreaturePlayedEvent(arrivalCreatures.get(i), lanes.indexOf(arrivalLanes.get(i))));
			}

		} else if(m.startsWith("--myBoard")) {

			try {
				Card c;
				//				if(m.charAt(11) == '|') {
				//					c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 11)));
				//				} else {
				//					c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, 12)));
				//				}

				c = cardsData.getCardFromID(Integer.parseInt(m.substring(10, m.indexOf("|"))));

				int l = Integer.parseInt(m.substring(m.indexOf("|") + 1));
				if(l == Constants.LANE_1) {
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane1);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane1.addEnemy(ic);
				} else if(l == Constants.LANE_2) {
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane2);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane2.addEnemy(ic);
				} else if(l == Constants.LANE_3) { 
					InPlayCreature ic = new InPlayCreature( (CreatureCard) c, lane3);
					enemyCreatures.add(ic);
					cardsInPlay.add(ic);
					lane3.addEnemy(ic);
				}
				repaint();

			} catch(Exception e) {
				e.printStackTrace();
				if(e instanceof NumberFormatException) {
					System.out.println("NumberFormatException");
				}
				if(e instanceof IndexOutOfBoundsException) {
					System.out.println("IndexOutOfBoundsException");
				}
				System.out.println("There was an exception");
			}
		} else if(m.startsWith("--attack")) {
			System.out.println("Got attack");
			blocking = true;
			String b = m.substring(10, m.length() - 1);
			StringTokenizer t = new StringTokenizer(b, ",");
			while(t.hasMoreTokens()) {
				int i = Integer.parseInt(t.nextToken());
				InPlayCreature c = enemyCreatures.get(i);
				enemyCreatures.get(enemyCreatures.indexOf(c)).setRed(true);
				attackingEnemys.add(c);
				attackingEnemyNums.add(i);
			}
			System.out.println("Attacking enemy numbers are " + attackingEnemyNums);
			System.out.println("Attacking enemys are " + attackingEnemys);
		}
	}

	public void matchScreen() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		output.println("--remPlay" + player.getUsername() + "|" + player.getRank());
		HashMap<String, ArrayList<Integer>> deecks = player.getDecks();
		Object[] a = deecks.keySet().toArray();

		add(Box.createHorizontalGlue());
		wait.setText("Choose a deck:");
		add(wait);
		for(int  i = 0; i < a.length; i++) {
			JButton b = new JButton(a[i].toString());
			System.out.println(deecks);
			b.addActionListener(this);
			deckButtons.add(b);

			add(b);
		}
		add(Box.createHorizontalGlue());
		revalidate();
	}

	public int drawCard() {
		if(!(deck.isEmpty())) {
			int i = deck.get(0);
			deck.remove(0);
			int x = handCards.get(handCards.size() - 1).getEndX() + 50;
			HandCard draw = new HandCard(x + 50, 600, x + 170, 770, cardsData.getCardFromID(i), handCards.size());
			handCards.add(draw);
			return i;
		} else {
			return 1;
		}
		//call drawCardEvent
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
		
		
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		String text = card.getText();
		String cost = Integer.toString(card.getCost());


		template.paintIcon(handPanel, g, x, y);
		img.paintIcon(handPanel, g, x + 12, y + 25);
		g.drawString(text, x + 10, y - 40);
		g.drawString(cost, x + 100, y + 23);

		if(card.getClass().equals(CreatureCard.class)) {
			CreatureCard cc = (CreatureCard) card;
			String name = cc.getName();
			String power = Integer.toString(cc.getPower());
			String health = Integer.toString(cc.getToughness());
			g.setFont(f12); 
			g.drawString(power, x + 20, y + 163);
			g.drawString(health, x + 92, y + 163);
			g.setFont(f8); 
			g.drawString(name, x + 25, y + 23);
		}
		else if(card.getClass().equals(SpellCard.class)) {
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
			i.paintIcon(handPanel, g, x, y);
		}
		else if(c.isRed()) {
			ImageIcon i = new ImageIcon("red.png");
			i.setImage(i.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
			i.paintIcon(handPanel, g, x, y);
		}
		ImageIcon img = c.getCard().getImageIcon();
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		String text = c.getCard().getText();
		String cost = Integer.toString(c.getCard().getCost());


		template.paintIcon(handPanel, g, x, y);
		img.paintIcon(handPanel, g, x + 12, y + 25);
		g.drawString(text, x + 10, y - 40);
		g.drawString(cost, x + 100, y + 23);

		String name = c.getCard().getName();
		String power = Integer.toString(c.getPower());
		String health = Integer.toString(c.getHealth());
		g.setFont(f12); 
		g.drawString(power, x + 20, y + 163);
		g.drawString(health, x + 92, y + 163);
		g.setFont(f8); 
		g.drawString(name, x + 25, y + 23);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point a = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(a, game);
		repaint();
		InPlayCreature c = lane1.getClick(a);
		if(c != null && myCreatures.contains(c)) {
			attacking.add(c);
			selectedCard = c;
			System.out.println("adding");

			int i = myCreatures.indexOf(c);
			int b = c.getLane().getCreatures().indexOf(c);
			c.setGreen(true);
			myCreatures.set(i, c);
			c.getLane().getCreatures().set(b, c);

		} else {
			InPlayCreature cr = lane2.getClick(a);
			if(cr != null && myCreatures.contains(cr)) {
				attacking.add(cr);
				System.out.println("adding");
				selectedCard = cr;
				int i = myCreatures.indexOf(cr);
				int b = cr.getLane().getCreatures().indexOf(cr);
				cr.setGreen(true);
				myCreatures.set(i, cr);
				cr.getLane().getCreatures().set(b, cr);

			} else {
				InPlayCreature cre = lane3.getClick(a);
				if(cre != null && myCreatures.contains(cre)) {
					attacking.add(cre);
					System.out.println("adding");
					selectedCard = cre;
					int i = myCreatures.indexOf(cre);
					int b = cre.getLane().getCreatures().indexOf(cre);
					cre.setGreen(true);
					myCreatures.set(i, cre);
					cre.getLane().getCreatures().set(b, cre);
				}
			}
		}


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
		//System.out.println("Got a click at " + a);


		for(int i = 0; i < handCards.size(); i++) {
			if(handCards.get(i).containsPoint(a)) {
				if(turn == true || blocking == true) {
					cd = new HandCardDragger();
					System.out.println("Clicking a Hand Card! :D");
					selectedHandCard = handCards.get(i);
					cd.execute();
				}
			}
		}

		if(blocking) {
			InPlayCreature c = lane1.getClick(a);
			if(c != null) {
				blocker = c;
				blockDragging = true;
			} else {
				InPlayCreature cr = lane2.getClick(a);
				if(cr != null) {
					blocker = cr;
					blockDragging = true;
					
				} else {
					InPlayCreature cre = lane3.getClick(a);
					if(cre != null) {
						
						blocker = cre;
						blockDragging = true;
					}
				}
			}
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {

		Point a = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(a, game);

		if(!(cd.isStopped())) {
			if(selectedHandCard.getCard() instanceof CreatureCard/* && mana >= selectedHandCard.getCard().getCost()*/) {
				System.out.println("Playing Creature: " + selectedHandCard);
				if(lane1.containsPoint(selecCardPoint)) {
					/*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane1);;
					arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane1);
					arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
					handCards.remove(selectedHandCard);*/
					arrivalHelper(lane1);

				} else if(lane2.containsPoint(selecCardPoint)) {
					/*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane2);
					arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane2);
					arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
					handCards.remove(selectedHandCard);*/
					arrivalHelper(lane2);

				} else if(lane3.containsPoint(selecCardPoint)) {
					/*InPlayCreature n = new InPlayCreature( (CreatureCard) selectedHandCard.getCard(), lane3);
					arrivals.put( (CreatureCard) selectedHandCard.getCard(), lane3);
					arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
					handCards.remove(selectedHandCard);*/
					arrivalHelper(lane3);
				}
				repaint();
			} else if(selectedHandCard.getCard() instanceof SpellCard) {
				SpellCard sc = (SpellCard) selectedHandCard.getCard();
				if(sc.hasTarget()) {
					EventBus.getInstance().callEvent(new TargetedSpellPlayedEvent(sc, this, selectedCard));
				} else {
					EventBus.getInstance().callEvent(new UntargetedSpellPlayedEvent(sc, this));
				}
				handCards.remove(selectedHandCard);
			}
		}
		cd.stop();
		selectedHandCard = null;



		if(blockDragging) {

			//System.out.println("isStopped? " + bd.isStopped() + "Blocker lane is" + blocker.getLane() + 
			//		"Enemy2 is " + lane2.getClick(a));

			if(attackingEnemys.contains(lane1.getClick(a)) && blocker.getLane() == lane1) {
				blockers.put(blocker, lane1.getClick(a));
				System.out.println("Blocking with creature: " + blocker + " in lane 1");
			}
			if(attackingEnemys.contains(lane2.getClick(a)) && blocker.getLane() == lane2) {
				blockers.put(blocker, lane2.getClick(a));
				System.out.println("Blocking with creature: " + blocker + " in lane 2");
			}
			if(attackingEnemys.contains(lane3.getClick(a)) && blocker.getLane() == lane3) {
				blockers.put(blocker, lane3.getClick(a));
				System.out.println("Blocking with creature: " + blocker + " in lane 3");
			}
			System.out.println(blockers);

			//			for(Lane l : lanes) {
			//				if(attackingEnemys.contains(l.getClick(a)) && blocker.getLane() == l) {
			//					blockers.put(blocker, l.getClick(a));
			//				}
			//			}
		}
		blockDragging = false;
	}

	public void arrivalHelper(Lane lane) {
		arrivalLanes.add(lane);
		arrivalCreatures.add( (CreatureCard) selectedHandCard.getCard());
		handCards.remove(selectedHandCard);

	}


	private class HandCardDragger extends SwingWorker<String, Point> {

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
			selecCardPoint = moves.get(moves.size() - 1); 
			repaint();
		}

		protected void done() {

		}
	}

/*
	private class BlockCardDragger extends SwingWorker<String, Point> {

		volatile boolean stop = true;

		@Override
		protected String doInBackground() {
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
			blockCardPoint = moves.get(0);
			//System.out.println(moves.get(0));
			repaint();
		}

		protected void done() {

		}
	}
*/

	public void addCreature(InPlayCreature n) {
		cardsInPlay.add(n);
		myCreatures.add(n);
		//arrivalLanes.remove(arrivalCreatures.indexOf(n));
		//arrivalCreatures.remove(n);
		n.getCard().registerListeners();
		int lane = 1;
		if (n == null) {
			System.out.println("n is null in addCreature");
		} else if (n.getLane() == null) {
			System.out.println("n.getLane() is null in addCreature");
		}
		if(n.getLane().equals(lane1)) lane = 1;
		if(n.getLane().equals(lane2)) lane = 2;
		if(n.getLane().equals(lane3)) lane = 3;

		String send = "--myBoard " + cardsData.getCollection().indexOf(n.getCard()) + "|" + lane;

		isMtn1Full = false;
		isMtn2Full = false;

		//should send something like --myBoard 1|2
		System.out.println("Sending " + send);
		output.println(send);
		output.flush();
	}

	public void paintInPlayCreatures(Graphics g) {

		//blockCardPoint = MouseInfo.getPointerInfo().getLocation();
		
		ArrayList<InPlayCreature> k = lane1.getCreatures();
		for(int i = 0; i < k.size(); i++) {
			if(k.get(i).equals(blocker)) {
				paintInPlayCreature(k.get(i), g, (int) blockCardPoint.getX(), (int) blockCardPoint.getY());
			} else {
				paintInPlayCreature(k.get(i), g, 50 + i*115, 400);
			}
		}

		ArrayList<InPlayCreature> j = lane2.getCreatures();
		for(int i = 0; i < j.size(); i++) {
			if(j.get(i).equals(blocker)) {
				paintInPlayCreature(j.get(i), g, (int) blockCardPoint.getX(), (int) blockCardPoint.getY());
			} else {
				paintInPlayCreature(j.get(i), g, 455 + i*115, 400);
			}
		}
		ArrayList<InPlayCreature> p = lane3.getCreatures();
		for(int i = 0; i < p.size(); i++) {
			if(p.get(i).equals(blocker)) {
				paintInPlayCreature(p.get(i), g, (int) blockCardPoint.getX(), (int) blockCardPoint.getY());
			} else {
				paintInPlayCreature(p.get(i), g, 855 + i*115, 400);
			}
		}

		ArrayList<InPlayCreature> b = lane1.getEnemyCreatures();
		for(int i = 0; i < b.size(); i++) {
			paintInPlayCreature(b.get(i), g, 50 + i*115, 180);
		}

		ArrayList<InPlayCreature> u = lane2.getEnemyCreatures();
		for(int i = 0; i < u.size(); i++) {
			paintInPlayCreature(u.get(i), g, 455 + i*115, 180);
		}

		ArrayList<InPlayCreature> l = lane3.getEnemyCreatures();
		for(int i = 0; i < l.size(); i++) {
			paintInPlayCreature(l.get(i), g, 855 + i*115, 180);
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

			x = i*(CARD_WIDTH + 5) + 50;
			y = 600;
			if(!(selectedHandCard == null)) {
				if(!(selectedHandCard.equals(handCards.get(i)))) {

					HandCard h = handCards.get(i);
					h.setStartX(x);
					h.setStartY(y);
					h.setEndX(x + (CARD_WIDTH + 5));
					h.setEndY(y + 170);
					handCards.set(i, h);
					paintCreature(handCards.get(i).getCard(), g, x, y);
				} else {
					//System.out.println("Painting selected card at: " + selecCardPoint);
					paintCreature(handCards.get(i).getCard(), g, (int) selecCardPoint.getX(), (int) selecCardPoint.getY());
				}
			} else {
				x = i*(CARD_WIDTH + 5) + 50;
				y = 600;
				paintCreature(handCards.get(i).getCard(), g, x, y);
			}
		}
	}

	public void paintArrivals(Graphics g) {
		for(int i = 0; i < arrivalCreatures.size(); i++) {
			CreatureCard c = arrivalCreatures.get(i);
			if(arrivalLanes.get(i).equals(lane1)) {
				if(! isMtn1Full) {
					paintCreature(c, g, MOUNTAIN_1_X, ARRIVAL_CREATURE_Y);
					isMtn1Full = true;
				} else {
					paintCreature(c, g, MOUNTAIN_1_X, ARRIVAL_CREATURE_Y_2);
				}
			} else if(arrivalLanes.get(i).equals(lane2)) {
				if(! isMtn1Full) {
					paintCreature(c, g, MOUNTAIN_1_X, ARRIVAL_CREATURE_Y);
				} else if(! isMtn2Full) {
					paintCreature(c, g, MOUNTAIN_2_X, ARRIVAL_CREATURE_Y);
					isMtn2Full = true;
				}
			} else if(arrivalLanes.get(i).equals(lane3)) {
				if(! isMtn2Full) {
					paintCreature(c, g, MOUNTAIN_2_X, ARRIVAL_CREATURE_Y);
					isMtn2Full = true;
				} 
			}

		}
		isMtn1Full = false;
		isMtn2Full = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override 
	public void keyTyped(KeyEvent e) {
		System.out.println("Meanie!" + e.getKeyChar());
		if(e.getKeyCode() == KeyEvent.VK_SPACE && selectedCard != null) {
			System.out.println("Calling AbilityEvent");
			bus.callEvent(new AbilityEvent(selectedCard));
			attacking.remove(selectedCard);

		}
	}

	@Override
	public void MessageRecieved(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(blockDragging) { 
			blockCardPoint = e.getPoint();
			System.out.println("Things are dragging "  + e);
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

}
