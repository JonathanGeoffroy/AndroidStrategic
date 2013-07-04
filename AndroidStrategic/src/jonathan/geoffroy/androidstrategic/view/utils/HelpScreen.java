package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.view.actors.MapActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;

public abstract class HelpScreen implements Screen {
	protected static App app;
	protected static float width, height;

	@Override
	public void show() {
		app.loadAssets(getAssetDescriptors());
	}

	@Override
	public void resume() {
		app.loadAssets(getAssetDescriptors());		
	}

	@Override
	public void dispose() {
		app.clearAssets();
	}

	public abstract ArrayList<AssetDescriptor<Object>> getAssetDescriptors();

	public static void initialize(App app) {
		HelpScreen.app = app;
		MapActor.initializeApp(app);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void render(float delta) {
		if(app.hasLoaded()) {
			draw(delta);
		}
		else {
			drawLoader();
		}
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	private void drawLoader() {
		// TODO Auto-generated method stub
		
	}

	public abstract void draw(float delta);
	
	public static App getApp() {
		return app;
	}

	public static void setApp(App app) {
		HelpScreen.app = app;
	}

}
