package jonathan.geoffroy.androidstrategic.model.mapping;

public class Mountain extends Terrain {
	public Mountain() {
		pedestrianMovementCost = 4;
		riderMovementCost = -1;
		defense = 2;
		avoid = 30;
	}
}
