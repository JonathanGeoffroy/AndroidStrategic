package jonathan.geoffroy.androidstrategic.model.fighters;

public class Priest extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 18;
		strength = 0;
		magic = 3;
		skill = 3;
		speed = 3;
		luck = 8;
		defense = 1;
		resistance = 8;
		constitution = 9;
		weight = 9;
		movement = movementMax = 5;
		
		scepterClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Priest";
	}
}