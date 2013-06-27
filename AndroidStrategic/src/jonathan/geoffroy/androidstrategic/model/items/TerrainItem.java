package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;

public abstract class TerrainItem extends Item {
	public abstract void use(Terrain terrain);
	
	@Override
	public int getType() {
		return 1;
	}
}