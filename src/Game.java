import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Player;
import Player.SimplePlayerProfile;



public class Game extends JFrame{

	JButton play = new JButton("Play");
	JButton settings = new JButton("Settings");
	JButton cards = new JButton("Cards");

	BufferedReader input;
	PrintWriter output;

	JLabel label;
	Content a;

	Player player;

	JPanel content = (JPanel) this.getContentPane();

	private static final Dimension SIZE = new Dimension(400, 350);
	private static final Dimension SIZE2 = new Dimension(400, 350);

	public Game(Player playyer, PrintWriter output) {

		super();
		player = playyer;
		a = new Content(this, player, output);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.output = output;
		content.add(a);
		this.pack();
		this.setVisible(true);

	}

	public void toContent(String s) {
		a.handleMessage(s);
	}
}
