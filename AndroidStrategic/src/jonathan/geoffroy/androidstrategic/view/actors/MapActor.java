package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

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
		
		addListener(new ActorGestureListener() {
			public void fling (InputEvent event, float velocityX, float velocityY, int button) {
				int x = (int)(-velocityX / Gdx.graphics.getWidth()) * 2;
				int y = (int)(velocityY / Gdx.graphics.getHeight()) * 2;
				System.out.println(x + " " + y);
				incBeginX(x);
				incBeginY(y);
				
			}

			public void zoom (InputEvent event, float initialDistance, float distance) {

			}
		});
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
	
	public void incBeginX(int value) {
		beginX += value;
		if(beginX < 0)
			beginX = 0;
		else if (beginX > map.getWidth() - nbTerrainsX) {
			beginX = map.getWidth() - nbTerrainsX;
		}
	}
	
	public void incBeginY(int value) {
		beginY += value;
		if(beginY < 0)
			beginY = 0;
		else if (beginY > map.getWidth() - nbTerrainsY) {
			beginY = map.getHeight() - nbTerrainsY;
		}
	}
}
