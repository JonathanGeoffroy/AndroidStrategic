package jonathan.geoffroy.androidstrategic.model.fighters;

import java.util.Vector;

public class Team {

	private String name;
	private Vector<Fighter> fighters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Fighter> getFighters() {
		return fighters;
	}
	public void setFighters(Vector<Fighter> fighters) {
		this.fighters = fighters;
	}

}