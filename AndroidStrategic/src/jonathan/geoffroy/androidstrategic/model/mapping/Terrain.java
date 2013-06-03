package jonathan.geoffroy.androidstrategic.model.mapping;

public abstract class Terrain {
	private short avoid;
	private short defense;
	private short thiefMovementCost;
	private short priestMovementCost;
	private short swordManMovementCost;
	private short axManMovementCost;
	private short bowmanMovementCost;
	private short spireManMovementCost;
	private short mageMovementCost;

	public void isTraversable() {
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

	public short getThiefMovementCost() {
		return thiefMovementCost;
	}

	public void setThiefMovementCost(short thiefMovementCost) {
		this.thiefMovementCost = thiefMovementCost;
	}

	public short getPriestMovementCost() {
		return priestMovementCost;
	}

	public void setPriestMovementCost(short priestMovementCost) {
		this.priestMovementCost = priestMovementCost;
	}

	public short getSwordManMovementCost() {
		return swordManMovementCost;
	}

	public void setSwordManMovementCost(short swordManMovementCost) {
		this.swordManMovementCost = swordManMovementCost;
	}

	public short getAxManMovementCost() {
		return axManMovementCost;
	}

	public void setAxManMovementCost(short axManMovementCost) {
		this.axManMovementCost = axManMovementCost;
	}

	public short getBowmanMovementCost() {
		return bowmanMovementCost;
	}

	public void setBowmanMovementCost(short bowmanMovementCost) {
		this.bowmanMovementCost = bowmanMovementCost;
	}

	public short getSpireManMovementCost() {
		return spireManMovementCost;
	}

	public void setSpireManMovementCost(short spireManMovementCost) {
		this.spireManMovementCost = spireManMovementCost;
	}

	public short getMageMovementCost() {
		return mageMovementCost;
	}

	public void setMageMovementCost(short mageMovementCost) {
		this.mageMovementCost = mageMovementCost;
	}

}