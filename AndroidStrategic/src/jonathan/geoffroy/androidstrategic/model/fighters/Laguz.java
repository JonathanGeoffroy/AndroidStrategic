package jonathan.geoffroy.androidstrategic.model.fighters;

public abstract class Laguz extends Fighter {
	public static final short TRANFORM_POINTS_ON_ATTACK = 3, TRANSFORM_POINTS_ON_NEXT_TURN = 4, MAX_TRANSFORM_POINTS = 20;

	protected short transform;
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
	public FightResult fight(Fighter assaulted) {
		FightResult result = super.fight(assaulted);
		setTransform((short) (transform - TRANFORM_POINTS_ON_ATTACK));
		return result;
	}

	/**
	 * Transform the Laguz
	 * @throws TransformNotPossibleException if laguz haven't 30 transform points
	 */
	public void transform() throws TransformNotPossibleException {
		if(canTransform()) {
			transformed = true;
		}
		else {
			throw new TransformNotPossibleException();
		}
	}


	/**
	 * remove transform points if if laguz is transformed, add transform points else
	 */
	@Override
	public void afterAssaulted() {
		int transPoints;
		if(transformed) {
			transPoints = -TRANFORM_POINTS_ON_ATTACK;
		}
		else {
			transPoints = TRANFORM_POINTS_ON_ATTACK;
		}
		setTransform((short) (transform + transPoints));
	}

	/**
	 * @return TRUE only if laguz can transform itself; i.e. if he has 30 transform points, and isn't yet transformed
	 */
	public boolean canTransform() {
		return !transformed && transform == 30;
	}

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
	public short getTransform() {
		return transform;
	}

	public void setTransform(short transform) {
		this.transform = transform;
		if(transform > 30)
			transform = 30;
		else if(transform <= 0) {
			transformed = false;
			transform = 0;
		}
	}
	public boolean isTransformed() {
		return transformed;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public short getTransDefense() {
		return transDefense;
	}
	public void setTransDefense(short transDefense) {
		this.transDefense = transDefense;
	}
	public short getTransConstitution() {
		return transConstitution;
	}
	public void setTransConstitution(short transConstitution) {
		this.transConstitution = transConstitution;
	}
	public short getTransMagic() {
		return transMagic;
	}
	public void setTransMagic(short transMagic) {
		this.transMagic = transMagic;
	}
	public short getTransResistance() {
		return transResistance;
	}
	public void setTransResistance(short transResistance) {
		this.transResistance = transResistance;
	}
	public short getTransStrength() {
		return transStrength;
	}
	public void setTransStrength(short transStrength) {
		this.transStrength = transStrength;
	}
	public short getTransSpeed() {
		return transSpeed;
	}
	public void setTransSpeed(short transSpeed) {
		this.transSpeed = transSpeed;
	}
	public short getTransWeight() {
		return transWeight;
	}
	public void setTransWeight(short transWeight) {
		this.transWeight = transWeight;
	}
	public short getTransMovement() {
		return transMovement;
	}
	public void setTransMovement(short transMovement) {
		this.transMovement = transMovement;
	}

	public void addTransform(int trans) {
		transform += trans;
		if(transform < 0) {
			transform = 0;
		}
		else if(transform > MAX_TRANSFORM_POINTS) {
			transform = MAX_TRANSFORM_POINTS;
		}
	}
}