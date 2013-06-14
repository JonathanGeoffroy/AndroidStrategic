package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.bags.FighterBag;

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
	protected short weight;
	private boolean general;
	protected FighterBag bag;

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
	 * @return true if and only if the adv died during the fight
	 */
	public boolean fight(Fighter adv) {
		// TODO
		return false;
	}

	/**
	 * Calculate de strength of the Fighter physical attack
	 * @return strength of the Fighter attack
	 */
	public int calculatePower() {
		return strength;
	}

	/**
	 * Calculate de strength of the Fighter physical defense
	 * @return
	 */
	public int calculateDefense() {
		return defense;
	}

	/**
	 * Calculate de strength of the Fighter magical defense
	 * @return
	 */
	public int calculateResistance() {
		return resistance;
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
}