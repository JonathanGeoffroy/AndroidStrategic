package jonathan.geoffroy.androidstrategic.model.fighters;

public class Priest extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 22;
		strength = 0;
		magic = 10;
		speed = 5;
		luck = 8;
		defense = 0;
		resistance = 14;
		constitution = 8;
		weight = 8;
		
		scepterClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Priest";
	}
}