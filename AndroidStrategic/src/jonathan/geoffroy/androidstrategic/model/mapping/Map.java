package jonathan.geoffroy.androidstrategic.model.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Knight;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Soldier;
import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.items.CureItem;
import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.CureMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.KillMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.TrapMagic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class Map {
	public final static int CLOSEDDOOR = 255, DESERT = -65281, FLOOR = 518783231, FOREST = 832381183, 
			FORT = 1852801279, GATE = 287426815, GRASS = 1638221567, MOUNTAIN = -1974532097, 
			PEAK = -815130625, PILLAR = -1381126657, PIT = 1582186751, PLAIN = -1107388161, 
			ROAD = -8453889, SEA = 1324740863, VILLAGE = -167772417;
	public static final int NO_ENNEMY = -1, PLAYER_CASE = 65535, ENNEMY_SOLDIER = -16776961, ENNEMY_KNIGHT = 255,
			ENNEMY_ARCHER = 16711935, ENNEMY_AXMAN = -16711681,
			ENNEMY_PRIEST = 16777215, ENNEMY_MAGE = -65281;
	public final static int NOMAGIC = -1, TRAPMAGIC = 255, KILLMAGIC = -16776961, CUREMAGIC = 16711935;
	private static final int NO_ITEM = -1, CURE_ITEM = 16711935;
	public static final int ATTACK = 0, PUSH = 1, SUPER_PUSH = 2, OBJECT = 3, EXCHANGE = 4, SAVE = 5, LEAVE = 6, SUSPEND = 7, END_FIGTH = 8, END_TURN = 9;
	public static final int NB_MENUS = 10;
	public final static String SCENARII_DIR = "data/scenarii/";
	public final static int USER_TEAM = 0, ENNEMY_TEAM = 1;
	private Terrain[][] map;
	private LinkedHashSet<Terrain> terrains;
	private ArrayList<CoordMagic> terrainMagics;
	private ArrayList<CoordItem> terrainItems; 
	private ArrayList<Coord2D> beginPlayers; 
	private HashMap<Fighter, Coord2D> fighters;
	private HashMap<Coord2D, Fighter> coordFighters;
	private Reachable reachable;
	private ArrayList<Team> teams;

	public Map() {
		terrains = new LinkedHashSet<Terrain>();
		terrainMagics = new ArrayList<CoordMagic>();
		terrainItems = new ArrayList<CoordItem>();
		fighters = new HashMap<Fighter, Coord2D>();
		coordFighters = new HashMap<Coord2D, Fighter>();
		beginPlayers = new ArrayList<Coord2D>();
		teams = new ArrayList<Team>(2);
		for(int i = 0; i < 2; i++) {
			teams.add(new Team());
		}
	}

	public static Map load(String scenarioName, int chapterNum)
			throws IOException {
		Map loadedMap = new Map();

		loadedMap.loadTerrains(scenarioName, chapterNum);
		loadedMap.loadMagics(scenarioName, chapterNum);
		loadedMap.loadItems(scenarioName, chapterNum);
		loadedMap.loadFighters(scenarioName, chapterNum);
		Fighter.setMap(loadedMap);
		return loadedMap;
	}

	private void loadItems(String scenarioName, int chapterNum) {
		try {
			FileHandle file = Gdx.files.internal(SCENARII_DIR + scenarioName + "/" + chapterNum + "_items.bmp");
			Pixmap img = new Pixmap(file);
			int width = img.getWidth();
			int height = img.getHeight();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					switch(img.getPixel(j, i)) {
					case NO_ITEM: 
						break;
					case CURE_ITEM:
						terrainItems.add(new CoordItem(new Coord2D(j, i), new CureItem("Potion", 10, (short) 3)));
						break;
					}
				}
			}
		} catch (Exception e) {}
	}

	private void loadFighters(String scenarioName, int chapterNum) 
			throws IOException {
		Team ennemy = teams.get(ENNEMY_TEAM);
		Fighter fighter;

		try {
			FileHandle file = Gdx.files.internal(SCENARII_DIR + scenarioName + "/" + chapterNum + "_fighters.bmp");
			Pixmap img = new Pixmap(file);
			int width = img.getWidth();
			int height = img.getHeight();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					switch(img.getPixel(j, i)) {
					case NO_ENNEMY: 
						continue;
					case PLAYER_CASE:
						beginPlayers.add(new Coord2D(j, i));
						continue;

					case ENNEMY_SOLDIER:
						fighter = new Soldier();
						break;
					case ENNEMY_KNIGHT:
						fighter = new Knight();
						break;
					case ENNEMY_ARCHER:
						fighter = new Archer();
						break;
					case ENNEMY_AXMAN:
						fighter = new Axman();
						break;
					case ENNEMY_PRIEST:
						fighter = new Priest();
						break;
					case ENNEMY_MAGE:
						fighter = new Mage();
						break;
					default:
						throw new IOException("this fighter is NOT available: " + i + " " + j);
					}
					addFighter(fighter, j, i);
					ennemy.addFighter(fighter);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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

		try {
			FileHandle file = Gdx.files.internal(SCENARII_DIR + scenarioName + "/" + chapterNum + "_map.bmp");
			Pixmap img = new Pixmap(file);
			int width = img.getWidth();
			int height = img.getHeight();
			map = new Terrain[height][width];
			int pixel;
			Terrain t;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					pixel = img.getPixel(j, i);
					t = createdTerrains.get(pixel);
					if (t == null) {
						throw new IOException("this terrain is NOT available: " + j + " - " + i);
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

		try {
			FileHandle file = Gdx.files.internal(SCENARII_DIR + scenarioName + "/" + chapterNum + "_magics.bmp");
			Pixmap img = new Pixmap(file);
			int width = img.getWidth();
			int height = img.getHeight();
			int pixel;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					pixel = img.getPixel(j, i);
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

	public ArrayList<Fighter> getFighters() {
		return new ArrayList<Fighter>(fighters.keySet());
	}

	public HashMap<Coord2D, Fighter> getCoordFighters() {
		return coordFighters;
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
		Coord2D coord = new Coord2D(x, y);
		assert(!fighters.containsKey(fighter));
		assert(!coordFighters.containsKey(coord));
		assert(y >= 0 && y < map.length);
		assert(x >= 0 && x < map[y].length);
		fighters.put(fighter, coord);
		coordFighters.put(coord, fighter);
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
		assert(!fighters.containsValue(new Coord2D(x, y))  || coordFighters.get(new Coord2D(x, y)).equals(fighter));
		Coord2D coord = fighters.get(fighter);
		assert(coordFighters.containsKey(coord));
		coordFighters.remove(coord);
		coord.x = x;
		coord.y = y;
		coordFighters.put(coord, fighter);

		fighter.setMoved();
	}

	/** 
	 * remove a previously added fighter
	 * @param fighter
	 */
	public void rmFighter(Fighter fighter) {
		assert(fighters.containsKey(fighter));
		Coord2D coord = fighters.get(fighter);
		assert(coordFighters.containsValue(fighter));
		fighters.remove(fighter);
		Fighter f = coordFighters.remove(coord);
		assert f != null;
	}

	public void clearFighters() {
		fighters.clear();
		coordFighters.clear();
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
		already.put(coord, (int) f.getMovement());
		calculateReach(f, f.getMovement(), coord.x, coord.y, already);

		return reachable;
	}

	@SuppressWarnings("unchecked")
	private void calculateReach(Fighter fighter, int nbMovementsLeft, int x, int y, HashMap<Coord2D, Integer>alreadyReachable) {
		Coord2D testsCoord[] = { new Coord2D(x - 1, y), new Coord2D(x + 1, y) , new Coord2D(x, y - 1), new Coord2D(x, y + 1) };
		Coord2D currentCoord;
		Terrain currentTerrain;
		int currentMovementCost;
		int nextMovementLeft;
		Integer previousMovementLeft;
		Fighter fighterAtCoord;
		HashMap<Coord2D, Integer>nextAlreadyReachable;

		for(int i = 0; i < testsCoord.length; i++) {
			currentCoord = testsCoord[i];

			// If the testde terrain is in map
			if(currentCoord.y >= 0 && currentCoord.y < map.length &&
					currentCoord.x >= 0 && currentCoord.x < map[y].length) {

				currentTerrain = getTerrain(currentCoord.x, currentCoord.y);
				currentMovementCost = currentTerrain.getMovementCost(fighter);
				nextMovementLeft = nbMovementsLeft - currentMovementCost;
				fighterAtCoord = coordFighters.get(currentCoord);
				previousMovementLeft = alreadyReachable.get(currentCoord);

				//if fighter can reach the terrain
				if(currentMovementCost <= nbMovementsLeft &&
						currentTerrain.isStoppable(fighter) && 
						(previousMovementLeft == null || previousMovementLeft < nextMovementLeft) &&	
						currentTerrain.isTraversable(fighter) && (fighterAtCoord == null || !fighterAtCoord.isEnnemy(fighter)))
				{

					reachable.addReachable(currentCoord);
					//recursive for next terrains 
					nextAlreadyReachable = (HashMap<Coord2D, Integer>) alreadyReachable.clone();
					nextAlreadyReachable.put(currentCoord, nextMovementLeft);
					calculateReach(fighter, nextMovementLeft, currentCoord.x, currentCoord.y, nextAlreadyReachable);
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

	public void addMagic(Coord2D coord, Magic magic) {
		terrainMagics.add(new CoordMagic(coord, magic));
	}

	/**
	 * Return the item at Terrain defined by coord
	 * @param coord the Terrain where we search the item
	 * @return the item if exist, else null
	 */
	public Item getItem(Coord2D coord) {
		for(CoordItem c : terrainItems) {
			if(coord.equals(c.coord)) {
				return c.item;
			}
		}
		return null;
	}

	public void setTerrain(Coord2D coord, Terrain newTerrain) {
		assert(coord.y >= 0 && coord.y < map.length);
		assert(coord.x >= 0 && coord.y < map[coord.x].length);
		map[coord.y][coord.x] = newTerrain;		
	}

	public ArrayList<Terrain> getTerrains() {
		return new ArrayList<Terrain>(terrains);
	}

	/**
	 * Return the fighter pointed by the parameter coord, or null if the coord's Terrain is empty.
	 * @param coord
	 * @return fighter on coord's Terrain, or null if Terrain is empty
	 */
	public Fighter getFighterAt(Coord2D coord) {
		return coordFighters.get(coord);
	}

	/**
	 * Compute the possibles actions that the fighter can do.
	 * @param fighter the fighter who do something. Must be called even if fighter's null.
	 * @return a List of possibilities.
	 */
	public ArrayList<Integer> possibilities(Fighter fighter) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if(fighter != null) {
			if(canAttackAnyOne(fighter)) {
				result.add(ATTACK);
			}
			// TODO: push & super_push

			result.add(OBJECT);

			if(touchAlly(fighter)) {
				result.add(EXCHANGE);
				if(canSaveAnyOne(fighter)) {
					result.add(SAVE);
				}
				//				if(fighter.getSaved() != null) {
				//					result.add(DISPOSE);
				//				}
			}
			result.add(END_FIGTH);
		}

		result.add(SUSPEND);
		result.add(END_TURN);
		return result;
	}

	/**
	 * 
	 * @param fighter fighter who attack
	 * @return TRUE if the fighter can attack at least 1 ennemy
	 */
	private boolean canAttackAnyOne(Fighter fighter) {
		return !assailableTerrains(fighter).isEmpty();
	}

	/**
	 * @param fighter
	 * @return a Coord's list for all assailables Terrains
	 */
	private ArrayList<Coord2D> assailableTerrains(Fighter fighter) {
		ArrayList<Coord2D> result = new ArrayList<Coord2D>();
		return result;
	}

	/**
	 * 
	 * @param fighter
	 * @return TRUE if the fighter touch at least 1 ally
	 */
	private boolean touchAlly(Fighter fighter) {
		return !touchAllyTerrains(fighter).isEmpty();
	}

	/**
	 * 
	 * @param fighter
	 * @return a Coord's list of ally touched
	 */
	private ArrayList<Coord2D> touchAllyTerrains(Fighter fighter) {
		Coord2D fighterCoord = fighters.get(fighter);
		Coord2D coord = new Coord2D();

		int x[] = {fighterCoord.x - 1, fighterCoord.x + 1, fighterCoord.x, fighterCoord.x};
		int y[] = {fighterCoord.y, fighterCoord.y, fighterCoord.y - 1, fighterCoord.y + 1};
		Fighter currentFighter;

		ArrayList<Coord2D> result = new ArrayList<Coord2D>();
		for(int i = 0; i < x.length; i++) {
			coord = new Coord2D(x[i], y[i]);
			currentFighter = coordFighters.get(coord);
			if(currentFighter != null && !currentFighter.isEnnemy(fighter)) {
				result.add(coord);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param fighter
	 * @return TRUE if fighter can save at least 1 ally
	 */
	private boolean canSaveAnyOne(Fighter fighter) {
		return !saveTerrains(fighter).isEmpty();
	}

	/**
	 * 
	 * @param fighter
	 * @return a Coord's list of terrains containing an ally who can saved by the fighter
	 */
	private ArrayList<Coord2D> saveTerrains(Fighter fighter) {
		Coord2D fighterCoord = fighters.get(fighter);
		Coord2D coord = new Coord2D();

		int x[] = {fighterCoord.x - 1, fighterCoord.x + 1, fighterCoord.x, fighterCoord.x};
		int y[] = {fighterCoord.y, fighterCoord.y, fighterCoord.y - 1, fighterCoord.y + 1};
		Fighter currentFighter;

		ArrayList<Coord2D> result = new ArrayList<Coord2D>();
		for(int i = 0; i < x.length; i++) {
			coord = new Coord2D(x[i], y[i]);
			currentFighter = coordFighters.get(coord);
			if(currentFighter != null && !fighter.canSave(currentFighter)) {
				result.add(coord);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param coord
	 * @return true if coord define a terrain which a player can begin
	 */
	public boolean isBeginPlayersTerrain(Coord2D coord) {
		return beginPlayers.contains(coord);
	}

	/**
	 * 
	 * @return true if there is at least 1 begin case which is empty
	 */
	public boolean hasFreeBeginCase() {
		for(Coord2D c : beginPlayers) {
			if(!coordFighters.containsKey(c)) {
				return true;
			}
		}
		return false;
	}

	public Team getUserTeam() {
		return teams.get(USER_TEAM);
	}

	public Team getEnnemyTeam() {
		return teams.get(ENNEMY_TEAM);
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