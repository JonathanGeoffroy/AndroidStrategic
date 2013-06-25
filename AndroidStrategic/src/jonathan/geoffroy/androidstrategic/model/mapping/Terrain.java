package jonathan.geoffroy.androidstrategic.model.mapping;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Flying;
import jonathan.geoffroy.androidstrategic.model.fighters.Riding;

public abstract class Terrain {
	protected short avoid;
	protected short defense;
	protected short pedestrianMovementCost;
	protected short riderMovementCost;
	
	public boolean isTraversable(Fighter fighter) {
		if(fighter instanceof Flying) {
			return true;
		}
		if(fighter instanceof Riding) {
			return riderMovementCost > 0;
		}
		return pedestrianMovementCost > 0;
	}
	
	public boolean isStoppable(Fighter fighter) {
		return isTraversable(fighter);
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

	public int getMovementCost(Fighter fighter) {
		if(fighter instanceof Flying) {
			return 1;
		}
		if(fighter instanceof Riding) {
			return riderMovementCost;
		}
		return pedestrianMovementCost;
	}
}