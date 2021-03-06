package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public abstract class MapActor extends Actor {
	protected static App app;
	protected Map map;
	protected MapScreen mapScreen;

	protected ArrayList<EventListener> listeners;
	protected int nbTerrainsX, nbTerrainsY;
	protected int beginX, beginY;
	protected float terrainSize;

	public MapActor(MapScreen mapScreen) {
		this.mapScreen = mapScreen;
		this.map = mapScreen.getMap();
		beginX = 0;
		beginY = 0;

		createListeners();
	}

	public MapActor(MapActor mapActor) {
		mapScreen = mapActor.mapScreen;
		map = mapScreen.getMap();
		nbTerrainsX = mapActor.nbTerrainsX;
		nbTerrainsY = mapActor.nbTerrainsY;
		terrainSize = mapActor.terrainSize;
		beginX = mapActor.beginX;
		beginY = mapActor.beginY;

		createListeners();
	}

	protected void createListeners() {
		listeners = new ArrayList<EventListener>();

		listeners.add(new InputListener() {
			@Override
			public boolean scrolled(InputEvent event, float x, float y,
					int amount) {
				zoom(-amount);
				return true;
			}

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch(keycode) {
				case Input.Keys.LEFT:
					incBeginX(-1);
					break;
				case Input.Keys.RIGHT:
					incBeginX(1);
					break;
				case Input.Keys.UP:
					incBeginY(-1);
					break;
				case Input.Keys.DOWN:
					incBeginY(1);
					break;
				case Input.Keys.EQUALS:
				case Input.Keys.PAGE_UP:
					zoom(1);
					break;
				case Input.Keys.MINUS:
				case Input.Keys.PAGE_DOWN:
					zoom(-1);
				default:
					return false;
				}
				return true;
			}

		});

		listeners.add(new ActorGestureListener() {
			private int lastZoomFactor;

			public void fling (InputEvent event, float velocityX, float velocityY, int button) {
				int x = (int)(-velocityX / Gdx.graphics.getWidth()) * 2;
				int y = (int)(velocityY / Gdx.graphics.getHeight()) * 2;
				incBeginX(x);
				incBeginY(y);
			}

			public void zoom (InputEvent event, float initialDistance, float distance) {
				int zoomFactor = (int) ((distance - initialDistance) / (getWidth() / 8)) - lastZoomFactor;
				MapActor.this.zoom(zoomFactor);
				lastZoomFactor = zoomFactor;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				lastZoomFactor = 0;
			}
		});
	}

	public void enableListeners() {
		for(EventListener l : listeners) {
			addListener(l);
		}
	}

	public void disableListeners() {
		for(EventListener l : listeners) {
			removeListener(l);
		}
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
		else if (beginY > map.getHeight() - nbTerrainsY) {
			beginY = map.getHeight() - nbTerrainsY;
		}
	}

	private void zoom(int zoomFactor) {
		float nextTerrainSize = terrainSize + zoomFactor;
		int nbTerrainsX = (int)(getWidth() / nextTerrainSize);
		int nbTerrainsY = (int)(getHeight() / nextTerrainSize);

		if (beginX + nbTerrainsX < map.getWidth() &&
				beginY  + nbTerrainsY < map.getHeight()) {
			terrainSize = nextTerrainSize;
			setNbTerrainsX(nbTerrainsX);
			setNbTerrainsY(nbTerrainsY);
		}
	}

	public void setNbTerrainsX(int value) {
		nbTerrainsX = value;
		if(beginX > map.getWidth() - nbTerrainsX) {
			nbTerrainsX = map.getWidth() - beginX - 1;
			terrainSize = getWidth() / nbTerrainsX;
		}
		assert beginX + nbTerrainsX < map.getWidth() : "x: " + beginX + nbTerrainsX;
	}

	public void setNbTerrainsY(int value) {
		nbTerrainsY = value;
		if(beginY > map.getHeight() - nbTerrainsY) {
			nbTerrainsY = map.getHeight() - beginY - 1;
		}
		assert beginY + nbTerrainsY < map.getHeight(): "y: " + beginY + nbTerrainsY;
	}
}
