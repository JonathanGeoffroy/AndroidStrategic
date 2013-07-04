package jonathan.geoffroy.androidstrategic.view.screens;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.actors.FighterInfoActor;
import jonathan.geoffroy.androidstrategic.view.actors.MapActor;
import jonathan.geoffroy.androidstrategic.view.actors.MapInfosGroup;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.StageScreen;

public class MapScreen extends StageScreen {
	private Map map;
	private MapActor mapActor;
	private MapInfosGroup mapInfos;
	private Coord2D coordFighter;

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
			desc.add(new AssetDescriptor(App.FIGHTERS_DIR + f.getTextureName(), Texture.class));
			desc.add(new AssetDescriptor(App.DIALOGS_DIR + f.getTextureName(), Texture.class));
		}

		desc.add(new AssetDescriptor(FighterInfoActor.WALLPAPER, Texture.class));
		desc.add(new AssetDescriptor(FighterInfoActor.FONT, BitmapFont.class));
		return desc;
	}

	@Override
	public void show() {
		assert(app.getScenario() != null);
		assert(app.getChapter() != 0);
		try {
			map = Map.load(app.getScenario(), app.getChapter());
			mapActor = new MapActor(this);
			mapActor.setBounds(0, 0, Gdx.graphics.getWidth() * 2.f / 3.f, Gdx.graphics.getHeight());
			stage.addActor(mapActor);
			
			FighterInfoActor fighterInfo = new FighterInfoActor(this);
			mapInfos = new MapInfosGroup(fighterInfo);
			mapInfos.setBounds(mapActor.getX() + mapActor.getWidth(), 0, Gdx.graphics.getWidth() - mapActor.getWidth(), Gdx.graphics.getHeight());
			stage.addActor(mapInfos);
			
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

	public Coord2D getCoordFighter() {
		return coordFighter;
	}

	public void setCoordFighter(Coord2D coordFighter) {
		this.coordFighter = coordFighter;
	}

	/**
	 * return the fighter pointed by the selected case. return null if there is no selected case.
	 * @return the selected fighter (or null if there is no selected case)
	 */
	public Fighter getSelectedFighter() {
		if(coordFighter == null)
			return null;
		return map.getFighterAt(coordFighter);
	}

	public Map getMap() {
		return map;
	}
}
