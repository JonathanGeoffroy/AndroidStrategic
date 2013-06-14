package jonathan.geoffroy.androidstrategic.model.fighters;

public class Cat extends Laguz {
	@Override
	public void  initializeStats() {
		movement = 7;
		transMovement = 2;
		strength = 6;
		transMovement = 6;
		magic = 4;
		transMagic = 0;
		speed = 8;
		transSpeed = 3;
		luck  = 0;
		
		transDefense = 5;
		transResistance = 3;
		transConstitution = 15;
		
		if(male) {
			hpMax = 27;
			defense = 4;
			resistance = 2;
			constitution = 9;
			weight = 24;
			transWeight = 12;
		}
		else {
			hpMax = 26;
			defense = 3;
			resistance = 3;
			constitution = 6;
			weight = 21;
			transWeight = 10;
		}
		hp = hpMax;
		movement = movementMax;
	}

	@Override
	protected String defaultName() {
		return "Cat";
	}
}