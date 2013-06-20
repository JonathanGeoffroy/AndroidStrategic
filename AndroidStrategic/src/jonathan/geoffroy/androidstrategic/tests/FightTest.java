package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;
import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Cat;
import jonathan.geoffroy.androidstrategic.model.fighters.FightResult;
import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Hawk;
import jonathan.geoffroy.androidstrategic.model.fighters.Heron;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.fighters.Lion;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Soldier;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
import jonathan.geoffroy.androidstrategic.model.fighters.Thief;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Sword;
import jonathan.geoffroy.androidstrategic.model.mapping.Desert;
import jonathan.geoffroy.androidstrategic.model.mapping.Grass;
import jonathan.geoffroy.androidstrategic.model.mapping.Road;
import jonathan.geoffroy.androidstrategic.model.mapping.Terrain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FightTest {
	Fighter fighters[];
	FightResult result;

	@Before
	public void preConditions() {
		if(fighters != null) {
			for(int i = 0; i < fighters.length; i++)
				fightConditions(fighters[i]);
			if(result != null) {
				fightResultConditions();
			}
		}
	}

	@After
	public void postConditions() {
		if(fighters != null) {
			for(int i = 0; i < fighters.length; i++)
				fightConditions(fighters[i]);
		}
		if(result != null) {
			fightResultConditions();
		}
	}

	public void fightConditions(Fighter f) {
		assertTrue("hp should be least or equals than hpMax", f.getHp() <= f.getHpMax());
		assertTrue("if hp <= 0,\n fighter should be dead", f.getHp() > 0 || f.isDead());
		assertTrue("hp shouldn't be negative", f.getHp() >= 0);
	}

	private void fightResultConditions() {
		assertEquals("a battle should have 2 fighters", result.getFighters().length, 2);
		assertTrue("fighters shouldn't be null", result.getFighters()[0] != null && result.getFighters()[1] != null);
		for(int i = 0; i < 2; i++) {
			assertTrue("hit sum should be positive", result.getSumDamages(i) >= 0);
			for(int j = 0; j < result.getNumberAttacks(i); j++)
				assertTrue("each hit should be positive", result.getInflictedDamages()[i][j] >= 0);
			
			assertTrue("fighter should lost sum inflicted points (or hp = 0 if he's died)",
					result.getSumDamages(i) == fighters[(i + 1) % 2].getHpMax() - fighters[(i + 1) % 2].getHp() ||
					fighters[(i + 1) % 2].isDead() && fighters[(i + 1) % 2].getHp() == 0 
					);
			assertEquals("fighter.isDead() should be equals to result.isDead()", fighters[i].isDead(), result.isDead(i));
		}
	}

	@Test
	public void fighterInitialization() {
		Fighter[] fightersArray = {
				new Archer(),
				new Axman(),
				new Cat(),
				new Hawk(),
				new Heron(),
				new Lion(),
				new Mage(),
				new Priest(),
				new Soldier(),
				new Ranger(),
				new Thief()
		};
		this.fighters = fightersArray;

		for(Fighter f: fighters) {
			assertTrue(f.getName() + ": at initialization, hp should be > 0", f.getHp() > 0);
			assertEquals(f.getName() + ": at initialization, hp should be equals hpMax", f.getHp(), f.getHpMax());
			assertEquals(f.getName() + ": at initialization, movement should be equals movementMax", f.getMovement(), f.getMovementMax());
			assertFalse(f.getName() + ": at initialization, fighter should not be dead", f.isDead());
		}
	}

	@Test
	public void hitNumber() {
		fighters = new Fighter[2];
		fighters[0] = new Archer();
		fighters[1] = new Archer();
		assertEquals("should hit only 1 time", fighters[0].hitNumber(fighters[1]), 1);
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		assertEquals("should hit twice", fighters[0].hitNumber(fighters[1]), 2);
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		assertEquals("should hit twice", fighters[0].hitNumber(fighters[1]), 2);
	}

	@Test
	public void accuracy() {
		fighters = new Fighter[2];
		fighters[0] = new Archer();
		fighters[1] = new Archer();
		fighters[0].setTerrain(new Road());
		fighters[1].setTerrain(new Desert());
		Bow bow = new Bow();
		bow.setHitRate((short)50);

		int accuracyWithNoWeapon = fighters[0].accuracy(fighters[1]);
		Human h = (Human)fighters[0];
		h.setEquiped(bow);

		assertTrue("accuracy should be between 0 & 100", fighters[0].accuracy(fighters[1]) >= 0 && fighters[0].accuracy(fighters[1]) <= 100);
		assertTrue("accuracy should be between 0 & 100", fighters[1].accuracy(fighters[0]) >= 0 && fighters[1].accuracy(fighters[0]) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", fighters[0].accuracy(fighters[1]) == accuracyWithNoWeapon + bow.getHitRate() || fighters[0].accuracy(fighters[1]) == 100);

		bow.setHitRate((short)90);
		assertTrue("accuracy should be between 0 & 100", fighters[0].accuracy(fighters[1]) >= 0 && fighters[0].accuracy(fighters[1]) <= 100);
		assertTrue("accuracy should be between 0 & 100", fighters[1].accuracy(fighters[0]) >= 0 && fighters[1].accuracy(fighters[0]) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", fighters[0].accuracy(fighters[1]) == accuracyWithNoWeapon + bow.getHitRate() || fighters[0].accuracy(fighters[1]) == 100);
	}

	@Test
	public void touchedRate() {
		fighters = new Fighter[2];
		Terrain terrain = new Grass();
		terrain.setAvoid((short)0);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
		}

		int nbTouched[] = new int[2];
		for(int i = 0; i < 1000000; i++) {
			fighters[0].setHp(fighters[0].getHpMax());
			fighters[1].setHp(fighters[1].getHpMax());
			result = fighters[0].fight(fighters[1]);
			for(int j = 0; j < 2; j++) {
				if(result.getTouched()[j][0]) {
					nbTouched[j]++;
				}
			}
		}

		assertTrue("Both fighters should do critical hits at the same frequence",
				nbTouched[0] >= nbTouched[1] - 200 &&
				nbTouched[0] <= nbTouched[1] + 200
				);

		for(int i = 0; i < 2; i++) {
			assertTrue("fighter critical frequence should follow his critical rates",
					nbTouched[i] >= fighters[i].hitRate() - 100 &&
					nbTouched[i] >= fighters[i].hitRate() - 100
					);
		}
	}

	@Test 
	public void criticalRate() {
		fighters = new Human[2];
		Sword sword = new Sword();
		sword.setHitRate((short) 80);
		Terrain terrain = new Grass();
		terrain.setAvoid((short)0);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			Human h = (Human)fighters[i];
			h.setEquiped(sword);
		}

		int nbCritical[] = new int[2];
		for(int i = 0; i < 500000; i++) {
			fighters[0].setHp(fighters[0].getHpMax());
			fighters[1].setHp(fighters[1].getHpMax());
			result = fighters[0].fight(fighters[1]);
			for(int j = 0; j < 2; j++) {
				if(result.getCriticalDamages()[j][0]) {
					nbCritical[j]++;
				}
			}
		}

		assertTrue("Both fighters should do critical hits at the same frequence",
				nbCritical[0] >= nbCritical[1] - 500 &&
				nbCritical[0] <= nbCritical[1] + 500
				);

		for(int i = 0; i < 2; i++) {
			assertTrue("fighter critical frequence should follow his critical rates",
					nbCritical[i] >= fighters[i].criticalRates() - 100 &&
					nbCritical[i] >= fighters[i].criticalRates() - 100
					);
		}
	}

	@Test
	public void fight() {
		initializeRangers();

		result = fighters[0].fight(fighters[1]);

		//Equals strength & speed case
		for(int i = 0; i < 2; i++) {
			assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(i), 1);
			assertTrue("Fighter missed attack should hit 0 pv", result.getTouched()[i][0] || result.getInflictedDamages()[i][0] == 0);
			assertTrue("Fighter critical attack should hit 3 * strength", 
					!result.getTouched()[i][0] || 
					result.getCriticalDamages()[i][0] && result.getInflictedDamages()[i][0] == 3 * fighters[i].attackStrength(fighters[(i+1)%2], true) ||
					!result.getCriticalDamages()[i][0] && result.getInflictedDamages()[i][0] == fighters[i].attackStrength(fighters[(i+1)%2], true)
					);
			assertEquals("Fighter should not be dead", result.isDead(i), false);
		}

		//twice hitting case
		initializeRangers();
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		result = fighters[0].fight(fighters[1]);
		assertEquals("Fighter should be hit twice", result.getNumberAttacks(0), 2);
		assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(1), 1);

		//Dead case
		initializeRangers();
		fighters[0].setStrength((short)100);
		result = fighters[0].fight(fighters[1]);
		assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(0), 1);
		assertEquals("Fighter should not be dead", result.isDead(0), false);
		assertEquals("Fighter should be dead", result.isDead(1), true);
		assertEquals("Fighter should not be hit because is dead", result.getNumberAttacks(1), 0);
	}


	@Test
	public void experience() {
		initializeRangers();

		//no touch case:
		// TODO

		//doing no damage case:
		initializeRangers();
		fighters[0].setStrength((short)1);
		fighters[1].setDefense((short)100);
		result = fighters[0].fight(fighters[1]);
		assertEquals("fighter who take no damage should receive only 1 exp", result.getExperienceWon()[0], 1);
		assertTrue("fighter who take damages should receive more than 1 exp", result.getExperienceWon()[1] > 1);

		//doing damage case:
		initializeRangers();
		result = fighters[0].fight(fighters[1]);

		//kill ennemy case:
		initializeRangers();

		int xp = fighters[0].fight(fighters[1]).getExperienceWon()[0]; //First battle where nobody's died
		initializeRangers();
		fighters[0].setStrength((short)100);
		result = fighters[0].fight(fighters[1]); // second battle where assaulted died

		assertTrue("fighter should be dead", fighters[1].isDead());
		assertTrue("fighter who kill ennemy should be receive more experience", result.getExperienceWon()[0] > xp);
		assertEquals("fighter who's died should be receive no experience", result.getExperienceWon()[1], 0);

		//kill  boss case:
		initializeRangers();
		fighters[0].setStrength((short)100);
		xp = fighters[0].fight(fighters[1]).getExperienceWon()[0];

		initializeRangers();
		fighters[0].setStrength((short)100);
		fighters[1].setGeneral(true);
		result = fighters[0].fight(fighters[1]);

		assertTrue("fighter should be dead", fighters[1].isDead());
		assertTrue("fighter who kill boss should be receive more experience", result.getExperienceWon()[0] > xp);
	}
	
	@Test
	public void level() {
		initializeRangers();
		assertEquals("at initialization, fighter should be level 1", 1, fighters[0].getLevel());
		
		fighters[0].addExperience(100);
		assertEquals("fighter should be level 2", 2, fighters[0].getLevel());
		assertEquals("fighter's xp should be reduce to 0", 0, fighters[0].getExperience());
		
		initializeRangers();
		fighters[0].addExperience(50);
		assertEquals("fighter should be level 1", 1, fighters[0].getLevel());
		assertEquals("fighter's xp should be 50", 50, fighters[0].getExperience());
		fighters[0].addExperience(80);
		assertEquals("fighter should be level 2", 2, fighters[0].getLevel());
		assertEquals("fighter's xp should be 30", 30, fighters[0].getExperience());
		
		initializeRangers();
		fighters[0].addExperience(350);
		assertEquals("fighter should be level 4", 4, fighters[0].getLevel());
		assertEquals("fighter's xp should be 50", 50, fighters[0].getExperience());
	}

	private void initializeRangers() {
		fighters = new Fighter[2];
		Human h;
		Terrain terrain = new Grass();
		Sword sword = new Sword();
		sword.setMight((short)10);
		sword.setHitRate((short)150);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			h = (Human)fighters[i];
			h.setEquiped(sword);
		}
	}
}
