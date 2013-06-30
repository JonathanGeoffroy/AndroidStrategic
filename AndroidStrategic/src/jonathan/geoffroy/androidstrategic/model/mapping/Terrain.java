package jonathan.geoffroy.androidstrategic.model.mapping;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Flying;
import jonathan.geoffroy.androidstrategic.model.fighters.Riding;

public abstract class Terrain implements Cloneable {
	protected short avoid;
	protected short defense;
	protected short pedestrianMovementCost;
	protected short riderMovementCost;
	
	public Terrain() {
		
	}
	
	public Terrain(Terrain t) {
		this.avoid = t.avoid;
		this.defense = t.defense;
		this.pedestrianMovementCost = t.pedestrianMovementCost;
		this.riderMovementCost = t.riderMovementCost;
	}
	/**
	 * TRUE if fighter can cross the Terrain
	 * do NOT take care of fighter's movements, but just in its type
	 * @param fighter
	 * @return true if fighter can cross this Terrain
	 */
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

	/**
	 * make the Terrain untraversable for non-flying units
	 */
	public void setUntraversable() {
		pedestrianMovementCost = riderMovementCost = -1;
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
	
	/**
	 * clone this
	 * @Return a new instance of cloning this
	 */
	public Terrain cloner() {
		try {
			return (Terrain) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}