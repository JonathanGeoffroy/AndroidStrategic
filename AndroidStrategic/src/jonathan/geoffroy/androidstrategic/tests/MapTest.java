package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Sword;
import jonathan.geoffroy.androidstrategic.model.mapping.ClosedDoor;
import jonathan.geoffroy.androidstrategic.model.mapping.Desert;
import jonathan.geoffroy.androidstrategic.model.mapping.Floor;
import jonathan.geoffroy.androidstrategic.model.mapping.Forest;
import jonathan.geoffroy.androidstrategic.model.mapping.Fort;
import jonathan.geoffroy.androidstrategic.model.mapping.Gate;
import jonathan.geoffroy.androidstrategic.model.mapping.Grass;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.model.mapping.Mountain;
import jonathan.geoffroy.androidstrategic.model.mapping.Peak;
import jonathan.geoffroy.androidstrategic.model.mapping.Pillar;
import jonathan.geoffroy.androidstrategic.model.mapping.Pit;
import jonathan.geoffroy.androidstrategic.model.mapping.Plain;
import jonathan.geoffroy.androidstrategic.model.mapping.Reachable;
import jonathan.geoffroy.androidstrategic.model.mapping.Road;
import jonathan.geoffroy.androidstrategic.model.mapping.Sea;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.model.mapping.Village;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.CureMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.KillMagic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.TrapMagic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

import org.junit.Test;

public class MapTest extends Map {
	private Human[] fighters;
	private Map map;

	@Test
	public void loadMap() {
		Terrain[][] intMap;

		// terrains tests
		try {
			map = Map.load("Test", 1);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertTrue("map should be loaded", map != null);

		intMap = map.getMap();
		Terrain[] terrains = { new ClosedDoor(), new Desert(), new Floor(),
				new Forest(), new Fort(), new Gate(), new Grass(),
				new Mountain(), new Peak(), new Pillar(), new Pit(),
				new Plain(), new Road(), new Sea(), new Village() };
		Magic[] magics = {null, null, null,
				new CureMagic(), new CureMagic(), new CureMagic(),
				new KillMagic(), new KillMagic(), new KillMagic(),
				new TrapMagic(), new TrapMagic(), new TrapMagic(),
				null, null, null
		};
		assertEquals("map should be completly loaded", 15, intMap[0].length);
		for (int i = 0; i < intMap[0].length; i++) {
			assertEquals("map " + 0 + "-" + i + "should be " + terrains[i].getClass().getName(), 
					terrains[i].getClass().getName(),
					map.getTerrain(i, 0).getClass().getName());

			assertEquals("magic " + 0 + "-" + i + "should be " + magics[i], magics[i], map.getMagic(new Coord2D(i, 0)));
		}

		// Floor map test
		try {
			map = Map.load("Test", 2);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertTrue("map should be loaded", map != null);

		intMap = map.getMap();
		Floor floor = new Floor();

		assertEquals("map should be completly loaded", 480, intMap.length);
		assertTrue("shouldn't have any magic on this map", map.getTerrainMagics().isEmpty());
		for (int i = 0; i < intMap.length; i++) {
			for (int j = 0; j < intMap[i].length; j++) {
				assertEquals("map should be completly loaded", 640,
						intMap[i].length);
				assertEquals("map " + i + "-" + j + "should be floor",
						floor.getClass().getName(),
						map.getTerrain(j, i).getClass().getName());
				assertNull("shouldn't have any magic on this map", map.getMagic(new Coord2D(j, i)));
			}
		}

	}

	
	@Test
	public void fighterMoving() {
		mapInitialization(2);
		fighterInitialization();
		
		for(int i = 0; i < map.getHeight(); i++) {
			for(int j = 0; j < map.getWidth(); j++) {
				map.addFighter(fighters[0], j, i);
				assertEquals("fighter should be in terrain " + j + "-" + i, fighters[0], map.getCoordFighters().get(new Coord2D(j, i)));
				assertEquals("should have only 1 fighter", 1, map.getCoordFighters().size());
				assertEquals("should have only 1 fighter", 1, map.getFighters().size());
				map.rmFighter(fighters[0]);
				assertEquals("shouldn't have any fighter", 0, map.getCoordFighters().size());
				assertEquals("shouldn't have any fighter", 0, map.getFighters().size());
			}
		}
		
		map.addFighter(fighters[1], 0, 0);
		for(int i = 0; i < map.getHeight(); i++) {
			for(int j = 0; j < map.getWidth(); j++) {
				map.moveFighter(fighters[1], j, i);
				assertEquals("fighter should be in terrain " + j + "-" + i, fighters[1], map.getCoordFighters().get(new Coord2D(j, i)));
				assertEquals("should have only 1 fighter", 1, map.getCoordFighters().size());
				assertEquals("should have only 1 fighter", 1, map.getFighters().size());
			}
		}
	}
	
	@Test
	public void range() {
		fighterInitialization();
		try {
			map = Map.load("Test", 2);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		map.addFighter(fighters[0], 0, 0);
		map.addFighter(fighters[1], 1, 0);
		assertTrue("fighter 1 should can hit fighter 2", map.canHit(fighters[0], fighters[1]));

		map.moveFighter(fighters[1], 2, 0);
		assertFalse("fighter 1 shouldn't can hit fighter 2", map.canHit(fighters[0], fighters[1]));
		map.rmFighter(fighters[1]);
		fighters[1] = new Archer();
		fighters[1].setEquiped(new Bow());
		map.addFighter(fighters[1], 2, 0);
		assertTrue("Archer should can hit Ranger", map.canHit(fighters[1], fighters[0]));
		assertFalse("Ranger shouldn't can hit Archer", map.canHit(fighters[0], fighters[1]));

		map.moveFighter(fighters[1], 0, 2);
		assertTrue("Archer should can hit Ranger", map.canHit(fighters[1], fighters[0]));
		assertFalse("Ranger shouldn't can hit Archer", map.canHit(fighters[0], fighters[1]));

		map.moveFighter(fighters[1], 1, 1);
		assertTrue("Archer should can hit Ranger", map.canHit(fighters[1], fighters[0]));
		assertFalse("Ranger shouldn't can hit Archer", map.canHit(fighters[0], fighters[1]));

		map.moveFighter(fighters[1], 2, 1);
		assertFalse("Archer shouldn't can hit Ranger", map.canHit(fighters[1], fighters[0]));
		assertFalse("Ranger shouldn't can hit Archer", map.canHit(fighters[0], fighters[1]));

		map.moveFighter(fighters[1], 0, 1);
		assertFalse("Archer shouldn't can hit Ranger", map.canHit(fighters[1], fighters[0]));
		assertTrue("Ranger should can hit Archer", map.canHit(fighters[0], fighters[1]));
	}

	private void fighterInitialization() {
		fighters = new Human[2];
		for (int i = 0; i < fighters.length; i++) {
			fighters[i] = new Ranger();
			fighters[i].setEquiped(new Sword());
		}
	}

	private void mapInitialization(int num) {
		try {
			map = Map.load("Test", num);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void reachability() {		
		// fighter on top-left corner
		fighterInitialization();
		mapInitialization(2);
		reachTest(new Coord2D(0, 0));
		map.clearFighters();

		//fighter on top-right corner
		reachTest(new Coord2D(map.getWidth() - 1, 0));
		map.clearFighters();

		//fighter on bottom-left corner
		reachTest(new Coord2D(0, map.getHeight() - 1));
		map.clearFighters();

		//fighter on bottom-right corner
		reachTest(new Coord2D(map.getWidth() - 1, map.getHeight() - 1));
		map.clearFighters();

		//fighter on middle
		reachTest(new Coord2D(map.getWidth()/2, map.getHeight()/2));
		map.clearFighters();
	}

	private void reachTest(Coord2D fighterCoord) {
		Reachable reachables;
		int[][] reachableMap;

		map.addFighter(fighters[0], fighterCoord.x, fighterCoord.y);
		reachables = map.getReachableTerrains(fighters[0]);
		reachableMap = reachables.getReachableMap();
		for(int i = 0 ; i < reachableMap.length; i++) {
			for(int j = 0; j < reachableMap[i].length; j++) {
				if(!map.getTerrain(j, i).isTraversable(fighters[0])) {
					assertEquals("figher shouln't reach a non traversable Terrain", Reachable.NONE, reachableMap[i][j]);
				}
				else if(Math.abs(fighterCoord.x - j) + Math.abs(fighterCoord.y - i) <= fighters[0].getMovementMax()) {
					assertEquals("fighter should reach this terrain: " + j + "-" + i,
							Reachable.REACHABLE, reachableMap[i][j]);
				}
				else if(Math.abs(fighterCoord.x - j) + Math.abs(fighterCoord.y - i) >= fighters[0].getMovementMax() + fighters[0].minRange() &&
						Math.abs(fighterCoord.x - j) + Math.abs(fighterCoord.y - i) <= fighters[0].getMovementMax() + fighters[0].maxRange()) {
					assertEquals("fighter should hit this terrain: " + j + "-" + i,
							Reachable.ASSAILABLE, reachableMap[i][j]);
				}
				else {
					assertEquals("fighter shouln't reach or hit this terrain: " + j + "-" + i,
							Reachable.NONE, reachableMap[i][j]);
				}
			}
		}
	}

	@Test
	public void ennemiesBlock() {
		mapInitialization(2);
		Team team1 = new Team(), team2 = new Team();
		int x[] = {1, 0, 2, 1, 1};
		int y[] = {1, 1, 1, 0, 2};
		fighters = new Human[5];
		Sword sword = new Sword();
		Human h;
		for(int i = 0; i < fighters.length; i++) {
			fighters[i] = new Ranger();
			h = fighters[i];
			h.setEquiped(sword);
			map.addFighter(h, x[i], y[i]);
		}
		team1.addFighter(fighters[0]);
		for(int i = 1; i < fighters.length; i++) {
			team2.addFighter(fighters[i]);
		}
		Reachable reachables;
		int[][] reachableMap;

		reachables = map.getReachableTerrains(fighters[0]);
		reachableMap = reachables.getReachableMap();
		for(int i = 0 ; i < reachableMap.length; i++) {
			for(int j = 0; j < reachableMap[i].length; j++) {
				if(!map.getTerrain(j, i).isTraversable(fighters[0])) {
					assertEquals("figher shouln't reach a non traversable Terrain", Reachable.NONE, reachableMap[i][j]);
				}
				else if(j == x[0] && i == y[0]) {
					assertEquals("fighter should reach this terrain: " + j + "-" + i,
							Reachable.REACHABLE, reachableMap[i][j]);
				}
				else if(Math.abs(x[0] - j) + Math.abs(y[0] - i) >= fighters[0].minRange() &&
						Math.abs(x[0] - j) + Math.abs(y[0] - i) <=  fighters[0].maxRange()) {
					assertEquals("fighter should hit this terrain: " + j + "-" + i,
							Reachable.ASSAILABLE, reachableMap[i][j]);
				}
				else {
					assertEquals("fighter shouln't reach or hit this terrain: " + j + "-" + i,
							Reachable.NONE, reachableMap[i][j]);
				}
			}
		}
	}
}