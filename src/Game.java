import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Game extends JFrame implements ActionListener{

	JPanel content = (JPanel) this.getContentPane();
	
	public Game() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		
		makeImage("MenuBackground.jpg");
		makeImage("playbutton.png");
		
		this.pack();
		this.setVisible(true);
	}

	public void makeImage(String image) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(image));
		} catch (IOException e) {
			System.out.println("wohtpoge");
		}
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(label);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public static void main(String[] args) {
		Game a = new Game();
	}

}
