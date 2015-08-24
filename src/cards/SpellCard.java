package cards;

public class SpellCard extends Card {

	SpellRunnable effect;
	String text;
	int cost;
	String name;
	boolean hasTarget;
	
	public SpellCard(String name, int cost, String text, boolean hasTarget, SpellRunnable effect) {
		this.name = name;
		this.cost = cost;
		this.effect = effect;
		this.text = text;
		this.hasTarget = hasTarget;
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
}
