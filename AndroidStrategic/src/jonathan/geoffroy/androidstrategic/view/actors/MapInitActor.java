package jonathan.geoffroy.androidstrategic.view.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;

public class MapInitActor extends MapActor {
	private FighterChooserActor fighterChooser;
	
	public MapInitActor(MapScreen mapScreen, FighterChooserActor fighterChooser) {
		super(mapScreen);
		this.fighterChooser = fighterChooser;
		
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Coord2D coord = new Coord2D(
						(int) (x / terrainSize) + beginX,
						(int) ((getHeight() - y) / terrainSize) + beginY 
						);

				if(map.isBeginPlayersTerrain(coord) && map.getFighterAt(coord) == null) {
					if(MapInitActor.this.fighterChooser.getSelectedActor() != null) {
						// put the fighter in the map
						Fighter fighter = MapInitActor.this.fighterChooser.getSelectedActor().getFighter();
						map.getUserTeam().addFighter(fighter);
						MapInitActor.this.mapScreen.getMap().addFighter(fighter, coord.x, coord.y);
						MapInitActor.this.fighterChooser.removeSelectedActor();
						
						// Load the battle
						if(MapInitActor.this.fighterChooser.isEmpty() || !map.hasFreeBeginCase()) {
							MapInitActor.this.mapScreen.onEndInit();
						}
					}
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

		for(int i = 0; i < nbTerrainsY; i++) {
			for(int j = 0 ; j < nbTerrainsX; j++) {
				coord.x = j + beginX; coord.y = i + beginY;

				//Draw terrains & reachable.
				if(map.isBeginPlayersTerrain(coord)) {
					text = (Texture) app.getAsset(App.TEXTURES_DIR + "begin.bmp");
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
						text = (Texture) app.getAsset(App.FIGHTERS_DIR + fighter.getTextureName());
					}
					batch.draw(text, getX() + x , getY() + y, terrainSize, terrainSize);
				}
			}
		}
	}
}
