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
	int id;
	RequirementRunnable requirement;
	
	public SpellCard(String name, int cost, String text, boolean hasTarget, ImageIcon image, int id, SpellRunnable effect, RequirementRunnable r) {
		this.name = name;
		this.cost = cost;
		this.effect = effect;
		this.text = text;
		requirement = r;
		this.id = id;
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
	
	@Override
	public int getID() {
		return id;
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
	
	public boolean meetsSpellRequirements(InPlayCreature target) {
		return requirement.run(target);
	}
}
