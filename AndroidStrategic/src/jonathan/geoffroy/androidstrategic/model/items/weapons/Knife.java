package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Knife extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getKnifeClass() >= weaponClass;
	}
}