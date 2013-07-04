package jonathan.geoffroy.androidstrategic.view.screens;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.view.actors.MapActor;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.StageScreen;

public class MapScreen extends StageScreen {
	private Map map;
	private MapActor mapActor;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<AssetDescriptor<Object>> getAssetDescriptors() {
		assert map != null;
		
		ArrayList<AssetDescriptor<Object>> desc = new ArrayList<AssetDescriptor<Object>>();
		
		for(Terrain t : map.getTerrains()) {
			desc.add(new AssetDescriptor(App.TEXTURES_DIR + t.getClass().getSimpleName() + ".bmp", Texture.class));
		}
		desc.add(new AssetDescriptor(App.TEXTURES_DIR + "reachable.bmp", Texture.class));
		desc.add(new AssetDescriptor(App.TEXTURES_DIR + "assailable.bmp", Texture.class));
		
		for(Fighter f : map.getFighters()) {
			desc.add(new AssetDescriptor(f.getTextureName(), Texture.class));
		}
		
		return desc;
	}

	@Override
	public void show() {
		assert(app.getScenario() != null);
		assert(app.getChapter() != 0);
		try {
			map = Map.load(app.getScenario(), app.getChapter());
			mapActor = new MapActor(map);
			mapActor.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			stage.addActor(mapActor);
			Gdx.input.setInputProcessor(stage);
			stage.setScrollFocus(mapActor);
			stage.setKeyboardFocus(mapActor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.show();
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}
}
