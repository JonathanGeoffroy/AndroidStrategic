package jonathan.geoffroy.androidstrategic.model.fighters;

public class Cat extends Laguz {
	@Override
	public void  initializeStats() {
		super.initializeStats();
		movement = 7;
		transMovement = 2;
		attributes[STRENGTH] = 6;
		transMovement = 6;
		attributes[MAGIC] = 4;
		transMagic = 0;
		attributes[SPEED] = 8;
		transSpeed = 3;
		attributes[LUCK]  = 0;
		
		transDefense = 5;
		transResistance = 3;
		transConstitution = 15;
		
		if(male) {
			attributes[HPMAX] = 27;
			attributes[DEFENSE] = 4;
			attributes[RESISTANCE] = 2;
			attributes[CONSTITUTION] = 9;
			weight = 24;
			transWeight = 12;
		}
		else {
			attributes[HPMAX] = 26;
			attributes[DEFENSE] = 3;
			attributes[RESISTANCE] = 3;
			attributes[CONSTITUTION] = 6;
			weight = 21;
			transWeight = 10;
		}
		hp = attributes[HPMAX];
		movement = attributes[MOVEMENTMAX];
	}

	@Override
	protected String defaultName() {
		return "Cat";
	}
}