package jonathan.geoffroy.androidstrategic.model.conditions;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

public abstract class Condition {
	protected Map map;
	
	public Condition(Map map) {
		this.map = map;
	}
	
	/**
	 * 
	 * @param team
	 * @return true if the team have won the battle; be careful: while return false, others team can haven't won too!
	 */
	public abstract boolean hasWon(Team team);
	
	/**
	 * 
	 * @return true if the user team has won; same result than hasWon(userTeam)
	 */
	public boolean userWon() {
		return hasWon(map.getUserTeam());
	}
	
	/**
	 * 
	 * @return true if the user team has won; same result than hasWon(ennemyTeam)
	 */
	public boolean userLost() {
		return hasWon(map.getEnnemyTeam());
	}
}
