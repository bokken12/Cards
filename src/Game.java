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

	public Game() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel content = (JPanel) this.getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("MenuBackground.jpg"));
		} catch (IOException e) {
			System.out.println("wohtpoge");
		}
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(label);
		
		this.pack();
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public static void main(String[] args) {
		Game a = new Game();
	}

}
