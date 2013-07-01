package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public class Archer extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 18;
		attributes[STRENGTH] = 3;
		attributes[SPEED] = 3;
		attributes[SKILL] = 6;
		attributes[DEFENSE] = 4;
		attributes[LUCK] = 0;
		attributes[RESISTANCE] = 0;
		movement = attributes[MOVEMENTMAX] = 1;
		attributes[CONSTITUTION] = 9;
		weight = 9;
		
		weaponClass[Weapon.BOW] = 1;
	}

	@Override
	protected String defaultName() {
		return "Archer";
	}

}
