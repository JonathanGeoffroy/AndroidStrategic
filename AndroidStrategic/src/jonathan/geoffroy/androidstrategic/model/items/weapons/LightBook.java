package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class LightBook extends Book {

	@Override
	public boolean canEquip(Human human) {
		return human.getLightBookClass() >= weaponClass;
	}
}