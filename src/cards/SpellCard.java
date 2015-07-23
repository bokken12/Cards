package cards;

public class SpellCard extends Card {

	SpellRunnable effect;
	String text;
	int cost;
	String name;
	
	public SpellCard(String name, int cost, String text, SpellRunnable effect) {
		this.name = name;
		this.cost = cost;
		this.effect = effect;
		this.text = text;
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
}
