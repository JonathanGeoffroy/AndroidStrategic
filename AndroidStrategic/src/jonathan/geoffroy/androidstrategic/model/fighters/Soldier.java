package	 jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public class Soldier extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 22;
		attributes[STRENGTH] = 4;
		attributes[MAGIC] = 0;
		attributes[SKILL] = 4;
		attributes[SPEED] = 4;
		attributes[LUCK] = 6;
		attributes[DEFENSE] = 4;
		attributes[RESISTANCE] = 0;
		attributes[CONSTITUTION] = 8;
		weight = 13;
		movement = attributes[MOVEMENTMAX] = 6;
		
		weaponClass[Weapon.SPEAR] = 1;
	}

	@Override
	protected String defaultName() {
		return "Soldier";
	}
}