package jonathan.geoffroy.androidstrategic.model.mapping;

public class Fort extends Terrain {
	public Fort() {
		pedestrianMovementCost = 2;
		riderMovementCost = 2;
		defense = 3;
		avoid = 30;
	}
}
