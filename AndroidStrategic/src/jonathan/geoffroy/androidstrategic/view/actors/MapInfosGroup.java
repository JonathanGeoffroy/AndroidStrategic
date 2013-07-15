package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.TableActor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MapInfosGroup extends Actor {
	public static final String WALLPAPER = App.TEXTURES_DIR + "fighterInfo_wallpaper.png";
	
	private MapScreen mapScreen;
	private int drawedActor;
	private ArrayList<TableActor> actors;
	
	public MapInfosGroup(MapScreen mapScreen, TableActor actor) {
		assert (actor != null);
		this.mapScreen = mapScreen;
		actors = new ArrayList<TableActor>();
		actors.add(actor);
		drawedActor = 0;

		addListener(new ActorGestureListener() {
			@Override
			public void fling(InputEvent event, float velocityX,
					float velocityY, int button) {
				super.fling(event, velocityX, velocityY, button);
				if (velocityX < 0) {
					MapInfosGroup.this.previousActor();
				} else if (velocityX > 0) {
					MapInfosGroup.this.nextActor();
				}
			}
		});
	}

	public void addActor(TableActor actor) {
		assert (actor != null);
		actor.setVisible(false);
		actors.add(actor);
		mapScreen.getStage().addActor(actor);
	}

	public boolean removeActor(TableActor actor) {
		assert (actors.contains(actor));
		boolean result = actors.remove(actor);
		drawedActor = 0;
		actors.get(0).setVisible(true);
		for(int i = 1, size = actors.size(); i < size; i++) {
			actors.get(i).setVisible(false);
		}
		return result;
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		for(Actor actor : actors) {
			actor.setBounds(x, y, width, height);
		}
	}

	private void nextActor() {
		actors.get(drawedActor).setVisible(false);
		drawedActor++;
		if (drawedActor >= actors.size()) {
			drawedActor = 0;
		}
		assert (drawedActor >= 0 && drawedActor < actors.size());
		actors.get(drawedActor).setVisible(true);
	}

	private void previousActor() {
		actors.get(drawedActor).setVisible(false);
		drawedActor--;
		if (drawedActor < 0) {
			drawedActor = actors.size() - 1;
		}
		assert (drawedActor >= 0 && drawedActor < actors.size());
		actors.get(drawedActor).setVisible(true);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		actors.get(drawedActor).draw(batch, parentAlpha);
	}
}