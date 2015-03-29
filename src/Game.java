import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Player;



public class Game extends JFrame implements ActionListener{
	
	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");
	JLabel label;

	Player player;

	JPanel content = (JPanel) this.getContentPane();
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	private static final Dimension SIZE = new Dimension(400, 350);
	private static final Dimension SIZE2 = new Dimension(400, 350);
	
	public Game(Player playyer) {
		super();
		player = playyer;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setPreferredSize(SIZE);
		panel.setMaximumSize(SIZE2);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("MenuBackground.jpg"));
		} catch (IOException e) {
			System.out.println("wohtpoge");
		}
		ImageIcon icon = new ImageIcon(img);
		label = new JLabel(icon);
		panel.add(label);
		
		play.addActionListener(this);
		cards.addActionListener(this);
		settings.addActionListener(this);
		
		
		panel.add(play);
		panel.add(cards);
		panel.add(settings);
		
		content.add(panel);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(play)) {
			playMenu();
		} else if(e.getSource().equals(cards)) {
			
		} else if(e.getSource().equals(settings)) {
			
		}
			
		
	}
	
	public void playMenu() {
		panel2.add(label);
		
		
		
		content.add(panel2);
		content.remove(panel);
		this.pack();
	}
	
	public void autoMatch() {
		
	}
	
}
