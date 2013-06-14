package jonathan.geoffroy.androidstrategic.model.fighters;

public class Hawk extends Laguz implements Flying {

	@Override
	protected void initializeStats() {
		hp = hpMax = 26;
		strength = 6;
		magic = 2;
		speed = 8;
		luck = 5;
		defense = 5;
		resistance = 3;
		movement = movementMax = 6;
		constitution = 10;
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