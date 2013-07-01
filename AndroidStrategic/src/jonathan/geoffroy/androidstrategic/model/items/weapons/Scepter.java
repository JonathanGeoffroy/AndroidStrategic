package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Scepter extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getWeaponClass(SCEPTER) >= weaponClass;
	}

	@Override
	public int getType() {
		return 6;
	}
	
	@Override
	public int getWeaponType() {
		return SCEPTER;
	}
}