package cards;

import javax.swing.ImageIcon;
import abilities.Ability;
import abilities.InPlayRunnable;
import events.GameEvent;
import events.GameListener;

public class CreatureCard extends Card implements GameListener {
	int power;
	int toughness;
	int cost;
	String name;
	Ability ability;
	boolean haste = false;
	String type;
	ImageIcon image;
	InPlayRunnable intoPlay = new InPlayRunnable() {
		
		public void run(InPlayCreature c) {
			
		}
	};
	
	public CreatureCard(String n, int p, int t, int c, ImageIcon img, Ability a, String type, int id) {
		
		power = p;
		name = n;
		toughness = t;
		cost = c; 
		ability = a;
		image = img;
	}

	public int getPower() {
		return power;
	}
	
	public void setPower(int a) {
		power = a;
	}
	
	public int getToughness() {
		return toughness;
	}
	
	public void setToughness(int a) {
		toughness = a;
	}
	
	public void registerListeners() {
		ability.RegisterListeners();
	}

	@Override
	public void passEvent(GameEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void giveHaste() {
		haste = true;
	}
	
	public boolean hasHaste() {
		return haste;
	}
	
	public String getAbilityText() {
		return ability.getText();
	}
	
	public void setIntoPlay(InPlayRunnable a) {
		intoPlay = a;
	}
	
	public InPlayRunnable getIntoPlay() {
		return intoPlay;
		
	}
	
	@Override
	public ImageIcon getImageIcon() {
		return image;
		
	}
	
	public int getCost() {
		return cost;
	}

	
}
