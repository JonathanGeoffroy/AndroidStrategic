package jonathan.geoffroy.androidstrategic.model.fighters;

public class Mage extends Human {

	@Override
	protected void initializeStats() {
		super.initializeStats();
		hp = attributes[HPMAX] = 16;
		attributes[STRENGTH] = 0;
		attributes[MAGIC] = 3;
		attributes[SKILL] = 2;
		attributes[SPEED] = 3;
		attributes[LUCK] = 20;
		attributes[DEFENSE] = 3;
		attributes[RESISTANCE] = 3;
		attributes[CONSTITUTION] = 9;
		weight = 9;
		movement = attributes[MOVEMENTMAX] = 5;
		
		lightBookClass = darkBookClass = fireBookClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Mage";
	}
	
	protected boolean isPhysicalAttack() {
		return false;
	}
}