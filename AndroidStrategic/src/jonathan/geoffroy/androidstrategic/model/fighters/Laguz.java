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
		if(transformed) return (short)(constitution + transConstitution);
		else return constitution;
	}
	@Override
	public short getDefense() {
		if(transformed) return (short)(defense + transDefense);
		else return defense;
	}
	@Override
	public short getResistance() {
		if(transformed) return (short)(resistance + transResistance);
		else return resistance;
	}
	@Override
	public short getStrength() {
		if(transformed) return (short)(strength + transStrength);
		else return strength;
	}
	@Override
	public short getMagic() {
		if(transformed) return (short)(magic + transMagic);
		else return magic;
	}
	@Override
	public short getSpeed() {
		if(transformed) return (short)(magic+ transMagic);
		else return magic;
	}
	@Override
	public short getWeight() {
		if(transformed) return (short)(weight + transWeight);
		else return weight;
	}

	@Override
	public short getMovementMax() {
		if(transformed) return (short)(movementMax + transMovement);
		else return movementMax;
	}
	
	
}