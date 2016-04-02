package cards;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SpellCard extends Card {

	SpellRunnable effect;
	String text;
	int cost;
	String name;
	boolean hasTarget;
	ImageIcon image;
	
	public SpellCard(String name, int cost, String text, boolean hasTarget, ImageIcon image, int id, SpellRunnable effect) {
		this.name = name;
		this.cost = cost;
		this.effect = effect;
		this.text = text;
		this.hasTarget = hasTarget;
		this.image = image;
		this.image.setImage(image.getImage().getScaledInstance((int) 92, 83, Image.SCALE_DEFAULT));
	}

	public SpellRunnable getEffect() {
		return effect;
	}

	public void setEffect(SpellRunnable effect) {
		this.effect = effect;
	}

	public String getText() {
		return text;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public boolean hasTarget() {
		return hasTarget;
	}

	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}
	
	@Override
	public ImageIcon getImageIcon() {
		return image;
	}
}
