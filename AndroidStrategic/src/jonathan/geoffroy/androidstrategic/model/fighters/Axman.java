package jonathan.geoffroy.androidstrategic.model.fighters;

public class Axman extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 24;
		strength = 5;
		skill = 3;
		magic = 0;
		speed = 3;
		luck = 0;
		defense = 3;
		resistance = 2;
		constitution = 12;
		weight = 12;
		movementMax = movement = 6;
		
		axClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Axman";
	}
}