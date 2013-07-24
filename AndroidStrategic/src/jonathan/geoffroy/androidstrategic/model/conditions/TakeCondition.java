package jonathan.geoffroy.androidstrategic.model.conditions;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

public abstract class TakeCondition extends Condition {
	private int nbTurns;
	private boolean taken;
	
	public TakeCondition(Map map, int nbTurns) {
		super(map);
		this.nbTurns = nbTurns;
	}
	
	@Override
	public boolean hasWon(Team team) {
		if(team.equals(map.getUserTeam())) {
			return taken;
		}
		else {
			return !taken && map.getNumTurn() > nbTurns;
		}
	}

}
