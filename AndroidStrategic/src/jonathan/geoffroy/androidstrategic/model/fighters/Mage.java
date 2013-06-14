package jonathan.geoffroy.androidstrategic.model.fighters;

public class Mage extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 52;
		strength = 20;
		speed = 20;
		luck = 20;
		defense = 20;
		resistance = 20;
		movement = movementMax = 20;
		
		lightBookClass = darkBookClass = fireBookClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Mage";
	}
	
}