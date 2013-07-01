package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class WindBook extends Book {

	@Override
	public boolean isEffectiveness(Fighter fighter) {
		if(fighter instanceof Human) {
			Human human = (Human) fighter;
			Weapon humanWeapon = human.getEquiped();
			if(humanWeapon != null && humanWeapon instanceof LightBook) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canEquip(Human human) {
		return human.getWeaponClass(WINDBOOK) >= weaponClass;
	}
	
	@Override
	public int getWeaponType() {
		return WINDBOOK;
	}
}