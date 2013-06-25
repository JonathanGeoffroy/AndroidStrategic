package jonathan.geoffroy.androidstrategic.model.mapping;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;

public class ClosedDoor extends DestroyableTerrain {
	public ClosedDoor() {
		pedestrianMovementCost = -1;
		riderMovementCost = -1;
	}
	
	@Override
	public boolean isTraversable(Fighter fighter) {
		return false;
	}
	
	@Override
	public void onDestroyed() {
		// TODO Auto-generated method stub
		
	}
}