package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.bags.FighterBag;

public abstract class Fighter {

	protected short hp;
	protected short hpMax;
	protected short constitution;
	protected short defense;
	protected short resistance;
	protected short strenght;
	protected short magic;
	protected short speed;
	protected short aid;
	protected short movement;
	protected short movementMax;
	protected short luck;
	protected short weight;
	private boolean general;
	protected FighterBag bag;

	public boolean fight(Fighter adv) {
		return false;
	}

	public int calculatePower() {
		return 0;
	}

	public int calculateDefense() {
		return 0;
	}

	public int calculateResistance() {
		return 0;
	}

	public boolean isGeneral() {
		return general;
	}

	public void setGeneral(boolean general) {
		this.general = general;
	}

}