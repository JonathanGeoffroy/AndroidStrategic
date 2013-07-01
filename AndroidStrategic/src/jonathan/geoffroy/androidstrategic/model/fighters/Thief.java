package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public class Thief extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 16;
		attributes[STRENGTH] = 4;
		attributes[MAGIC] = 0;
		attributes[SKILL] = 2;
		attributes[SPEED] = 9;
		attributes[LUCK] = 5;
		attributes[DEFENSE] = 2;
		attributes[RESISTANCE] = 0;
		movement = attributes[MOVEMENTMAX] = 7;
		attributes[CONSTITUTION] = 9;
		weight = 9;
		
		weaponClass[Weapon.KNIFE] = 1;
	}

	@Override
	protected String defaultName() {
		return "Thief";
	}
}