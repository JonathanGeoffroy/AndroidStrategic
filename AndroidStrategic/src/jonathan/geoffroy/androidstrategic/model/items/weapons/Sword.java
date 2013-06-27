package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Sword extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getSwordClass() >= weaponClass;
	}

	@Override
	public int getType() {
		return 8;
	}
}