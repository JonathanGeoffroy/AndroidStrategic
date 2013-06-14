package jonathan.geoffroy.androidstrategic.model.fighters;

public class Ranger extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 19;
		strength = 5;
		magic = 1;
		speed = 7;
		luck = 6;
		defense = 5;
		resistance = 0;
		movement = movementMax = 6;
		constitution = 9;
		weight = 9;
		
		swordClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Ranger";
	}
}