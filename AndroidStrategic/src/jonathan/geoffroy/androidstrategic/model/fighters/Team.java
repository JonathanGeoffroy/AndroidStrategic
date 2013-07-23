package jonathan.geoffroy.androidstrategic.model.fighters;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Team {
	private static int nbTeams = 1;
	private String name;
	private ArrayList<Fighter> fighters;
	
	public Team() {
		name = "Team " + nbTeams;
		nbTeams++;
		fighters = new ArrayList<Fighter>();
	}

	public Team(String name) {
		this.name = name;
		fighters = new ArrayList<Fighter>();
	}

	public static Team load(String scenarioName) {
		Team team = null;
		Json json = new Json();
		team = json.fromJson(Team.class, Gdx.files.internal(Map.SCENARII_DIR + scenarioName + "/team.json"));
		return team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Fighter> getFighters() {
		return fighters;
	}

	/**
	 * Add a fighter to the team
	 * 
	 * @param fighter
	 *            the fighter to add
	 */
	public void addFighter(Fighter fighter) {
		fighters.add(fighter);
		fighter.setTeam(this);
	}

	/**
	 * Check if fighter is in this team or not
	 * 
	 * @param fighter
	 * @return true if the fighter is in this team
	 */
	public boolean isInTeam(Fighter fighter) {
		return fighters.contains(fighter);
	}
}