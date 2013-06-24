package jonathan.geoffroy.androidstrategic.model.mapping;

public abstract class DestroyableTerrain extends Terrain {

	protected int hp;

	public abstract void onDestroyed();

	@Override
	public boolean equals(Object obj) {
		return this == obj; // pointers comparison
	}

	
}