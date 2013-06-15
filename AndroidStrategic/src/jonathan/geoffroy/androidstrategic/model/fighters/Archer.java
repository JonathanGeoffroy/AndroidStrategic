package jonathan.geoffroy.androidstrategic.model.fighters;

public class Archer extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 18;
		strength = 3;
		speed = 3;
		skill = 6;
		defense = 4;
		luck = 0;
		resistance = 0;
		movement = movementMax = 1;
		constitution = 9;
		weight = 9;
		
		bowClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Archer";
	}

}
