package jonathan.geoffroy.androidstrategic.model.fighters;

public class Archer extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 19;
		strength = 9;
		speed = 8;
		defense = 5	;
		luck = 0;
		resistance = 5;
		movement = movementMax = 1;
		constitution = 10;
		weight = 10;
		
		bowClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Archer";
	}

}
