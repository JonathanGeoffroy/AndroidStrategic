package jonathan.geoffroy.androidstrategic.model.fighters;

public class Lion extends Laguz {
	@Override
	protected void initializeStats() {
		hp = hpMax = 18;
		strength = 15;
		magic = 2;
		speed = 9;
		luck = 5;
		defense = 10;
		resistance = 5;
		movement = movementMax = 6;
		constitution = 27;
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