package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.bags.FighterBag;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;

public abstract class Fighter {
	private String name;
	protected short hp;
	protected short hpMax;
	protected short constitution;
	protected short defense;
	protected short resistance;
	protected short strength;
	protected short magic;
	protected short speed;
	protected short aid;
	protected short movement;
	protected short movementMax;
	protected short luck;
	protected short skill;
	protected short weight;
	private boolean general;
	protected FighterBag bag;
	protected Terrain terrain;

	public Fighter() {
		setName(defaultName());
		initializeStats();
	}

	public Fighter(String name) {
		this.setName(name);
		initializeStats();
	}

	/**
	 * Permit to initialize stat for Fighter  
	 */
	protected abstract void initializeStats();
	protected abstract String defaultName();

	/**
	 * 
	 * @param adv
	 * @return a FightResult which retrace all battle
	 */
	public FightResult fight(Fighter assaulted) {
		FightResult result = new FightResult(this, assaulted);
		
		Fighter fighters[] = {this, assaulted};
		int random;

		int hitNumber[] = new int[2];
		hitNumber[0] = this.hitNumber(assaulted);
		hitNumber[1] = this.hitNumber(this);

		boolean physicalAttack[] = new boolean[2];
		physicalAttack[0] = this.isPhysicalAttack();
		physicalAttack[1] = assaulted.isPhysicalAttack();

		int hitStrength[] = new int[2];
		hitStrength[0] = this.calculatePower() - assaulted.calculateDefense(physicalAttack[0]);
		hitStrength[1] = assaulted.calculatePower() - this.calculateDefense(physicalAttack[1]);

		int criticalRate[] = new int[2];
		criticalRate[0] = this.criticalRates();
		criticalRate[1] = assaulted.criticalRates();
		boolean criticalAttack;
		boolean touched;
		int attackStrength;
		int attack = 0, defense = 1;

		while(! (hitNumber[0] == 0 && hitNumber[1] == 0) && !this.isDead() && !assaulted.isDead()) {
			if(hitNumber[attack] > 0) {
				random = (int)(Math.random() * 100);
				criticalAttack = criticalRate[attack] >= random;

				touched = fighters[attack].hasTouched(fighters[defense], criticalAttack);
				if(touched) {
					attackStrength = hitStrength[attack];
					if(criticalAttack)
						attackStrength *= 3;
						
					fighters[defense].addHp(- attackStrength);
				}
				else {
					attackStrength = 0;
				}

				result.addAttack(attack, attackStrength, touched, criticalAttack);
			}

			hitNumber[attack] --;
			attack = (attack + 1) % 2;
			defense = (defense + 1) % 2;
		}

		return result;
	}

	/**
	 * Calculate de strength of the Fighter physical attack
	 * @return strength of the Fighter attack
	 */
	public int calculatePower() {
		if(isPhysicalAttack())
			return strength;
		return magic;
	}

	protected boolean isPhysicalAttack() {
		return true;
	}

	/**
	 * Calculate de strength of the Fighter defense
	 * @return
	 */
	public int calculateDefense(boolean isPhysicalAttack) {
		if(isPhysicalAttack)
			return defense;
		return resistance;
	}

	/** 
	 * Calculate the hit rate of the Fighter
	 * @return the hit rate
	 */
	public int hitRate() {
		return skill * 2 + luck;
	}

	/**
	 * Calculate the evade of the Fighter
	 * @return a rate of evade for a non critical attack
	 */
	public int evade() {
		return speed * 2 + luck + terrain.getAvoid();
	}

	/**
	 * Calculate the evade, depending on it's a critical attack or not.
	 * if isCriticalAttack return criticalEvade() else return evade()
	 * @param isCriticalAttack
	 * @return a rate of evade 
	 */
	public int evade(boolean isCriticalAttack) {
		if(isCriticalAttack)
			return criticalEvade();
		return evade();
	}

	/**
	 * Calculate the evade for a critical attack
	 * @return a rate of evade for a critical attack
	 */
	public int criticalEvade() {
		return luck;
	}
	/**
	 * Calculate the real accuracy of the fighter, depending on the ennemy.
	 * It's actually fighter.hitRate() - ennemy.evade(), with limit of 100
	 * @param other the ennemy
	 * @return accuracy of the Fighter to the ennemy
	 */
	public int accuracy(Fighter other) {
		int accuracy = hitRate() - other.evade();
		if(accuracy > 100)
			accuracy = 100;
		return accuracy;
	}

	private boolean hasTouched(Fighter fighter, boolean criticalAttack) {
		if(criticalAttack) {
			return criticalRates() - criticalEvade() > Math.random();
		}
		else {
			return hitRate() - evade() > Math.random();
		}
	}
	
	public int calculateSpeed() {
		return  speed;
	}
	/**
	 * Calculate the number of hits, depending on the ennemy.
	 * @param ennemy the ennemy
	 * @return 2 if fighter.speed > ennemy.speed + 3, else 1
	 */
	public int hitNumber(Fighter ennemy) {
		if(speed >= ennemy.speed + 3)
			return 2;
		return 1;
	}

	/**
	 * Calculate the chance to do a critical hit
	 * @return a % of chance to do a critical hit
	 */
	public short criticalRates() {
		return (short)(skill / 2);
	}

	public boolean isGeneral() {
		return general;
	}

	public void setGeneral(boolean general) {
		this.general = general;
	}

	public short getHp() {
		return hp;
	}

	public void setHp(short hp) {
		this.hp = hp;
	}

	private void addHp(int hpAdded) {
		hp += hpAdded;
		if(hp < 0)
			hp = 0;
		else if (hp > hpMax)
			hp = hpMax;
	}

	public short getHpMax() {
		return hpMax;
	}

	public void setHpMax(short hpMax) {
		this.hpMax = hpMax;
	}

	public short getConstitution() {
		return constitution;
	}

	public void setConstitution(short constitution) {
		this.constitution = constitution;
	}

	public short getDefense() {
		return defense;
	}

	public void setDefense(short defense) {
		this.defense = defense;
	}

	public short getResistance() {
		return resistance;
	}

	public void setResistance(short resistance) {
		this.resistance = resistance;
	}

	public short getStrength() {
		return strength;
	}

	public void setStrength(short strength) {
		this.strength = strength;
	}

	public short getMagic() {
		return magic;
	}

	public void setMagic(short magic) {
		this.magic = magic;
	}

	public short getSpeed() {
		return speed;
	}

	public void setSpeed(short speed) {
		this.speed = speed;
	}

	public short getAid() {
		return aid;
	}

	public void setAid(short aid) {
		this.aid = aid;
	}

	public short getMovement() {
		return movement;
	}

	public void setMovement(short movement) {
		this.movement = movement;
	}

	public short getMovementMax() {
		return movementMax;
	}

	public void setMovementMax(short movementMax) {
		this.movementMax = movementMax;
	}

	public short getLuck() {
		return luck;
	}

	public void setLuck(short luck) {
		this.luck = luck;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public FighterBag getBag() {
		return bag;
	}

	public void setBag(FighterBag bag) {
		this.bag = bag;
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getSkill() {
		return skill;
	}

	public void setSkill(short skill) {
		this.skill = skill;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
}