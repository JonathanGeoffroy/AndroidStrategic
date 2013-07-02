package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

public class App extends Game {
	public static final String TEXTURES_DIR = "data/img/textures/", FONTS_DIR = "data/img/fonts", SOUNDS_DIR = "data/sounds/sounds/", MUSICS_DIR = "data/sounds/musics/";
	public static final int MAP = 0;
	private AssetManager manager;
	private ArrayList<HelpScreen> screens;
	
	private String scenario;
	private int chapter;

	@Override
	public void create() {
		HelpScreen.initialize(this);
		scenario = "Test";
		chapter = 2;
		manager = new AssetManager();
		screens = new ArrayList<HelpScreen>();
		screens.add(new MapScreen());
		setScreen(screens.get(MAP));
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
