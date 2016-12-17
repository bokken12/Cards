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

import player.GamePlayer;
import player.Player;
import player.SimplePlayerProfile;
import abilities.Ability;
import abilities.AbilityRunnable;
import cards.Card;
import cards.Cards;
import cards.CreatureCard;
import cards.HandCard;
import cards.InPlayCreature;
import cards.SpellCard;
import events.AbilityEvent;
import events.CardDrawnEvent;
import events.CreatureKilledEvent;
import events.CreaturePlayedEvent;
import events.DamageEvent;
import events.EventBus;
import events.GameEvent;
import events.SpellPlayedEvent;
import events.TargetedSpellPlayedEvent;
import events.TurnEndedEvent;
import events.TurnStartedEvent;
import events.UntargetedSpellPlayedEvent;
<<<<<<< HEAD
import uselessSubclasses.Lane;
=======
import Player.GamePlayer;
import Player.Player;
import Player.SimplePlayerProfile;
>>>>>>> master


public class Content extends JPanel implements ActionListener, MouseListener, KeyListener {	

<<<<<<< HEAD
=======

	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	ImageIcon screen = new ImageIcon("newBoard.png");

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

	Font f = new Font("FONT", 2, 30);

	Integer mana = 0;
	Integer maxMana = 0;
	Integer turnNum = 0;

	static final int CARD_WIDTH = 115;
	static final int CARD_HEIGHT = 185;
	static final int BLOWUP_CARD_WIDTH = 230;
	static final int BLOWUP_CARD_HEIGHT = 370;
	int SCREEN_WIDTH = 1200;
	int SCREEN_HEIGHT = 750;
	int HAND_Y_POSITION = SCREEN_HEIGHT - CARD_HEIGHT - 5;

	int health = 20;
	int enemyHealth = 20;


	static final int MOUNTAIN_1_X = 80;
	static final int MOUNTAIN_2_X = 1000;
	static final int ARRIVAL_CREATURE_Y = 350;
	static final int ARRIVAL_CREATURE_Y_2 = 150;

	JScrollPane decklist = new JScrollPane();
	Graphics g;
	PrintWriter output;

	Player player;
	Game game;
	JLabel wait = new JLabel();
	JLabel manaLabel = new JLabel(mana.toString());


	public static EventBus bus = EventBus.getInstance();
	public static GamePlayer you;
	public static GamePlayer opponent;
	public Cards cardsData = new Cards();
	public static InPlayCreature selectedCard;

	public static ArrayList<InPlayCreature> cardsInPlay = new ArrayList<InPlayCreature>();
	public static List<InPlayCreature> myCreatures = Collections.synchronizedList(new ArrayList<InPlayCreature>());
	public static List<InPlayCreature> enemyCreatures = Collections.synchronizedList(new ArrayList<InPlayCreature>());
	public static ArrayList<CreatureCard> arrivalCreatures = new ArrayList<CreatureCard>();
	ArrayList<InPlayCreature> attacking = new ArrayList<InPlayCreature>();
	ArrayList<InPlayCreature> attackingEnemys = new ArrayList<InPlayCreature>();
	ArrayList<Integer> attackingEnemyNums = new ArrayList<Integer>();
	ArrayList<Integer> deck;


	HashMap<InPlayCreature, InPlayCreature> blockers = new HashMap<InPlayCreature, InPlayCreature>();

	ImageIcon green = new ImageIcon("green.png");
	ImageIcon red = new ImageIcon("red.png");
	ImageIcon template = new ImageIcon("CreatureTemplate.png");
	ImageIcon blowupTemplate = new ImageIcon("CreatureTemplate.png");


	ArrayList<HandCard> handCards = new ArrayList<HandCard>();
	HandCard selectedHandCard;

	Point selecCardPoint = new Point();
	HandCardDragger cd = new HandCardDragger();

	Point blockCardPoint = new Point();
	BlockCardDragger bd = new BlockCardDragger();
	InPlayCreature blocker;

	Font f8 = new Font("Helvetica", Font.PLAIN, 8);
	Font f12 = new Font("Helvetica", Font.PLAIN, 12);
	volatile SimplePlayerProfile match;
	boolean targetingAbility = false;

	public void paintComponent(Graphics g) {

		//System.out.println("Repainting");
		super.paintComponent(g);
		this.g = g;
		if(clear) {

			screen.paintIcon(this, g, 0, 0);
			paintInPlayCreatures(g);
			paintHandCards(g);
			paintArrivals(g);


			g.setFont(f);
			g.drawString(Integer.toString(enemyHealth), 500, 50);
			g.drawString(mana.toString(), 550, 50);

			if(enemyHealth <= 0) {
				win();
			}

		} else {
			background.paintIcon(this, g, 0, 0);
		}
	}
>>>>>>> master

	private void win() {
		System.out.println("Winning");
		output.println("--win");
		output.flush();
		game.add(new WinScreen(game, player, true));
		game.remove(this);

	}

	private void lose() {
		System.out.println("Losing");
		game.add(new WinScreen(game, player, false));
		game.remove(this);
	}

	public Content(Game parent, Player p, PrintWriter out) {
		int height = background.getIconHeight();
		int width = background.getIconWidth();
		Dimension a = new Dimension(width, height);
		addMouseListener(this);
		addKeyListener(this);
<<<<<<< HEAD

=======
		
		game.addKeyListener(this);
		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		blowupTemplate.setImage(template.getImage().getScaledInstance((int) BLOWUP_CARD_WIDTH, BLOWUP_CARD_HEIGHT, Image.SCALE_DEFAULT));
		green.setImage(green.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
		red.setImage(red.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
		screen.setImage(screen.getImage().getScaledInstance((int) 1200,	 800, Image.SCALE_DEFAULT));
>>>>>>> master
		setPreferredSize(a);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
		setFocusable(true);
		game.setFocusable(true);
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

<<<<<<< HEAD
=======
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
			if(startTurn == true) {
				opponent = new GamePlayer(2);
				you = new GamePlayer(1);
				mana = 1;
				maxMana = 1;
			} else {
				opponent = new GamePlayer(1);
				you = new GamePlayer(2);
			}

			clear = true;
			game.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
			this.removeAll();

			add(field);
			buttons.add(endTurn);
			endTurn.addActionListener(this);
			buttons.add(attack);
			attack.addActionListener(this);
			buttons.add(block);
			block.addActionListener(this);
			this.resetKeyboardActions();
			buttons.add(manaLabel);
			add(buttons);
			add(manaLabel);
			//add(handPanel);
			int x;
			int y;
			for(Integer i = 0; i < 5; i++) {
				Card ca = cardsData.getCardFromID(deck.get(i));
				x = i*120 + 150;
				y = HAND_Y_POSITION;
				HandCard c = new HandCard(x, y, x + 120, y + 170,ca,i);
				handCards.add(c);
			}
			for(int a = 0; a < 5; a++) {
				deck.remove(0);
			}
			add(manaLabel);
			this.revalidate();
		} else if(e.getSource().equals(endTurn)) {
			if(turn == true) {
				System.out.println("Ending Turn?");
				turn = false;
				bus.callEvent(new TurnEndedEvent(you));
				output.println("--turn");
				output.flush();
			}
		} else if(e.getSource().equals(attack) && turn == true && blocking == false) {

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

		} else if(e.getSource().equals(block) && blocking == true) {
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

		bus.callEvent(new DamageEvent(a2, myCreatures.get(c1)));
		bus.callEvent(new DamageEvent(a1, enemyCreatures.get(c2)));
		repaint();
		if(myCreatures.get(c1).getHealth() <= 0) {
			bus.callEvent(new CreatureKilledEvent(myCreatures.get(c1), this, true));

		}
		if(enemyCreatures.get(c2).getHealth() <= 0) {
			bus.callEvent(new CreatureKilledEvent(enemyCreatures.get(c2), this, false));
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
			if(isManaTurn(turnNum)) {
				maxMana++;
			}
			mana = maxMana;
			System.out.println("My Turn! :)");
			drawCard();
			bus.callEvent(new TurnStartedEvent(you));
			repaint();
			for(int i = 0; i < arrivalCreatures.size(); i=0) {
				bus.callEvent(new CreaturePlayedEvent(arrivalCreatures.get(i), this));
			}
			System.out.println("You have " + mana + " mana.");
			manaLabel.setText(mana.toString());
		} else if(m.startsWith("--myBoard")) {

			try {
				Card c;
				c = cardsData.getCardFromID(Integer.parseInt(m.substring(10)));

				InPlayCreature ic = new InPlayCreature( (CreatureCard) c);
				enemyCreatures.add(ic);
				cardsInPlay.add(ic);

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
		} else if(m.startsWith("--wi")) {
			lose();
		} else if(m.startsWith("--spellPlayed")) {
			int spellId = Integer.parseInt(m.substring(m.indexOf(" ") + 1, m.lastIndexOf(" ")));

			String maybe = m.substring(m.lastIndexOf(" ") + 1);
			if(maybe != "") {
				int targetIndex = Integer.parseInt(maybe);
				bus.callEvent(new TargetedSpellPlayedEvent<InPlayCreature>((SpellCard) cardsData.getCardFromID(spellId), this, cardsInPlay.get(targetIndex)));
			} else {
				bus.callEvent(new UntargetedSpellPlayedEvent((SpellCard) cardsData.getCardFromID(spellId), this));
			}
		}
		repaint();
	}

	public boolean isManaTurn(int turnNum) {
		int current = 1;
		int past = 0;
		while(current < turnNum + 1) {
			int temp = current;
			current += past;
			past = temp;
			if(current == turnNum) {
				return true;
			}
		}
		return false;
	}

	public void matchScreen() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		output.println("--remPlay" + player.getUsername() + "|" + player.getRank());
		HashMap<String, int[]> deecks = player.getDecks();
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
			HandCard draw = new HandCard(x + 50, HAND_Y_POSITION, x + 170, 770, cardsData.getCardFromID(i), handCards.size());
			handCards.add(draw);
			bus.callEvent(new CardDrawnEvent(draw.getCard()));
			return i;
		} else {
			return 1;
		}
		
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
		String text = card.getText();
		String cost = Integer.toString(card.getCost());

		template.paintIcon(handPanel, g, x, y);
		img.paintIcon(handPanel, g, x + 12, y + 25);
		g.drawString(text, x + 10, y + 120);
		g.setFont(f12);
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
		if(card.getClass().equals(SpellCard.class)) {
			SpellCard sc = (SpellCard) card;
			String name = sc.getName();
			//g.setFont(new Font("Helvetica", Font.PLAIN, 11)); 
			g.setFont(f8);
			g.drawString(name, x + 25, y + 23);
		}
	}


	public void paintInPlayCreature(InPlayCreature c, Graphics g, int x, int y) {


		if(c.isGreen()) {
			green.paintIcon(handPanel, g, x, y);
		}
		if(c.isRed()) {
			red.paintIcon(handPanel, g, x, y);
		}
		ImageIcon img = c.getCard().getImageIcon();


		//img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
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
		InPlayCreature c = getClick(a);
		if(c != null && myCreatures.contains(c)) {
			if(!attacking.contains(c)) {
				attacking.add(c);
				c.setGreen(true);
			} else {
				c.setGreen(false);
				attacking.remove(c);
			}
			selectedCard = c;
			System.out.println("adding");

			int i = myCreatures.indexOf(c);

			myCreatures.set(i, c);

		}

		if(getClick(a) != null && e.getButton() == MouseEvent.BUTTON2) {
			//Display closeup of card! :D
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


		for(int i = 0; i < handCards.size(); i++) {
			if(handCards.get(i).containsPoint(a)) {
				if(turn == true || blocking == true) {
					cd = new HandCardDragger();
					selectedHandCard = handCards.get(i);
					cd.execute();
				}
			}
		}

		if(blocking) {
			InPlayCreature c = getClick(a);
			if(c != null) {
				bd = new BlockCardDragger();
				blocker = c;
				bd.execute();
			}
		}
		
		if(targetingAbility == true) {
			if(getClick(a) != null) {
				 System.out.println("Targeted ability is activating!");
				bus.callEvent(new AbilityEvent(selectedCard, getClick(a), this));
				targetingAbility = false;
			}
		}
		
		if(getClick(a) != null && e.getButton() == MouseEvent.BUTTON2) {
			
		}
	}
	
	public void displayBlowup(Point p, InPlayCreature a) {
		
	}

	public boolean boardContainsPoint(Point p) {
		return p.y < 550;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released");
		Point a = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(a, game);

		if(!(cd.isStopped())) {
			System.out.println("Mouse released on a card drag");
			if(selectedHandCard.getCard() instanceof CreatureCard && mana >= selectedHandCard.getCard().getCost()) {
				System.out.println("Playing Creature: " + selectedHandCard);
				if(boardContainsPoint(selecCardPoint)) {
					arrivalHelper();
					mana -= selectedHandCard.getCard().getCost();
				}
				repaint();
			} else if(selectedHandCard.getCard() instanceof SpellCard && mana >= selectedHandCard.getCard().getCost()) {
				System.out.println("Playing a spellCard");
				SpellCard sc = (SpellCard) selectedHandCard.getCard();
				if(getClick(a) != null) {
					if(sc.hasTarget() && sc.meetsSpellRequirements(getClick(a))) {
						System.out.println("playing a targeted spell on a target");
						output.println("--spellPlayed " + selectedHandCard.getCard().getID() + " " + cardsInPlay.indexOf(getClick(a)));
						output.flush();
						EventBus.getInstance().callEvent(new TargetedSpellPlayedEvent<InPlayCreature>(sc, this, getClick(a)));
						handCards.remove(selectedHandCard);
						mana -= selectedHandCard.getCard().getCost();

					}
				}

				if(!sc.hasTarget()) {
					System.out.println("playing an untargeted spell");
					output.println("--spellPlayed " + selectedHandCard.getCard().getID() + " ");
					output.flush();
					EventBus.getInstance().callEvent(new UntargetedSpellPlayedEvent(sc, this));
					handCards.remove(selectedHandCard);
					mana -= selectedHandCard.getCard().getCost();

				}
			}
			cd.stop();
			selectedHandCard = null;

		}

		if(!(bd.isStopped())) {

			if(attackingEnemys.contains(getClick(a))) {
				blockers.put(blocker, getClick(a));
			}
		}
		bd.stop();
		manaLabel.setText(mana.toString());
	}

	public InPlayCreature getClick(Point p) {
		try {
			if(p.y > 400 && p.y < 600) {
				//Clicked on your creature lane
				int offset = (game.getWidth()/2) - myCreatures.size()*(CARD_WIDTH + 5)/2 - (CARD_WIDTH/2);
				int index = (p.x-offset)/120;
				return myCreatures.get(index);

			}
			if(p.y > 120 && p.y < 300) {
				//Clicked on enemy creature lane
				int offset = (game.getWidth()/2) - myCreatures.size()*(CARD_WIDTH + 5)/2 - (CARD_WIDTH/2);
				int index = (p.x-offset)/120;
				return enemyCreatures.get(index);

			}
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Jeally!");
		}
		return null;
	}

	public void arrivalHelper() {
		arrivalCreatures.add((CreatureCard) selectedHandCard.getCard());
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


	public void addCreature(InPlayCreature n) {
		cardsInPlay.add(n);
		myCreatures.add(n);
		//arrivalLanes.remove(arrivalCreatures.indexOf(n));
		//arrivalCreatures.remove(n);
		n.registerListeners();

		String send = "--myBoard " + cardsData.getCollection().indexOf(n.getCard());

		isMtn1Full = false;
		isMtn2Full = false;

		//should send something like --myBoard 1|2
		System.out.println("Sending " + send);
		output.println(send);
		output.flush();
	}

	public void paintInPlayCreatures(Graphics g) {

		/*List<InPlayCreature> k = myCreatures;
		for(int i = 0; i < k.size(); i++) {
			if(k.get(i).equals(blocker)) {
				paintInPlayCreature(k.get(i), g, (int) blockCardPoint.getX(), (int) blockCardPoint.getY());
			} else {
				paintInPlayCreature(k.get(i), g, 50 + i*120, 400);
			}
		}

		List<InPlayCreature> b = enemyCreatures;
		for(int i = 0; i < b.size(); i++) {
			paintInPlayCreature(b.get(i), g, 50 + i*120, 120);
		}*/
		
		
		List<InPlayCreature> k = myCreatures;
		
		int offset = (game.getWidth()/2) - k.size()*(CARD_WIDTH + 5)/2 - (CARD_WIDTH/2);
		for(int i = 0; i < k.size(); i++) {
			if(k.get(i).equals(blocker)) {
				paintInPlayCreature(k.get(i), g, (int) blockCardPoint.getX(), (int) blockCardPoint.getY());
			} else {
				paintInPlayCreature(k.get(i), g, 50 + i*120 + offset, 400);
			}
		}
		
		List<InPlayCreature> b = enemyCreatures;
		int offset2 = game.getWidth() - b.size()*(CARD_WIDTH + 5)/2 - (CARD_WIDTH/2);
		for(int i = 0; i < b.size(); i++) {
			paintInPlayCreature(b.get(i), g, 50 + i*120 + offset, 120);
		}
		
		
	}

	public void paintHandCards(Graphics g) {

		int x;
		int y;
		int offset = (game.getWidth()/2) - handCards.size()*(CARD_WIDTH + 5)/2 - (CARD_WIDTH/2);
//		for(int i = 0; i < handCards.size(); i++) {
//			x = i*120 + offset;
//			y = HAND_Y_POSITION;
//			HandCard h = handCards.get(i);
//			h.setStartX(x);
//			h.setStartY(y);
//			h.setEndX(x + 120);
//			h.setEndY(y + 170);
//			handCards.set(i, h);
//		}

		for(int i = 0; i < handCards.size(); i++) {

			x = i*(CARD_WIDTH + 5) + offset;
			y = HAND_Y_POSITION;
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
				paintCreature(handCards.get(i).getCard(), g, x, y);
			}
		}
	}

	public void paintArrivals(Graphics g) {
		
		boolean foo = false;
		
		for(int i = 0; i < arrivalCreatures.size(); i++) {
			CreatureCard c = arrivalCreatures.get(i);
			if(! isMtn1Full) {
				paintCreature(c, g, MOUNTAIN_1_X, ARRIVAL_CREATURE_Y);
				isMtn1Full = true;
			} else if(! isMtn2Full){
				paintCreature(c, g, MOUNTAIN_2_X, ARRIVAL_CREATURE_Y);
				isMtn1Full = true;
			} else if(!foo){
				paintCreature(c, g, MOUNTAIN_1_X, ARRIVAL_CREATURE_Y_2);
				foo = true;
			} else {
				paintCreature(c, g, MOUNTAIN_2_X, ARRIVAL_CREATURE_Y_2);
			}

		}
		isMtn1Full = false;
		isMtn2Full = false;
		foo = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Meanie!" + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override 
	public void keyTyped(KeyEvent e) {
		System.out.println("Meanie!" + e.getKeyChar());
		if(e.getKeyCode() == KeyEvent.VK_W && selectedCard != null) {
			System.out.println("Calling AbilityEvent");
			if(!selectedCard.getCard().getAbility().hasTarget()) {
				 System.out.println("Untargeted ability is activating!");
				bus.callEvent(new AbilityEvent(selectedCard, null, this));
			} else {
				 System.out.println("Targeted ability is targeting");
				targetingAbility  = true;
			}
			attacking.remove(selectedCard);

		}
	}

>>>>>>> master
}
