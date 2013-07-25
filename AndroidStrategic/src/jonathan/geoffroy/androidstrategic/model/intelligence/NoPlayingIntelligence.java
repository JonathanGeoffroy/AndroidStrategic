package jonathan.geoffroy.androidstrategic.model.intelligence;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;

/**
 * An A.I. which just end its turn
 * @author Jonathan
 *
 */
public class NoPlayingIntelligence extends Intelligence {

	public NoPlayingIntelligence(MapScreen mapScreen, Team team) {
		super(mapScreen, team);
	}
}
