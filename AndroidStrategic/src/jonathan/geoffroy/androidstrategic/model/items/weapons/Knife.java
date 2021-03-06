package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Knife extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getWeaponClass(KNIFE) >= weaponClass;
	}

	@Override
	public int getType() {
		return 5;
	}
	
	@Override
	public int getWeaponType() {
		return KNIFE;
	}
}