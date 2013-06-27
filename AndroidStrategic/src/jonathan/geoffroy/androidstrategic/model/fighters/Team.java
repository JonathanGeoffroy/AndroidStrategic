package jonathan.geoffroy.androidstrategic.model.fighters;

import java.util.LinkedList;

public class Team {
	private static int nbTeams = 1;
	private String name;
	private LinkedList<Fighter> fighters;
	
	public Team() {
		name = "Team " + nbTeams;
		nbTeams++;
		fighters = new LinkedList<Fighter>();
	}
	
	public Team(String name) {
		this.name = name;
		fighters = new LinkedList<Fighter>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Fighter> getFighters() {
		return fighters;
	}
	
	/**
	 * Add a fighter to the team
	 * @param fighter the fighter to add
	 */
	public void addFighter(Fighter fighter) {
		fighters.add(fighter);
		fighter.setTeam(this);
	}

	/**
	 * Check if fighter is in this team or not
	 * @param fighter
	 * @return true if the fighter is in this team
	 */
	public boolean isInTeam(Fighter fighter) {
		return fighters.contains(fighter);
	}
}