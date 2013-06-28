package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.mapping.magic.BlockMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;

public class BlockItem extends TerrainItem {

	@Override
	public Magic magic() {
		return new BlockMagic();
	}
}