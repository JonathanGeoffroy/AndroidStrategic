package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Bow extends Weapon {
	public Bow() {
		super();
		minRange = maxRange = 2;
	}
	
	@Override
	public boolean canEquip(Human human) {
		return human.getBowClass() >= weaponClass;
	}
}