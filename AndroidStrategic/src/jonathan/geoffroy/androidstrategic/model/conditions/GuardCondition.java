package jonathan.geoffroy.androidstrategic.model.conditions;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

public class GuardCondition extends Condition {
	private int nbTurns;
	private boolean taken;
	
	public GuardCondition(Map map, int nbTurns) {
		super(map);
		this.nbTurns = nbTurns;
	}

	@Override
	public boolean hasWon(Team team) {
		if(team.equals(map.getUserTeam())) {
			return !taken && map.getNumTurn() > nbTurns;
		}
		else {
			return taken;
		}
	}

}
