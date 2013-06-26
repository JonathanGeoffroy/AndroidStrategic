package jonathan.geoffroy.androidstrategic.model.mapping;

public class Gate extends Terrain {
	public Gate() {
		pedestrianMovementCost = 2;
		riderMovementCost = -1;
		defense = 2;
		avoid = 30;
	}
}
