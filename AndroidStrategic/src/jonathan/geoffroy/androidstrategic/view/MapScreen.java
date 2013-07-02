package jonathan.geoffroy.androidstrategic.view;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.StageScreen;

public class MapScreen extends StageScreen {
	private Map map;
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<AssetDescriptor<Object>> getAssetDescriptors() {
		assert map != null;
		
		ArrayList<AssetDescriptor<Object>> desc = new ArrayList<AssetDescriptor<Object>>();
		
		for(Terrain t : map.getTerrains()) {
			desc.add(new AssetDescriptor(App.TEXTURES_DIR + t.getClass().getSimpleName() + ".bmp", Texture.class));
		}
//		for(Fighter f : map.getFighters()) {
//			desc.add(new AssetDescriptor(App.TEXTURES_DIR + f.getName(), Texture.class));
//		}
		
		return desc;
	}

	@Override
	public void show() {
		assert(app.getScenario() != null);
		assert(app.getChapter() != 0);
		try {
			map = Map.load(app.getScenario(), app.getChapter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.show();
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}

	@Override
	public void draw(float delta) {
		super.draw(delta);
		System.out.println("on draw !!!");	
	}
}
