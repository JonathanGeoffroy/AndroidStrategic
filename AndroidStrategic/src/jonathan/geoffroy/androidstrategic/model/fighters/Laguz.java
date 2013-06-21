package jonathan.geoffroy.androidstrategic.model.fighters;

public abstract class Laguz extends Fighter {
	protected short tranform;
	protected boolean transformed;
	protected boolean male;
	protected short transDefense;
	protected short transConstitution;
	protected short transMagic;
	protected short transResistance;
	protected short transStrength;
	protected short transSpeed;
	protected short transWeight;
	protected short transMovement;

	@Override
	public short getConstitution() {
		if(transformed) return (short)(attributes[CONSTITUTION] + transConstitution);
		else return attributes[CONSTITUTION];
	}
	@Override
	public short getDefense() {
		if(transformed) return (short)(attributes[DEFENSE]+ transDefense);
		else return attributes[DEFENSE];
	}
	@Override
	public short getResistance() {
		if(transformed) return (short)(attributes[RESISTANCE]+ transResistance);
		else return attributes[RESISTANCE];
	}
	@Override
	public short getStrength() {
		if(transformed) return (short)(attributes[STRENGTH] + transStrength);
		else return attributes[STRENGTH];
	}
	@Override
	public short getMagic() {
		if(transformed) return (short)(attributes[MAGIC] + transMagic);
		else return attributes[MAGIC];
	}
	@Override
	public short getSpeed() {
		if(transformed) return (short)(attributes[SPEED] + transMagic);
		else return attributes[SPEED];
	}
	@Override
	public short getWeight() {
		if(transformed) return (short)(weight + transWeight);
		else return weight;
	}

	@Override
	public short getMovementMax() {
		if(transformed) return (short)(attributes[MOVEMENTMAX] + transMovement);
		else return attributes[MOVEMENTMAX];
	}
}