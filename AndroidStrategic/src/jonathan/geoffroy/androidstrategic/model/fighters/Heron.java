package jonathan.geoffroy.androidstrategic.model.fighters;

public class Heron extends Laguz implements Flying {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 18;
		attributes[STRENGTH] = 0;
		attributes[MAGIC] = 5;
		attributes[LUCK] = 5;
		attributes[RESISTANCE] = 20;
		movement = attributes[MOVEMENTMAX] = 5;
		attributes[CONSTITUTION] = 10;
		weight = 10;
		transStrength = 1;
		transMovement = 1;
		transMagic = 1;
		transSpeed = 3;		
		transDefense = 1;
		transResistance = 5;
		transWeight = 2;
		
		if(male) {
			attributes[SPEED] = 7;
			attributes[DEFENSE] = 2;
			transConstitution = 8;
		}
		else {
			attributes[SPEED] = 8;
			attributes[DEFENSE] = 1;
			transConstitution = 5;
		}
	}

	@Override
	protected String defaultName() {
		return "Heron";
	}
}