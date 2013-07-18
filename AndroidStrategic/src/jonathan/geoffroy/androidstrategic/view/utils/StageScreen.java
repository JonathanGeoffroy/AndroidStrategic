package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class StageScreen extends HelpScreen {
	protected Stage stage;
	private float width, height;

	public StageScreen() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, false);
	}

	@Override
	public void resize(int w, int h) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		super.dispose();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public ArrayList<AssetDescriptor<Object>> getAssetDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onEndLoaded() {
		// TODO Auto-generated method stub
		
	}
}