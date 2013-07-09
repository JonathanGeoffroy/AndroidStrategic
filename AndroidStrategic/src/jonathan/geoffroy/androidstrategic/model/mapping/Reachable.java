package jonathan.geoffroy.androidstrategic.model.mapping;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class Reachable {
	public static final int NONE = 0, REACHABLE = 1, ASSAILABLE = 2;
	
	private Map map;
	private int[][] reachableMap;
	private ArrayList<Fighter> fightersIncluded;
	
	public Reachable(Map map) {
		this.map = map;
		reachableMap = new int[map.getHeight()][map.getWidth()];
		fightersIncluded = new ArrayList<Fighter>();
	}
	
	public void clear() {
		reachableMap = new int[map.getHeight()][map.getWidth()];
		fightersIncluded.clear();
	}
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int[][] getReachableMap() {
		return reachableMap;
	}

	public void setReachableMap(int[][] terrain) {
		this.reachableMap = terrain;
	}

	public ArrayList<Fighter> getFightersIncluded() {
		return fightersIncluded;
	}

	public void setFightersIncluded(ArrayList<Fighter> fightersIncluded) {
		this.fightersIncluded = fightersIncluded;
	}

	public void addReachable(Coord2D reachable) {
		reachableMap[reachable.y][reachable.x] = REACHABLE;		
	}

	public void addAssailable(Coord2D touchCoord) {
		assert(touchCoord.y >= 0 && touchCoord.y < reachableMap.length);
		assert(touchCoord.x >= 0 && touchCoord.x < reachableMap[touchCoord.y].length);
		
		if(reachableMap[touchCoord.y][touchCoord.x] == NONE) {
			reachableMap[touchCoord.y][touchCoord.x] = ASSAILABLE;
		}
	}

	public boolean isReachable(Coord2D coord) {
		return reachableMap[coord.y][coord.x] == REACHABLE;
	}
}
