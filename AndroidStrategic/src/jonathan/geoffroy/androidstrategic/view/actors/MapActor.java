package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapActor extends Actor {
	private static App app;
	private Map map;
	private int nbTerrainsX, nbTerrainsY;
	private int beginX, beginY;
	private float terrainSize;
	
	public MapActor(Map map) {
		this.map = map;
		beginX = 0;
		beginY = 0;
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		calculateTerrains(width, height);
	}

	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		calculateTerrains(width, getHeight());
	}

	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		calculateTerrains(getWidth(), height);
	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		calculateTerrains(width, height);
	}

	private void calculateTerrains(float width, float height) {
		nbTerrainsX = Math.min(10, map.getWidth());
		terrainSize = width / nbTerrainsX;		
		nbTerrainsY = Math.min((int)(height / terrainSize) + 1, map.getHeight());
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		assert(app != null);
		super.draw(batch, parentAlpha);
		Texture text;
		float x, y;
		for(int i = 0; i < nbTerrainsY; i++) {
			for(int j = 0 ; j < nbTerrainsX; j++) {
				text = (Texture) app.getAsset(App.TEXTURES_DIR + map.getTerrain(j + beginX, i + beginY).getClass().getSimpleName() + ".bmp");
				x = j * terrainSize;
				y = getHeight() - (i+1) * terrainSize;
				batch.draw(text, getX() + x , getY() + y, terrainSize, terrainSize);
			}
		}
	}
	
	public static void initializeApp(App app) {
		MapActor.app = app;
	}
}
