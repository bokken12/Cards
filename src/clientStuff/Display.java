package clientStuff;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Player.Player;
import uselessSubclasses.Lane;
import cards.HandCard;
import cards.InPlayCreature;

public class Display extends JPanel implements Constants
{
    private GameState gs;
    private JScrollPane decklist = new JScrollPane();
    private Graphics g;
    private PrintWriter output;
    private ImageIcon background = new ImageIcon("MenuBackground.jpg");
    private ImageIcon screen = new ImageIcon("CardScreen.png");
    private JButton play = new JButton("Play");
    private JButton settings = new JButton("Settings");
    private JButton cards = new JButton("Cards");
    private JButton endTurn = new JButton("End Turn");
    private JButton attack = new JButton("Attack");
    private JButton block = new JButton("Block");
    private ArrayList<JButton> deckButtons = new ArrayList<JButton>();
    private JPanel foo = new JPanel();
    private JPanel buttons = new JPanel();
    private JPanel field = new JPanel();
    private JPanel handPanel = new JPanel();
    private JPanel cardDragging = new JPanel();
    private Game game;
    private JLabel wait = new JLabel();
    private JLabel manaLabel = new JLabel(mana.toString());

    public Display(GameState gamestate)
    {
        gs = gamestate;
    }

    public void paintComponent(Graphics g)
    {

        // System.out.println("Repainting");
        super.paintComponent(g);
        this.g = g;
        if (clear)
        {
            screen.setImage(screen.getImage().getScaledInstance((int) 1200,
                    800, Image.SCALE_DEFAULT));
            screen.paintIcon(this, g, 0, 0);
            paintInPlayCreatures(g);
            paintHandCards(g);
            paintArrivals(g);
            Font f = new Font("FONT", 2, 30);

            g.setFont(f);
            g.drawString(Integer.toString(enemyHealth), 500, 50);

        }
        else
        {
            background.paintIcon(this, g, 0, 0);
        }
    }

    public void paintInPlayCreature(InPlayCreature c, Graphics g, int x, int y)
    {

        if (c.isGreen())
        {
            ImageIcon i = new ImageIcon("green.png");
            i.setImage(i.getImage().getScaledInstance((int) 122, 172,
                    Image.SCALE_DEFAULT));
            i.paintIcon(handPanel, g, x, y);
        }
        if (c.isRed())
        {
            ImageIcon i = new ImageIcon("red.png");
            i.setImage(i.getImage().getScaledInstance((int) 122, 172,
                    Image.SCALE_DEFAULT));
            i.paintIcon(handPanel, g, x, y);
        }
        ImageIcon img = c.getCard().getImageIcon();
        ImageIcon template = new ImageIcon("CreatureTemplate.png");
        template.setImage(template.getImage().getScaledInstance((int) 120, 170,
                Image.SCALE_DEFAULT));
        img.setImage(img.getImage().getScaledInstance((int) 92, 83,
                Image.SCALE_DEFAULT));
        String text = c.getCard().getText();
        String cost = Integer.toString(c.getCard().getCost());

        template.paintIcon(handPanel, g, x, y);
        img.paintIcon(handPanel, g, x + 12, y + 25);
        g.drawString(text, x + 10, y - 40);
        g.drawString(cost, x + 100, y + 23);

        String name = c.getCard().getName();
        String power = Integer.toString(c.getPower());
        String health = Integer.toString(c.getHealth());
        g.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g.drawString(power, x + 20, y + 163);
        g.drawString(health, x + 92, y + 163);
        g.setFont(new Font("Helvetica", Font.PLAIN, 8));
        g.drawString(name, x + 25, y + 23);
        // System.out.println("Painting " + name + " with health " + health);
    }

    public void paintInPlayCreatures(Graphics g)
    {
        Lane[] lanes = gs.getLanes();
        for (int i = 0; i < lanes.length; i++)
        {
            paintLaneCreatures(lanes[i], i);
        }
    }

    private void paintLaneCreatures(Lane lane, int x)
    {
        ArrayList<InPlayCreature> p = lane.getCreatures();
        for (int i = 0; i < p.size(); i++)
        {
            if (p.get(i).equals(blocker))
            {
                paintInPlayCreature(p.get(i), g, (int) blockCardPoint.getX(),
                        (int) blockCardPoint.getY());
            }
            else
            {
                paintInPlayCreature(p.get(i), g, 400 * x + 55 + i * 115, 400);
            }
        }
        ArrayList<InPlayCreature> l = lane.getEnemyCreatures();
        for (int i = 0; i < l.size(); i++)
        {
            paintInPlayCreature(l.get(i), g, 400 * x + 55 + i * 115, 180);
        }
    }

    public void paintHandCards(Graphics g)
    {

        int x;
        int y;

        for (int i = 0; i < gs.getHandCards().size(); i++)
        {
            x = i * 120 + 50;
            y = 600;
            HandCard h = gs.getHandCards().get(i);
            h.setStartX(x);
            h.setStartY(y);
            h.setEndX(x + 120);
            h.setEndY(y + 170);
            gs.getHandCards().set(i, h);
        }

        for (int i = 0; i < gs.getHandCards().size(); i++)
        {

            x = i * (CARD_WIDTH + 5) + 50;
            y = 600;
            if (!(selectedHandCard == null))
            {
                if (!(selectedHandCard.equals(gs.getHandCards().get(i))))
                {

                    HandCard h = gs.getHandCards().get(i);
                    h.setStartX(x);
                    h.setStartY(y);
                    h.setEndX(x + (CARD_WIDTH + 5));
                    h.setEndY(y + 170);
                    gs.getHandCards().set(i, h);
                    paintCreature(gs.getHandCards().get(i).getCard(), g, x, y);
                }
                else
                {
                    // System.out.println("Painting selected card at: " +
                    // selecCardPoint);
                    paintCreature(gs.getHandCards().get(i).getCard(), g,
                            (int) selecCardPoint.getX(),
                            (int) selecCardPoint.getY());
                }
            }
            else
            {
                x = i * (CARD_WIDTH + 5) + 50;
                y = 600;
                paintCreature(gs.getHandCards().get(i).getCard(), g, x, y);
            }
        }
    }
}
