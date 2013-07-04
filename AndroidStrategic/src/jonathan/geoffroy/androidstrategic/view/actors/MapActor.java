package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Reachable;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MapActor extends Actor {
	private static App app;
	private Map map;
	private Coord2D coordFighter;
	private Reachable reachable;
	private int nbTerrainsX, nbTerrainsY;
	private int beginX, beginY;
	private float terrainSize;

	public MapActor(Map map) {
		this.map = map;
		beginX = 0;
		beginY = 0;
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Coord2D coord = new Coord2D(
						(int) (x / terrainSize) + beginX,
						(int) ((getHeight() - y) / terrainSize) + beginY 
						);

				Fighter fighter = MapActor.this.map.getFighterAt(coord);
				if(fighter != null) {
					MapActor.this.coordFighter = coord;
					MapActor.this.reachable = MapActor.this.map.getReachableTerrains(fighter);
				}
				else {
					coordFighter = null;
					reachable = null;
				}

				assert( (coordFighter == null && reachable == null) || (coordFighter != null && reachable != null) );
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
		addListener(new ActorGestureListener() {
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
		
		for(int i = 0; i < nbTerrainsY; i++) {
			for(int j = 0 ; j < nbTerrainsX; j++) {
				coord.x = j + beginX; coord.y = i + beginY;

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

				fighter = map.getFighterAt(coord);
				if(fighter != null) {
					text = (Texture) app.getAsset(fighter.getTextureName());
					batch.draw(text, getX() + x , getY() + y, terrainSize, terrainSize);
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
		else if (beginY > map.getWidth() - nbTerrainsY) {
			beginY = map.getHeight() - nbTerrainsY;
		}
	}

	private void zoom(int zoomFactor) {
		if(terrainSize + zoomFactor < getWidth() / 5) {
			terrainSize += zoomFactor;
			setNbTerrainsX((int)(getWidth() / terrainSize));
			setNbTerrainsY((int)(getHeight() / terrainSize));
		}
	}

	public void setNbTerrainsX(int value) {
		nbTerrainsX = value;
		if(beginX > map.getWidth() - nbTerrainsX) {
			nbTerrainsX = map.getWidth() - beginX;
			terrainSize = getWidth() / nbTerrainsX;
		}
	}

	public void setNbTerrainsY(int value) {
		nbTerrainsY = value;
		if(beginY > map.getWidth() - nbTerrainsY) {
			nbTerrainsY = map.getHeight() - beginY;
		}
	}
}
