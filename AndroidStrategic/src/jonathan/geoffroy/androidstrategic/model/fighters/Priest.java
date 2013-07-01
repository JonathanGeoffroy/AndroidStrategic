package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public class Priest extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 18;
		attributes[STRENGTH] = 0;
		attributes[MAGIC] = 3;
		attributes[SKILL] = 3;
		attributes[SPEED] = 3;
		attributes[LUCK] = 8;
		attributes[DEFENSE] = 1;
		attributes[RESISTANCE] = 8;
		attributes[CONSTITUTION] = 9;
		weight = 9;
		movement = attributes[MOVEMENTMAX] = 5;
		
		weaponClass[Weapon.SCEPTER] = 1;
	}

	@Override
	protected String defaultName() {
		return "Priest";
	}
	
	protected boolean isPhysicalAttack() {
		return false;
	}
}