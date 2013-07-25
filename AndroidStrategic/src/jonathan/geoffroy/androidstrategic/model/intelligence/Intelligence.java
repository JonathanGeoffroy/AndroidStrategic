package jonathan.geoffroy.androidstrategic.model.intelligence;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;

public abstract class Intelligence {
	protected MapScreen mapScreen;
	protected Team team;


	public Intelligence(MapScreen mapScreen, Team team) {
		super();
		this.mapScreen = mapScreen;
		this.team = team;
	}

	/**
	 * Move the fighter at the @from terrain, to  @to terrain
	 * Select the fighter to move 500 ms before move it, and wait 500 ms again, to be more graphical
	 * @param from the coord of the fighter to move
	 * @param to the final terrain of the fighter
	 */
	public void graphicalMove(Coord2D from, Coord2D to) {
		try {
			Map map = mapScreen.getMap();
			mapScreen.setCoordFighter(from);
			wait(500);
			map.moveFighter(map.getFighterAt(from), to.x, to.y);
		} catch (InterruptedException e) {
		}

	}

	public void play() {
		System.out.println("intelligence is playing");
		mapScreen.endTurn();
	}
}
