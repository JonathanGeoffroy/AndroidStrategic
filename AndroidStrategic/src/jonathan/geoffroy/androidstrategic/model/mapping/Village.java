package jonathan.geoffroy.androidstrategic.model.mapping;

public class Village extends Terrain {
	public Village() {
		pedestrianMovementCost = 1;
		riderMovementCost = 1;
		defense = 2;
		avoid = 20;
	}
}
