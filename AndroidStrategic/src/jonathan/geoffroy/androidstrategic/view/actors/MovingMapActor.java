package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Reachable;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MovingMapActor extends MapActor {
	private Reachable reachable;

	public MovingMapActor(MapScreen mapScreen) {
		super(mapScreen);
	}

	public MovingMapActor(MapActor mapActor) {
		super(mapActor);
	}

	@Override
	protected void createListeners() {
		super.createListeners();
		listeners.add(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Coord2D coord = new Coord2D(
						(int) (x / terrainSize) + beginX,
						(int) ((getHeight() - y) / terrainSize) + beginY 
						);

				Fighter clikedFighter = MovingMapActor.this.map.getFighterAt(coord);
				if(clikedFighter != null && !clikedFighter.hasMoved()) {
					MovingMapActor.this.mapScreen.setCoordFighter(coord);
					MovingMapActor.this.reachable = MovingMapActor.this.map.getReachableTerrains(clikedFighter);
				}
				else {
					Fighter selectedFighter = MovingMapActor.this.mapScreen.getSelectedFighter();

					if(selectedFighter != null && !MovingMapActor.this.mapScreen.isSelectedMoved() && map.isCurrentTeam(selectedFighter.getTeam()) && reachable.isReachable(coord)) {
						MovingMapActor.this.mapScreen.moveSelectedFighter(coord.x, coord.y);
						if(!selectedFighter.getTeam().canMove()) {
							MovingMapActor.this.mapScreen.endTurn();
						}
					}
					else {
						MovingMapActor.this.mapScreen.setCoordFighter(null);
					}
					reachable = null;
				}
				return true;
			}
		});
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
}
