package jonathan.geoffroy.androidstrategic.model.fighters;

public class Thief extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 16;
		strength = 4;
		magic = 0;
		skill = 2;
		speed = 9;
		luck = 5;
		defense = 2;
		resistance = 0;
		movement = movementMax = 7;
		constitution = 9;
		weight = 9;
		
		knifeClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Thief";
	}
}