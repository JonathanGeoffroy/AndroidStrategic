package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Ax extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getAxClass() >= weaponClass;
	}

}