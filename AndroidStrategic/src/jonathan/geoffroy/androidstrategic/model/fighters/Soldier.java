package	 jonathan.geoffroy.androidstrategic.model.fighters;

public class Soldier extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 22;
		strength = 4;
		magic = 0;
		skill = 4;
		speed = 4;
		luck = 6;
		defense = 4;
		resistance = 0;
		constitution = 8;
		weight = 13;
		movement = movementMax = 6;
		
		spearClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Soldier";
	}
}