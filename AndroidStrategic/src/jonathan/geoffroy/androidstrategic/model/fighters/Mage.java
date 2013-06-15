package jonathan.geoffroy.androidstrategic.model.fighters;

public class Mage extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 16;
		strength = 0;
		magic = 3;
		skill = 2;
		speed = 3;
		luck = 20;
		defense = 3;
		resistance = 3;
		constitution = 9;
		weight = 9;
		movement = movementMax = 5;
		
		lightBookClass = darkBookClass = fireBookClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Mage";
	}
	
}