package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public abstract class Human extends Fighter {
	public static final int WEAPON_EXP_LVL = 20;
	protected Weapon equiped;
	protected short[] weaponClass;
	protected short[] weaponExp;

	
	@Override
	public int hitRate(Fighter fighter) {
		int rate = super.hitRate(fighter);
		if(equiped != null) {
			rate += equiped.getHitRate();
			if(equiped.isEffectiveness(fighter)) {
				rate += (rate * 20) / 100;
			}
			else if(fighter instanceof Human) {
				Human human = (Human)fighter;
				Weapon weapon = human.getEquiped();
				if(weapon != null && weapon.isEffectiveness(this)) {
					rate -= (rate * 20) / 100;
				}
			}
		}
		return rate;
	}

	@Override
	protected void initializeStats() {
		super.initializeStats();
		weaponClass = new short[Weapon.NB_WEAPONS_TYPE];
		weaponExp = new short[Weapon.NB_WEAPONS_TYPE];
	}

	@Override
	public short criticalRate() {
		short critical = super.criticalRate();
		if(equiped != null) {
			critical += equiped.getCriticalRate();
		}
		return critical;
	}

	@Override
	public int calculateSpeed() {
		int speed = super.calculateSpeed();
		int weaponSpeed = 0;
		if(equiped != null) {
			weaponSpeed = equiped.getWeight() - attributes[STRENGTH];
			if(weaponSpeed < 0)
				weaponSpeed = 0;
		}
		return speed - weaponSpeed;
	}

	@Override
	public int calculatePower(Fighter fighter) {
		int strength = super.calculatePower(fighter);
		if(equiped != null) {
			strength += equiped.getMight();
			if(equiped.isEffectiveness(fighter)) {
				strength += 1;
			}
			else if (fighter instanceof Human) {
				Human human = (Human)fighter;
				Weapon weapon = human.getEquiped();
				if(weapon != null && weapon.isEffectiveness(this)) {
					strength -= 1;
				}
			}
		}
		return strength;
	}

	@Override
	public int minRange() { 
		if(equiped != null) {
			return equiped.getMinRange();
		}
		return super.minRange();
	}

	@Override
	public int maxRange() {
		if(equiped != null) {
			return equiped.getMaxRange();
		}
		return super.maxRange();
	}

	@Override
	public int weaponExeperienceWon(Fighter fighter) {
		short exp = 0;
		
		if(equiped != null) {
			exp = (short) this.hitNumber(fighter);
			incWeaponExp(equiped.getWeaponType(), exp);
		}	
		return exp;
	}
	public Weapon getEquiped() {
		return equiped;
	}
	public void setEquiped(Weapon equiped) {
		this.equiped = equiped;
	}
	public short getWeaponClass(int weaponType) {
		return weaponClass[weaponType];
	}
	public void setWeaponClass(int weaponType, short value) {
		weaponClass[weaponType] = value;	
	}
	public short getWeaponExp(int weaponType) {
		return weaponExp[weaponType];
	}
	public void setWeaponExp(int weaponType, short value) {
		weaponExp[weaponType] = value;	
	}
	
	/**
	 * 
	 * @param weaponType 
	 * @param value xp won
	 * @return the number of level up
	 */
	public int incWeaponExp(int weaponType, short value) {
		int nbLevelUp = (weaponExp[weaponType] + value) / WEAPON_EXP_LVL;
		weaponExp[weaponType] += value;
		weaponExp[weaponType] = (short) (weaponExp[weaponType] % WEAPON_EXP_LVL);
		weaponClass[weaponType] += nbLevelUp;
		
		assert(weaponExp[weaponType] >= 0 && weaponExp[weaponType] < WEAPON_EXP_LVL);
		assert(nbLevelUp >= 0);
		return nbLevelUp;
	}
}