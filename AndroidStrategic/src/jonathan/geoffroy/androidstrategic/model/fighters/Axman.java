package jonathan.geoffroy.androidstrategic.model.fighters;

public class Axman extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 20;
		strength = 8;
		magic = 0;
		speed = 8;
		luck = 0;
		defense = 7;
		resistance = 2;
		constitution = 11;
		weight = 15;
		movementMax = movement = 6;
		
		axClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Axman";
	}
}