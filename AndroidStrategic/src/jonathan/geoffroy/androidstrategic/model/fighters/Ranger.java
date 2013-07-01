package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public class Ranger extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 18;
		attributes[STRENGTH] = 6;
		attributes[MAGIC] = 1;
		attributes[SKILL] = 3;
		attributes[SPEED] = 4;
		attributes[LUCK] = 6;
		attributes[DEFENSE] = 5;
		attributes[RESISTANCE] = 0;
		movement = attributes[MOVEMENTMAX] = 6;
		attributes[CONSTITUTION] = 9;
		weight = 9;
		
		weaponClass[Weapon.SWORD] = 1;
	}

	@Override
	protected String defaultName() {
		return "Ranger";
	}
}