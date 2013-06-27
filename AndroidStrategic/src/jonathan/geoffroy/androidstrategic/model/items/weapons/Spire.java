package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Spire extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getSpearClass() >= weaponClass;
	}

	@Override
	public int getType() {
		return 7;
	}
}