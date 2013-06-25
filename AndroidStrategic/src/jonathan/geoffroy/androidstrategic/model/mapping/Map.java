package jonathan.geoffroy.androidstrategic.model.mapping;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import javax.imageio.ImageIO;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.CureMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.KillMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.TrapMagic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class Map {
	public final static int CLOSEDDOOR = -16777216, DESERT = -459750, FLOOR = -14750720, FOREST = -13525728, 
			FORT = -9539712, GATE = -15654456, GRASS = -10377914, MOUNTAIN = -7713017, 
			PEAK = -3184105, PILLAR = -5395027, PIT = -10596800, PLAIN = -4325736, 
			ROAD = -33024, SEA = -11602448, VILLAGE = -655362;
	public final static int NOMAGIC = -1, TRAPMAGIC = -65536, KILLMAGIC = -16777216, CUREMAGIC = -16711936;
	public final static String SCENARII_DIR = "data/scenarii/";

	private Terrain[][] map;
	private LinkedHashSet<Terrain> terrains;
	private ArrayList<CoordMagic> terrainMagics;
	private ArrayList<CoordItem> terrainsItems;
	private HashMap<Fighter, Coord2D> fighters;
	private Reachable reachable;

	public Map() {
		terrains = new LinkedHashSet<Terrain>();
		terrainMagics = new ArrayList<CoordMagic>();
		terrainsItems = new ArrayList<CoordItem>();
		fighters = new HashMap<Fighter, Coord2D>();
	}

	public static Map load(String scenarioName, int chapterNum)
			throws IOException {
		Map loadedMap = new Map();

		loadedMap.loadTerrains(scenarioName, chapterNum);
		loadedMap.loadMagics(scenarioName, chapterNum);

		return loadedMap;
	}

	private void loadTerrains(String scenarioName, int chapterNum)
			throws IOException {
		HashMap<Integer, Terrain> createdTerrains = new HashMap<Integer, Terrain>();
		createdTerrains.put(CLOSEDDOOR, new ClosedDoor());
		createdTerrains.put(DESERT, new Desert());
		createdTerrains.put(FLOOR, new Floor());
		createdTerrains.put(FOREST, new Forest());
		createdTerrains.put(FORT, new Fort());
		createdTerrains.put(GATE, new Gate());
		createdTerrains.put(GRASS, new Grass());
		createdTerrains.put(MOUNTAIN, new Mountain());
		createdTerrains.put(PEAK, new Peak());
		createdTerrains.put(PILLAR, new Pillar());
		createdTerrains.put(PIT, new Pit());
		createdTerrains.put(PLAIN, new Plain());
		createdTerrains.put(ROAD, new Road());
		createdTerrains.put(SEA, new Sea());
		createdTerrains.put(VILLAGE, new Village());

		BufferedImage img;
		try {
			img = ImageIO.read(new FileInputStream(SCENARII_DIR + scenarioName + "/" + chapterNum + "_map.png"));
			int width = img.getWidth();
			int height = img.getHeight();
			map = new Terrain[height][width];
			int pixel;
			Terrain t;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					pixel = img.getRGB(j, i);
					t = createdTerrains.get(pixel);
					if (t == null) {
						throw new IOException("this terrain is NOT available");
					}
					map[i][j] = t;
					terrains.add(t);
				}
			}
			reachable = new Reachable(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadMagics(String scenarioName, int chapterNum)
			throws IOException {
		CureMagic cureMagic = new CureMagic();
		KillMagic killMagic = new KillMagic();
		TrapMagic trapMagic = new TrapMagic();

		BufferedImage img;
		try {
			img = ImageIO.read(new FileInputStream(SCENARII_DIR + scenarioName + "/" + chapterNum + "_magics.png"));
			int width = img.getWidth();
			int height = img.getHeight();
			int pixel;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					pixel = img.getRGB(j, i);
					switch(pixel) {
					case NOMAGIC:
						break;
					case CUREMAGIC:
						terrainMagics.add(new CoordMagic(new Coord2D(j, i), cureMagic));
						break;
					case KILLMAGIC:
						terrainMagics.add(new CoordMagic(new Coord2D(j, i), killMagic));
						break;
					case TRAPMAGIC:
						terrainMagics.add(new CoordMagic(new Coord2D(j, i), trapMagic));
						break;
					default:
						throw new IOException("this magic is NOT available: " + i + " " + j);
					}
				}
			}
		} catch(FileNotFoundException e) {}
	}

	/**
	 * test if attacker can hit defender, depending on they own Terrain, and the attacker's range
	 * @param attacker Fighter who attack defender
	 * @param defender Fighter assaulted
	 * @return TRUE if attacker can hit defender, depending on they own Terrain, and the attacker's range 
	 */
	public boolean canHit(Fighter attacker, Fighter defender) {
		assert(fighters.containsKey(attacker));
		assert(fighters.containsKey(defender));

		Coord2D coordAttacker = fighters.get(attacker);
		Coord2D coordDefender = fighters.get(defender);
		int attackerMinRange = attacker.minRange();
		int attackerMaxRange = attacker.maxRange();

		int manhattanDistance = Math.abs(coordAttacker.x - coordDefender.x) + Math.abs(coordAttacker.y - coordDefender.y);
		return manhattanDistance >= attackerMinRange && manhattanDistance <= attackerMaxRange;
	}

	public Terrain getTerrain(int x, int y) {
		return map[y][x];
	}

	public Terrain getTerrain(Fighter fighter) {
		assert(fighters.containsKey(fighter));
		Coord2D coord = fighters.get(fighter);
		return getTerrain(coord.x, coord.y);
	}

	public Terrain[][] getMap() {
		return map;
	}

	public void setMap(Terrain[][] map) {
		this.map = map;
	}

	/**
	 * Add a non previously added fighter on the Map, to an empty Coord2D(x, y)
	 * @param fighter
	 * @param x
	 * @param y
	 */
	public void addFighter(Fighter fighter, int x, int y) {
		assert(!fighters.containsKey(fighter));
		assert(y >= 0 && y < map.length);
		assert(x >= 0 && x < map[y].length);
		fighters.put(fighter, new Coord2D(x, y));
	}

	/**
	 * Move a previously added fighter to an empty Coord2D(x, y);
	 * @param fighter
	 * @param x
	 * @param y
	 */
	public void moveFighter(Fighter fighter, int x, int y) {
		assert(fighters.containsKey(fighter));
		assert(y >= 0 && y < map.length);
		assert(x >= 0 && x < map[y].length);
		assert(!fighters.containsValue(new Coord2D(x, y)));
		Coord2D coord = fighters.get(fighter);
		coord.x = x;
		coord.y = y;
	}

	/** 
	 * remove a previously added fighter
	 * @param fighter
	 */
	public void rmFighter(Fighter fighter) {
		assert(fighters.containsKey(fighter));
		fighters.remove(fighter);
	}

	public void clearFighters() {
		fighters.clear();		
	}

	public Magic getMagic(Coord2D coord) {
		for(CoordMagic c : terrainMagics) {
			if(coord.equals(c.coord))
				return c.magic;
		}
		return null;
	}

	public ArrayList<CoordMagic> getTerrainMagics() {
		return terrainMagics;
	}

	public int getWidth() {
		return map[0].length;
	}
	public int getHeight() {
		return map.length;
	}

	public Reachable getReachableTerrains(Fighter f) {
		reachable.clear();
		return addReachableTerrains(f);
	}

	public Reachable addReachableTerrains(Fighter f) {
		Coord2D coord = fighters.get(f);
		reachable.addReachable(coord);
		HashMap<Coord2D, Integer> already = new HashMap<Coord2D, Integer>();
		already.put(coord, (int) f.getMovementMax());
		calculateReach(f, f.getMovementMax(), coord.x, coord.y, already);

		return reachable;
	}

	private void calculateReach(Fighter fighter, int nbMovementsLeft, int x, int y, HashMap<Coord2D, Integer>alreadyReachable) {
		Coord2D testsCoord[] = { new Coord2D(x - 1, y), new Coord2D(x + 1, y) , new Coord2D(x, y - 1), new Coord2D(x, y + 1) };
		Coord2D currentCoord;
		Terrain currentTerrain;
		int currentMovementCost;
		int nextMovementLeft;
		Integer previousMovementLeft;
		HashMap<Coord2D, Integer>nextAlreadyReachable;
		for(int i = 0; i < testsCoord.length; i++) {
			currentCoord = testsCoord[i];

			if(currentCoord.y >= 0 && currentCoord.y < map.length &&
					currentCoord.x >= 0 && currentCoord.x < map[y].length) {
				currentTerrain = getTerrain(currentCoord.x, currentCoord.y);
				currentMovementCost = currentTerrain.getMovementCost();
				nextMovementLeft = nbMovementsLeft - currentMovementCost;

				if(currentMovementCost <= nbMovementsLeft) {
					if(currentTerrain.isStoppable(fighter)) {
						previousMovementLeft = alreadyReachable.get(currentCoord);

						if(previousMovementLeft == null || previousMovementLeft < nextMovementLeft) {	
							reachable.addReachable(currentCoord);

							if(currentTerrain.isTraversable(fighter)) {
								nextAlreadyReachable = (HashMap<Coord2D, Integer>) alreadyReachable.clone();
								nextAlreadyReachable.put(currentCoord, nextMovementLeft);
								calculateReach(fighter, nextMovementLeft, currentCoord.x, currentCoord.y, nextAlreadyReachable);
							}
						}
					}
				}

				// Assailable terrains 
				else {
					Coord2D touchCoord = new Coord2D();
					for(int j = fighter.minRange() - 1, maxRange = fighter.maxRange(); j < maxRange; j++) {
						if(currentCoord.x - j >= 0) {
							touchCoord.x  = currentCoord.x - j;
							touchCoord.y = currentCoord.y;
							reachable.addAssailable(touchCoord);
						}
						if(currentCoord.x + j <= map[currentCoord.y].length) {
							touchCoord.x  = currentCoord.x + j;
							touchCoord.y = currentCoord.y;
							reachable.addAssailable(touchCoord);
						}

						if(currentCoord.y - j >= 0) {
							touchCoord.x  = currentCoord.x;
							touchCoord.y = currentCoord.y - j;
							reachable.addAssailable(touchCoord);
						}
						if(currentCoord.y + j <= map.length) {
							touchCoord.x  = currentCoord.x;
							touchCoord.y = currentCoord.y + j;
							reachable.addAssailable(touchCoord);
						}
					}
				}
			}
		}
	}
}

class CoordMagic {
	public Coord2D coord;
	public Magic magic;

	public CoordMagic(Coord2D c, Magic m) {
		coord = c;
		magic = m;
	}
}

class CoordItem {
	public Coord2D coord;
	public Item item;

	public CoordItem(Coord2D c, Item i) {
		coord = c;
		item = i;
	}
}