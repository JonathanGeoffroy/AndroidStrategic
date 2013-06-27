package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.fighters.Laguz;

public abstract class LaguzItem extends Item {

	public abstract void use(Laguz laguz);

	@Override
	public int getType() {
		return 2;
	}
}