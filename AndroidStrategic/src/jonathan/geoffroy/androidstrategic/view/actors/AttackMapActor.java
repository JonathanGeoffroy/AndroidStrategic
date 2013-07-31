package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;

public class AttackMapActor extends MapActor {
	private ArrayList<Coord2D> assailables;

	public AttackMapActor(MapScreen mapScreen) {
		super(mapScreen);
		assert(mapScreen.getSelectedFighter() != null);
		assailables = map.getAssailableTerrains(mapScreen.getSelectedFighter());
	}

	public AttackMapActor(MapActor mapActor) {
		super(mapActor);
		assailables = map.getAssailableTerrains(mapScreen.getSelectedFighter());
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
				System.out.println("touchedDown!");
				if(assailables.contains(coord)) {
					AttackMapActor.this.mapScreen.attack(map.getFighterAt(coord));
				}
				AttackMapActor.this.mapScreen.setMovingMap();
				return true;
			}

		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		assert(app != null);

		Texture text;
		float x, y;
		Fighter fighter;
		Coord2D coord = new Coord2D();
		boolean isCurrentTeam;

		for(int i = 0; i < nbTerrainsY; i++) {
			for(int j = 0 ; j < nbTerrainsX; j++) {
				coord.x = j + beginX; coord.y = i + beginY;

				//Draw terrains & reachable.
				if(assailables.contains(coord)) {
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
