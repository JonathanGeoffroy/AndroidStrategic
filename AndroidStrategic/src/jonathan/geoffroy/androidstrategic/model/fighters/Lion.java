package jonathan.geoffroy.androidstrategic.model.fighters;

public class Lion extends Laguz {
	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 18;
		attributes[STRENGTH] = 15;
		attributes[MAGIC] = 2;
		attributes[SPEED] = 9;
		attributes[LUCK] = 5;
		attributes[DEFENSE] = 10;
		attributes[RESISTANCE] = 5;
		movement = attributes[MOVEMENTMAX] = 6;
		attributes[CONSTITUTION] = 27;
		weight = 42;
		transStrength = 12;
		transMagic = 0;
		transSpeed = 3;		
		transDefense = 5;
		transResistance = 0;
		transConstitution = 5;
		transWeight = 10;
		transMovement = 3;
	}

	@Override
	protected String defaultName() {
		return "Lion";
	}
}