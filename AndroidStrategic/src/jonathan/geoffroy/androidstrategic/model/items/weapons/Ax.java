package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;

public class Ax extends Weapon {

	@Override
	public boolean canEquip(Human human) {
		return human.getWeaponClass(AX) >= weaponClass;
	}

	@Override
	public int getType() {
		return 3;
	}
	
	public boolean isEffectiveness(Fighter fighter) {
		if(fighter instanceof Human) {
			Human human = (Human) fighter;
			Weapon humanWeapon = human.getEquiped();
			if(humanWeapon != null && humanWeapon instanceof Spear) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getWeaponType() {
		return AX;
	}
}