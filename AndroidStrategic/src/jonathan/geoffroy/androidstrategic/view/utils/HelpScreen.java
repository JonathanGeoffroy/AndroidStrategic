package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.ArrayList;

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
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void render(float delta) {
		if(app.hasLoaded()) {
			System.out.println("has loaded");
			draw(delta);
		}
		else {
			System.out.println("not loaded");
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
}
