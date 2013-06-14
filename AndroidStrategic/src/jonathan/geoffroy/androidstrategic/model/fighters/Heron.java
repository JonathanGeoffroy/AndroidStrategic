package jonathan.geoffroy.androidstrategic.model.fighters;

public class Heron extends Laguz implements Flying {

	@Override
	protected void initializeStats() {
		hp = hpMax = 18;
		strength = 0;
		magic = 5;
		luck = 5;
		resistance = 20;
		movement = movementMax = 5;
		constitution = 10;
		weight = 10;
		transStrength = 1;
		transMovement = 1;
		transMagic = 1;
		transSpeed = 3;		
		transDefense = 1;
		transResistance = 5;
		transWeight = 2;
		
		if(male) {
			speed = 7;
			defense = 2;
			transConstitution = 8;
		}
		else {
			speed = 8;
			defense = 1;
			transConstitution = 5;
		}
	}

	@Override
	protected String defaultName() {
		return "Heron";
	}
}