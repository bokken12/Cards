import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

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


public class Content extends JPanel implements ActionListener {

	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	JPanel foo = new JPanel();
	JScrollPane decklist = new JScrollPane();
	PrintWriter output;
	Player player;
	boolean paint1 = false;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(paint1) {

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
			output.println("--Playing" + player.getUsername() + " " + player.getRank());
			playMenu();
			
			
		} else if(e.getSource().equals(cards)) {
			CardsMenu();

		} else if(e.getSource().equals(settings)) {

		} else if(e.getSource().equals(decklist)) {
			
		}


	}

	public void playMenu() {

		System.out.println("PlayMenu");

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
}
