package cards;

import abilities.AbilityRunnable;

public class SpellCard extends Card {

	AbilityRunnable effect;
	String text;
	int cost;
	String name;
	
	public SpellCard(String name, int cost, String text, AbilityRunnable effect) {
		this.name = name;
		this.cost = cost;
		this.effect = effect;
		this.text = text;
	}

	public AbilityRunnable getEffect() {
		return effect;
	}

	public void setEffect(AbilityRunnable effect) {
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
