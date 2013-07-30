package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Reachable;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MapActor extends Actor {
	protected static App app;
	protected Map map;
	protected MapScreen mapScreen;
	private Reachable reachable;
	private ArrayList<EventListener> listeners;
	protected int nbTerrainsX, nbTerrainsY;
	protected int beginX, beginY;
	protected float terrainSize;

	public MapActor(MapScreen mapScreen) {
		this.mapScreen = mapScreen;
		this.map = mapScreen.getMap();
		beginX = 0;
		beginY = 0;
		listeners = new ArrayList<EventListener>();

		listeners.add(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Coord2D coord = new Coord2D(
						(int) (x / terrainSize) + beginX,
						(int) ((getHeight() - y) / terrainSize) + beginY 
						);

				Fighter clikedFighter = MapActor.this.map.getFighterAt(coord);
				if(clikedFighter != null && !clikedFighter.hasMoved()) {
					MapActor.this.mapScreen.setCoordFighter(coord);
					MapActor.this.reachable = MapActor.this.map.getReachableTerrains(clikedFighter);
				}
				else {
					Fighter selectedFighter = MapActor.this.mapScreen.getSelectedFighter();

					if(selectedFighter != null && map.isCurrentTeam(selectedFighter.getTeam()) && reachable.isReachable(coord)) {
						map.moveFighter(selectedFighter, coord.x, coord.y);
						if(!selectedFighter.getTeam().canMove()) {
							MapActor.this.mapScreen.endTurn();
						}
					}
					MapActor.this.mapScreen.setCoordFighter(null);
					reachable = null;
				}

				assert( (MapActor.this.mapScreen.getCoordFighter() == null && reachable == null) || (MapActor.this.mapScreen.getCoordFighter() != null && reachable != null) );
				return true;
			}

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

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		assert(app != null);
		super.draw(batch, parentAlpha);
		Texture text;
		float x, y;
		Fighter fighter;
		Coord2D coord = new Coord2D();
		boolean isCurrentTeam;
		
		for(int i = 0; i < nbTerrainsY; i++) {
			for(int j = 0 ; j < nbTerrainsX; j++) {
				coord.x = j + beginX; coord.y = i + beginY;

				//Draw terrains & reachable.
				if(reachable != null && reachable.getReachableMap()[coord.y][coord.x] == Reachable.REACHABLE) {
					text = (Texture) app.getAsset(App.TEXTURES_DIR + "reachable.bmp");
				}
				else if(reachable != null && reachable.getReachableMap()[coord.y][coord.x] == Reachable.ASSAILABLE) {
					text = (Texture) app.getAsset(App.TEXTURES_DIR + "assailable.bmp");
				}
				else {
					text = (Texture) app.getAsset(App.TEXTURES_DIR + map.getTerrain(coord.x, coord.y).getClass().getSimpleName() + ".bmp");
				}
				x = j * terrainSize;
				y = getHeight() - (i+1) * terrainSize;
				batch.draw(text, getX() + x , getY() + y, terrainSize, terrainSize);

				// Draw fighter
				fighter = map.getFighterAt(coord);
				if(fighter != null) {
					if(fighter.hasMoved()) {
						text = (Texture) app.getAsset(App.FIGHTERS_DIR + "moved_" + fighter.getTextureName());
					}
					else {
						isCurrentTeam = map.isCurrentTeam(fighter.getTeam());
						if(!isCurrentTeam) {
							batch.setColor(Color.RED);
						}
						text = (Texture) app.getAsset(App.FIGHTERS_DIR + fighter.getTextureName());
					}
					batch.draw(text, getX() + x , getY() + y, terrainSize, terrainSize);
					batch.setColor(Color.WHITE);
				}
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
