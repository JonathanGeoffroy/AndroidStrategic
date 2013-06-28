package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.TrapMagic;

public class TrapItem extends TerrainItem {
	
	@Override
	public Magic magic() {
		return new TrapMagic();
	}
}