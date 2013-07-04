package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MapInfosGroup extends Group {
	private ArrayList<Actor> actors;
	private int drawedActor;

	public MapInfosGroup(Actor actor) {
		actors = new ArrayList<Actor>();
		assert (actor != null);
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

	public void addActor(Actor actor) {
		assert (actor != null);
		actors.add(actor);
		actor.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public boolean removeActor(Actor actor) {
		assert (actors.contains(actor));
		super.removeActor(actor);
		return actors.remove(actor);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (drawedActor >= 0) {
			actors.get(drawedActor).draw(batch, parentAlpha);
		}
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		for(Actor actor : actors) {
			actor.setBounds(x, y, width, height);
		}
	}

	private void nextActor() {
		drawedActor++;
		if (drawedActor >= actors.size()) {
			drawedActor = 0;
		}
		assert (drawedActor >= 0 && drawedActor < actors.size());
	}

	private void previousActor() {
		drawedActor--;
		if (drawedActor < 0) {
			drawedActor = actors.size() - 1;
		}
		assert (drawedActor >= 0 && drawedActor < actors.size());
	}

}
