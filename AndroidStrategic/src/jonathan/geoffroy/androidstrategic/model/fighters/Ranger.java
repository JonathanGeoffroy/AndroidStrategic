package jonathan.geoffroy.androidstrategic.model.fighters;

public class Ranger extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 18;
		strength = 3;
		magic = 1;
		skill = 3;
		speed = 4;
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