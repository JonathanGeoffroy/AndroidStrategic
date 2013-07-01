package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Bow extends Weapon {
	public Bow() {
		super();
		minRange = maxRange = 2;
	}
	
	@Override
	public boolean canEquip(Human human) {
		return human.getWeaponClass(BOW) >= weaponClass;
	}

	@Override
	public int getType() {
		return 9;
	}

	@Override
	public int getWeaponType() {
		return BOW;
	}
}