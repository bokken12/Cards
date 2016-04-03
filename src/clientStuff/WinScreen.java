package clientStuff;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Player.Player;

public class WinScreen extends JPanel implements MouseListener {

	Game game;
	Player player;
	ImageIcon background = new ImageIcon("background.png");
	boolean won;

	public WinScreen(Game g, Player p, boolean won) {
		player = p;
		game = g;
		this.won = won;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, this);
		if(won) {
			g.drawString("You Won!", 300, 300);
		} else {
			g.drawString("You Lose.", 300, 300);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.newContent(player);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
