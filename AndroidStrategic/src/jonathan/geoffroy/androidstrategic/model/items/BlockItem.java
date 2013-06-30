package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class BlockItem extends TerrainItem {

	@Override
	public void use(Map map, Coord2D coord) {
		Terrain t = map.getTerrain(coord.x,  coord.y);
		Terrain newTerrain = t.cloner();
		newTerrain.setUntraversable();
		map.setTerrain(coord, newTerrain);
	}
	
	@Override
	public Magic magic() {
		return null;
	}
}