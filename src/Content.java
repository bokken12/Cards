import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Content extends JPanel {
	
	ImageIcon background = new ImageIcon("MenuBackground.jpg");
	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.paintIcon(this, g, 0, 0);
		
	}
	
	public Content(Game parent) {
		
		int height = background.getIconHeight();
		int width = background.getIconWidth();
		
		Dimension a = new Dimension(width, height);
		
		setPreferredSize(a);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createHorizontalGlue());
		JPanel foo = new JPanel();
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
		
		play.addActionListener(parent);
		cards.addActionListener(parent);
		settings.addActionListener(parent);
	}
}
