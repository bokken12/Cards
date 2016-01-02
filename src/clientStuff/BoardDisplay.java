package clientStuff;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import cards.Card;
import cards.CreatureCard;
import cards.InPlayCreature;
import cards.SpellCard;
import uselessSubclasses.Lane;

public class BoardDisplay extends JPanel
{

	ImageIcon screen;
	private JButton attack;
	private JButton block;
	private Board board;

	public BoardDisplay(Board toDisplay){

		board = toDisplay;

		screen = new ImageIcon("CardScreen.png");
	}
	public Board getDisplaying()
	{
		return board;
	}
	public void setDisplaying(Board displaying)
	{
		this.board = displaying;
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(screen.getImage(), 0, 0, this);

		paintInPlayCreatures(g);
	}

	public void paintCreature(Card card, Graphics g, int x, int y) {

		ImageIcon img = card.getImageIcon();
		ImageIcon template = new ImageIcon("CreatureTemplate.png");
		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		String text = card.getText();
		String cost = Integer.toString(card.getCost());


		template.paintIcon(this, g, x, y);
		img.paintIcon(this, g, x + 12, y + 25);
		g.drawString(text, x + 10, y - 40);
		g.drawString(cost, x + 100, y + 23);

		if(card.getClass().equals(CreatureCard.class)) {
			CreatureCard cc = (CreatureCard) card;
			String name = cc.getName();
			String power = Integer.toString(cc.getPower());
			String health = Integer.toString(cc.getToughness());
			g.setFont(new Font("Helvetica", Font.PLAIN, 12)); 
			g.drawString(power, x + 20, y + 163);
			g.drawString(health, x + 92, y + 163);
			g.setFont(new Font("Helvetica", Font.PLAIN, 8)); 
			g.drawString(name, x + 25, y + 23);
			//System.out.println("Painting " + name + " with health " + health);
		}
		if(card.getClass().equals(SpellCard.class)) {
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
			i.paintIcon(this, g, x, y);
		}
		if(c.isRed()) {
			ImageIcon i = new ImageIcon("red.png");
			i.setImage(i.getImage().getScaledInstance((int) 122, 172, Image.SCALE_DEFAULT));
			i.paintIcon(this, g, x, y);
		}
		ImageIcon img = c.getCard().getImageIcon();
		ImageIcon template = new ImageIcon("CreatureTemplate.png");
		template.setImage(template.getImage().getScaledInstance((int) 120, 170, Image.SCALE_DEFAULT));
		img.setImage(img.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
		String text = c.getCard().getText();
		String cost = Integer.toString(c.getCard().getCost());


		template.paintIcon(this, g, x, y);
		img.paintIcon(this, g, x + 12, y + 25);
		//g.drawString(text, x + 10, y - 40);
		g.drawString(cost, x + 100, y + 23);

		String name = c.getCard().getName();
		String power = Integer.toString(c.getPower());
		String health = Integer.toString(c.getHealth());
		g.setFont(new Font("Helvetica", Font.PLAIN, 12)); 
		g.drawString(power, x + 20, y + 163);
		g.drawString(health, x + 92, y + 163);
		g.setFont(new Font("Helvetica", Font.PLAIN, 8)); 
		g.drawString(name, x + 25, y + 23);
		//System.out.println("Painting " + name + " with health " + health);



	}

	public void paintCloseUpCreature() {
		//TODO
	}

	public void paintInPlayCreatures(Graphics g) {
		for(int i = 0; i < board.getLane(board.getCurrentLane()).getCreatures().size(); i++) {
			paintInPlayCreature(board.getLane(board.getCurrentLane()).getCreatures().get(i), g, 234, 199);
		}
	}
	
	public void paintHandCards(Graphics g) {
		
	}
}
