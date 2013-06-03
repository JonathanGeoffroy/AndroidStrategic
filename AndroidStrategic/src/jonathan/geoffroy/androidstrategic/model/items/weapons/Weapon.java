package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.items.Item;

public abstract class Weapon extends Item {
	protected int might;
	private int weaponClass;

	public int triangleMt(Weapon weapon) {
		return 0;
	}

	public int triangleHit(Weapon weapon) {
		return 0;
	}

	public boolean isEffectiveness(Fighter fighter) {
		return false;
	}

	public boolean canEquip(Human human) {
		return false;
	}

	public String getCharClass() {
		char c = (char) ('E' - weaponClass);
		char chars[] = new char[1];
		chars[0] = c;
		assert(c >= 'A' && c <= 'E');
		
		return new String(chars);
	}

	public int calculateMight() {
		return 0;
	}

	public String getCharClass(int weaponclass) {
		return null;
	}

}