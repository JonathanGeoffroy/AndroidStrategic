package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Scepter extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getScepterClass() >= weaponClass;
	}

	@Override
	public int getType() {
		return 6;
	}
}