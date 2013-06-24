package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
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
import jonathan.geoffroy.androidstrategic.model.mapping.Road;
import jonathan.geoffroy.androidstrategic.model.mapping.Sea;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;
import jonathan.geoffroy.androidstrategic.model.mapping.Village;

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

		assertEquals("map should be completly loaded", 15, intMap[0].length);
		for (int i = 0; i < intMap[0].length; i++) {
			assertEquals("map " + 0 + "-" + i + "should be " + map.getTerrain(i, 0).getClass().getName(), 
					terrains[i].getClass().getName(),
					map.getTerrain(i, 0).getClass().getName());
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
		for (int i = 0; i < intMap.length; i++) {
			for (int j = 0; j < intMap[i].length; j++) {
				assertEquals("map should be completly loaded", 640,
						intMap[i].length);
				assertEquals("map " + i + "-" + j + "should be floor",
						floor.getClass().getName(),
						map.getTerrain(j, i).getClass().getName());
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
}