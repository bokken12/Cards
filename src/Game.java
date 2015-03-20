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
	JButton play2 = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");


	JPanel content = (JPanel) this.getContentPane();
	private static final Dimension SIZE = new Dimension(400, 350);
	private static final Dimension SIZE2 = new Dimension(4000, 3500);
	
	public Game(Player player) {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(SIZE);
		panel.setMaximumSize(SIZE2);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("MenuBackground.jpg"));
		} catch (IOException e) {
			System.out.println("wohtpoge");
		}
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		panel.add(label);
		
		play.addActionListener(this);
		cards.addActionListener(this);
		settings.addActionListener(this);
		play2.addActionListener(this);
		
		
		add(play);
		add(cards);
		add(settings);
		
		content.add(panel);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(play)) {
			
		} else if(e.getSource().equals(cards)) {
			
		} else if(e.getSource().equals(settings)) {
			
		} else if(e.getSource().equals(play2)) {
			autoMatch();
		} 
	}
	
	public void playMenu() {
		
	}
	
	public void autoMatch() {
		
	}
	
}
