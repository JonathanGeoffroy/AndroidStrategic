package jonathan.geoffroy.androidstrategic.model.conditions;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

public class ExterminateCondition extends Condition {

	public ExterminateCondition(Map map) {
		super(map);
	}

	@Override
	public boolean hasWon(Team team) {
		// Find the ennemy of param team
		Team ennemy = map.getUserTeam();
		if(team.equals(ennemy)) {
			ennemy = map.getEnnemyTeam();
		}
		return ennemy.getFighters().isEmpty();
	}

}
