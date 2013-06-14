package	 jonathan.geoffroy.androidstrategic.model.fighters;

public class Soldier extends Human {

	@Override
	protected void initializeStats() {
		hp = hpMax = 22;
		strength = 8;
		magic = 2;
		speed = 11;
		luck = 6;
		defense = 9;
		resistance = 3;
		constitution = 8;
		weight = 10;
		
		spearClass = 1;
	}

	@Override
	protected String defaultName() {
		return "Soldier";
	}
}