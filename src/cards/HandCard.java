package cards;

public class HandCard {
	int startX;
	int startY;
	int endX;
	int endY;
	Card card;
	int i;
	
	public HandCard(int x, int y, int x2, int y2, Card card, int i) {
		this.card = card;
		this.startX = x;
		this.startY = y;
		this.endX = x2;
		this.endY = y2;
		this.i = i;
		
	}
	
	public Card getCard() {
		return card;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

}
