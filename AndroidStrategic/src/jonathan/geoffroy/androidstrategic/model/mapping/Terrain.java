package jonathan.geoffroy.androidstrategic.model.mapping;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;

public abstract class Terrain {
	protected short avoid;
	protected short defense;
	protected short movementCost;
	
	public boolean isTraversable(Fighter fighter) {
		return true;
	}
	
	public boolean isStoppable(Fighter fighter) {
		return true;
	}

	public short getAvoid() {
		return avoid;
	}

	public void setAvoid(short avoid) {
		this.avoid = avoid;
	}

	public short getDefense() {
		return defense;
	}

	public void setDefense(short defense) {
		this.defense = defense;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass().equals(this.getClass());
	}

	public int getMovementCost() {
		return movementCost;
	}

	public void setMovementCost(short movementCost) {
		this.movementCost = movementCost;
	}
}