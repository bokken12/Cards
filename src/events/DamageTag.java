package events;

import uselessSubclasses.DamageableEntity;
import cards.Card;

public class DamageTag extends TagEvent{
	int damage = 0;
	DamageableEntity target = null;
	public DamageTag(int inputDamage, DamageableEntity inputTarget){
		for(int i = 0; i < 20; i++){
			for(Card listening: listeners.get(i)){
				listening.receiveEvent(this);
			}
		}
	}
	public DamageableEntity getTarget(){
		return target;
	}
	public int getDamage(){
		return damage;
	}
	public void setTarget(DamageableEntity inputTarget){
		target = inputTarget;
	}
	public void setDamage(int inputDamage){
		damage = inputDamage;
	}
}
