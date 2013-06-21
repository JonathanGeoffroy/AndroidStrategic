package jonathan.geoffroy.androidstrategic.model.fighters;

public class Axman extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 24;
		attributes[STRENGTH] = 5;
		attributes[SKILL] = 3;
		attributes[MAGIC] = 0;
		attributes[SPEED] = 3;
		attributes[LUCK] = 0;
		attributes[DEFENSE] = 3;
		attributes[RESISTANCE] = 2;
		attributes[CONSTITUTION] = 12;
		weight = 12;
		movement = attributes[MOVEMENTMAX] = 6;
		
		axClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Axman";
	}
}