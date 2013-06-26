package jonathan.geoffroy.androidstrategic.model.mapping;

public class Forest extends Terrain {
	public Forest() {
		pedestrianMovementCost = 2;
		riderMovementCost = 3;
		defense = 1;
		avoid = 20;
	}
}
