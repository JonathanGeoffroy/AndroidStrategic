package jonathan.geoffroy.androidstrategic.model.conditions;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

public class GeneralCondition extends Condition {

	public GeneralCondition(Map map) {
		super(map);
	}

	@Override
	public boolean hasWon(Team team) {
		// Find the ennemy of param team
		Team ennemy = map.getUserTeam();
		if(team.equals(ennemy)) {
			ennemy = map.getEnnemyTeam();
		}
		for(Fighter f : ennemy.getFighters()) {
			if(f.isGeneral())
				return false;
		}
		return true; 
	}

}
