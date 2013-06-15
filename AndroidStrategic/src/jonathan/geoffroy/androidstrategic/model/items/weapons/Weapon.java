package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.items.Item;

public abstract class Weapon extends Item {
	protected int might;
	protected int weaponClass;

	public Weapon() {
		weaponClass = 1;
	}
	
	public int triangleMt(Weapon weapon) {
		return 0;
	}

	public int triangleHit(Weapon weapon) {
		return 0;
	}

	public boolean isEffectiveness(Fighter fighter) {
		return false;
	}

	public abstract boolean canEquip(Human human);

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

	public int getMight() {
		return might;
	}

	public void setMight(int might) {
		this.might = might;
	}

	public int getWeaponClass() {
		return weaponClass;
	}

	public void setWeaponClass(int weaponClass) {
		this.weaponClass = weaponClass;
	}

}