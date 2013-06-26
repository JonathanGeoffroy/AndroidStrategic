package jonathan.geoffroy.androidstrategic.model.mapping;

public class Grass extends Terrain {
	public Grass() {
		pedestrianMovementCost = 2;
		riderMovementCost = 3;
		defense = 1;
		avoid = 20;
	}
}