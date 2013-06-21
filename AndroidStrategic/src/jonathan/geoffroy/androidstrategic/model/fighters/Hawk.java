package jonathan.geoffroy.androidstrategic.model.fighters;

public class Hawk extends Laguz implements Flying {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 26;
		attributes[STRENGTH] = 6;
		attributes[MAGIC] = 2;
		attributes[SPEED] = 8;
		attributes[LUCK] = 5;
		attributes[DEFENSE] = 5;
		attributes[RESISTANCE] = 3;
		movement = attributes[MOVEMENTMAX] = 6;
		attributes[CONSTITUTION] = 10;
		weight = 10;
		transStrength = 6;
		transMagic = 0;
		transSpeed = 3;
		transDefense = 4;
		transResistance = 2;
		transConstitution = 10;
		transWeight = 8;
		transMovement = 2;
	}

	@Override
	protected String defaultName() {
		return "Hawk";
	}
}