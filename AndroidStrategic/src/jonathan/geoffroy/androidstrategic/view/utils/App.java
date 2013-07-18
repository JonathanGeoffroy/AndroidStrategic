package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.view.screens.DialogScreen;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

public class App extends Game {
	public static final String TEXTURES_DIR = "data/img/textures/", DIALOGS_DIR = "data/img/fighters/dialogs/", FIGHTERS_DIR = "data/img/fighters/", FONTS_DIR = "data/fonts/", SOUNDS_DIR = "data/sounds/sounds/", MUSICS_DIR = "data/sounds/musics/";
	public static final int MAP = 0, DIALOG = 1;
	private AssetManager manager;
	private ArrayList<HelpScreen> screens;

	private String scenario;
	private int chapter;

	@Override
	public void create() {
		HelpScreen.initialize(this);
		scenario = "Test";
		chapter = 3;
		manager = new AssetManager();
		screens = new ArrayList<HelpScreen>();
		screens.add(new MapScreen());
		screens.add(new DialogScreen());

		setScreen(screens.get(MAP));
		//setScreen(screens.get(DIALOG));
	}

	public void setScreen(int screenType) {
		assert(screenType >= 0 && screenType < screens.size());
		setScreen(screens.get(screenType));
	}

	public void loadAssets(ArrayList<AssetDescriptor<Object>> assetDesc) {
		for(AssetDescriptor<Object> ad : assetDesc) {
			manager.load(ad);
		}
	}

	public void clearAssets() {
		manager.clear();
	}

	public boolean hasLoaded() {
		return manager.update();
	}

	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public Object getAsset(String name) {
		return manager.get(name);
	}
}
