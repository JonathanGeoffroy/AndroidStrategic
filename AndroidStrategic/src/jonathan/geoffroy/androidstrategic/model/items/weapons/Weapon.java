package jonathan.geoffroy.androidstrategic.model.items.weapons;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.items.Item;

public abstract class Weapon extends Item {
	public final static int AX = 0, BOW = 1, FIREBOOK = 2, KNIFE = 3, LIGHTBOOK = 4, SCEPTER = 5, SPEAR = 6, SWORD = 7, WINDBOOK = 8;
	public final static int NB_WEAPONS_TYPE = 9;
	protected short might;
	protected short weaponClass;
	protected short hitRate;
	protected short minRange, maxRange;

	public Weapon() {
		minRange = maxRange = 1;
		weaponClass = 1;
	}

	public abstract int getWeaponType();
	
	public short getHitRate() {
		return hitRate;
	}

	public void setHitRate(short hitRate) {
		this.hitRate = hitRate;
	}

	protected short criticalRate;

	public int triangleMt(Weapon weapon) {
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

	public short getMight() {
		return might;
	}

	public void setMight(short might) {
		this.might = might;
	}

	public short getWeaponClass() {
		return weaponClass;
	}

	public void setWeaponClass(short weaponClass) {
		this.weaponClass = weaponClass;
	}

	public short getCriticalRate() {
		return criticalRate;
	}

	public void setCriticalRate(short criticalRate) {
		this.criticalRate = criticalRate;
	}

	public short getMinRange() {
		return minRange;
	}

	public void setMinRange(short minRange) {
		this.minRange = minRange;
	}

	public short getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(short maxRange) {
		this.maxRange = maxRange;
	}
}