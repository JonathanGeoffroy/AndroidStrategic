package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public abstract class TerrainItem extends Item {

	public void use(Map map, Coord2D coord) {
		map.addMagic(coord, magic());
		isUsed();
	}

	@Override
	public int getType() {
		return 1;
	}

	public abstract Magic magic();
}