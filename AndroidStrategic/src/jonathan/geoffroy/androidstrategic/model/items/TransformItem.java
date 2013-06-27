package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.fighters.Laguz;

public class TransformItem extends LaguzItem {

	private int transform;

	public TransformItem() {
		transform = 10;
	}
	public TransformItem(int transform) {
		this.transform = transform;
	}

	public int getTransform() {
		return transform;
	}

	public void setTransform(int transform) {
		this.transform = transform;
	}

	@Override
	public void use(Laguz laguz) {
		laguz.addTransform(transform);
	}
}