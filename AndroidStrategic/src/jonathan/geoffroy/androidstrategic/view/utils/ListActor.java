package jonathan.geoffroy.androidstrategic.view.utils;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class ListActor<T extends Actor> extends TableActor {
	private LinkedList<T> actors;
	private int selectedIndex;

	public ListActor() {
		super();
		table = new Table();
		actors = new LinkedList<T>();
		selectedIndex = -1;
		addListeners();
		moveTable();
	}

	public ListActor(LinkedList<T> actors) {
		this.actors = actors;
		table = new Table();
		selectedIndex = -1;
		for(Actor a : actors) {
			table.add(a);
			table.row();
		}
		moveTable();
		addListeners();
	}

	private void addListeners() {
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				float tableDown = table.getY();
				float tableUp = tableDown + table.getHeight();

				if(y < tableUp && y > tableDown) {
					float actorHeight = actors.get(0).getHeight();
					int index = (int) ((tableUp - y) / actorHeight);
					System.out.println(index);

					onChoose(getSelectedActor(), actors.get(index));
					setSelectedIndex(index);
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Called when an Actor is choose
	 * @param actor the choosen actor
	 */
	protected void onChoose(T previouslySelected, T nextlySelected) {};
	
	@Override
	public void loadTable() {
		for(T actor: actors) {
			actor.setSize(getWidth(), getHeight() / 8);
			table.add(actor);
			table.row();
		}
		table.pack();
		table.setPosition(getX(), getY() + getHeight() - table.getHeight());
	}

	@Override
	public void reloadTable() {
		table.clear();
		loadTable();
		
	}
	public void addActor(T actor) {
		actors.add(actor);
		table.add(actor);
		table.row();
		reloadTable();
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		reloadTable();
	}
	
	public void removeActor(int index) {
		assert(index < actors.size());
		actors.remove(index);
		if(selectedIndex == index)
			selectedIndex = -1;
		reloadTable();
	}

	public void removeSelectedActor() {
		if(selectedIndex != -1) {
			actors.remove(selectedIndex);
			selectedIndex = -1;
			reloadTable();
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		table.draw(batch, parentAlpha);
	}

	private void moveTable() {
		table.setY(0);
	}
	/**
	 * 
	 * @return the selected index, or -1 if there is no selected actor
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * 
	 * @return the selected actor, or null if there is no selected actor
	 */
	public T getSelectedActor() {
		if(selectedIndex != -1) {
			return actors.get(selectedIndex);
		}
		return null;
	}

	public void setSelectedIndex(int selectedIndex) {
		actors.get(selectedIndex).setColor(Color.BLACK);
		this.selectedIndex = selectedIndex;
		actors.get(selectedIndex).setColor(Color.RED);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public LinkedList<T> getActors() {
		return actors;
	}
}
