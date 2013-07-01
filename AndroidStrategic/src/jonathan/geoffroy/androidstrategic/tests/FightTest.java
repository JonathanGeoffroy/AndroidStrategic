package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Cat;
import jonathan.geoffroy.androidstrategic.model.fighters.FightResult;
import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Hawk;
import jonathan.geoffroy.androidstrategic.model.fighters.Heron;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.fighters.Laguz;
import jonathan.geoffroy.androidstrategic.model.fighters.Lion;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
import jonathan.geoffroy.androidstrategic.model.fighters.Soldier;
import jonathan.geoffroy.androidstrategic.model.fighters.Thief;
import jonathan.geoffroy.androidstrategic.model.fighters.TransformNotPossibleException;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Spear;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Sword;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FightTest {
	private Fighter fighters[];
	private FightResult result;
	private Map map;
	private final static int NB_RATIO_TESTS = 100000;

	@Before
	public void preConditions() {
		try {
			map = Map.load("Test", 2);
			Fighter.setMap(map);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
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
		initializeRangers();
		assertEquals("should hit only 1 time", fighters[0].hitNumber(fighters[1]), 1);
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		assertEquals("should hit twice", fighters[0].hitNumber(fighters[1]), 2);
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		assertEquals("should hit twice", fighters[0].hitNumber(fighters[1]), 2);

		initializeRangers();
		Human h = (Human)fighters[0];
		Sword sword = new Sword();
		sword.setMaxRange((short)2);
		h.setEquiped(sword);
		map.moveFighter(fighters[0], 2, 2);
		map.moveFighter(fighters[1], 2, 0);
		fighters[0].fight(fighters[1]);
		assertEquals("should hit only 1 time", 1, fighters[0].hitNumber(fighters[1]));
		assertEquals("shouldn't can hit", 0, fighters[1].hitNumber(fighters[0]));
	}

	@Test
	public void accuracy() {
		initializeRangers();
		Human h = (Human)fighters[0];
		fighters[0].setSkill((short) 10);
		h.setEquiped(null);
		int accuracyWithNoWeapon = fighters[0].accuracy(fighters[1]);
		Sword sword = new Sword();
		sword.setHitRate((short)10);
		h.setEquiped(sword);

		assertTrue("accuracy should be between 0 & 100", fighters[0].accuracy(fighters[1]) >= 0 && fighters[0].accuracy(fighters[1]) <= 100);
		assertTrue("accuracy should be between 0 & 100", fighters[1].accuracy(fighters[0]) >= 0 && fighters[1].accuracy(fighters[0]) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", fighters[0].accuracy(fighters[1]) == accuracyWithNoWeapon + sword.getHitRate() || fighters[0].accuracy(fighters[1]) == 100);

		sword.setHitRate((short)90);
		assertTrue("accuracy should be between 0 & 100", fighters[0].accuracy(fighters[1]) >= 0 && fighters[0].accuracy(fighters[1]) <= 100);
		assertTrue("accuracy should be between 0 & 100", fighters[1].accuracy(fighters[0]) >= 0 && fighters[1].accuracy(fighters[0]) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", fighters[0].accuracy(fighters[1]) == accuracyWithNoWeapon + sword.getHitRate() || fighters[0].accuracy(fighters[1]) == 100);
	}

	@Test
	public void touchedRate() {
		initializeRangers();
		Human h;
		int nbTouched[] = new int[2];
		for(int i = 0; i < NB_RATIO_TESTS; i++) {
			initializeRangers();
			for(int j = 0; j < 2; j++) {
				h = (Human)(fighters[j]);
				h.getEquiped().setHitRate((short)20);
				h.getEquiped().setCriticalRate((short)0);
			}
			result = fighters[0].fight(fighters[1]);
			for(int j = 0; j < 2; j++) {
				if(result.getTouched()[j][0]) {
					nbTouched[j]++;
				}
			}
		}
		assertTrue("Both fighters should hit at the same frequence",
				nbTouched[0] >= nbTouched[1] - 500 &&
				nbTouched[0] <= nbTouched[1] + 500
				);
		for(int i = 0; i < 2; i++) {
			assertTrue("fighter critical frequence should follow his touched rates",
					nbTouched[i] >= fighters[i].accuracy(fighters[(i+1) % 2]) * (NB_RATIO_TESTS / 100) - 2000 &&
					nbTouched[i] <= fighters[i].accuracy(fighters[(i+1) % 2]) * (NB_RATIO_TESTS / 100) + 2000
					);
		}
	}

	@Test 
	public void criticalRate() {
		initializeRangers();
		Human h;
		int nbCritical[] = new int[2];
		for(int i = 0; i < NB_RATIO_TESTS; i++) {
			initializeRangers();
			for(int j = 0; j < 2; j++) {
				h = (Human)(fighters[j]);
				h.getEquiped().setHitRate((short)100);
				h.getEquiped().setCriticalRate((short)10);
			}
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
			assertTrue("fighter critical frequence should follow his touched rates",
					nbCritical[i] >= fighters[i].criticalRate() * (NB_RATIO_TESTS / 100) - 2000 &&
					nbCritical[i] <= fighters[i].criticalRate() * (NB_RATIO_TESTS / 100) + 2000
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
		//no touch case:
		fighters = new Fighter[2];
		Archer archer = new Archer();
		Bow bow = new Bow();
		bow.setMight((short)10);
		bow.setHitRate((short)150);
		archer.setEquiped(bow);
		Soldier soldier = new Soldier();
		soldier.setEquiped(new Spear());
		fighters[0] = archer;
		fighters[1] = soldier;
		map.addFighter(archer, 0, 0);
		map.addFighter(soldier, 2, 0);
		result = archer.fight(soldier);
		assertTrue("archer should win many xp", result.getExperienceWon()[0] > 1);
		assertEquals("soldier should win only 1 xp", 1, result.getExperienceWon()[1]);

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
		short[] results;
		initializeRangers();
		assertEquals("at initialization, fighter should be level 1", 1, fighters[0].getLevel());

		fighters[0].addExperience(100);
		assertEquals("fighter should be level 2", 2, fighters[0].getLevel());
		assertEquals("fighter's xp should be reduce to 0", 0, fighters[0].getExperience());

		initializeRangers();
		results = fighters[0].addExperience(50);
		assertEquals("fighter should be level 1", 1, fighters[0].getLevel());
		assertEquals("fighter's xp should be 50", 50, fighters[0].getExperience());
		for(int i = 0; i < results.length; i++) {
			assertEquals("fighter's attributes shouln't upgrade", 0, results[i]);
		}

		fighters[0].addExperience(80);
		assertEquals("fighter should be level 2", 2, fighters[0].getLevel());
		assertEquals("fighter's xp should be 30", 30, fighters[0].getExperience());

		initializeRangers();
		fighters[0].addExperience(350);
		assertEquals("fighter should be level 4", 4, fighters[0].getLevel());
		assertEquals("fighter's xp should be 50", 50, fighters[0].getExperience());

		//Rate test
		int[] sumResults = new int[10];
		for(int i = 0; i < 5000; i++) {
			initializeRangers();
			results = fighters[0].addExperience(100);
			assertEquals("should level up", 2, fighters[0].getLevel());
			for(int j = 0; j < results.length; j++) {
				sumResults[j] += results[j];
			}
		}

		for(int j = 0; j < results.length; j++) {
			assertTrue("attributes should be upgrade", 
					sumResults[j] > 5000 * (fighters[0].getLevelUpRate()[j] / 100) - 200 &&
					sumResults[j] > 5000 * (fighters[0].getLevelUpRate()[j] / 100) + 200
					);
		}
	}

	private void initializeRangers() {
		map.clearFighters();
		fighters = new Fighter[2];
		Human h;
		Sword sword = new Sword();
		sword.setMight((short)1);
		sword.setHitRate((short)150);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			h = (Human)fighters[i];
			h.setEquiped(sword);
		}
		map.addFighter(fighters[0], 0, 0);
		map.addFighter(fighters[1], 1, 0);

	}


	@Test
	public void laguz() {
		Lion lion;
		initializeLaguz();
		lion = (Lion) fighters[0];

		// initialization
		assertFalse("at initialization, laguz shouldn't be transformed", lion.isTransformed());
		assertEquals("at initialization, laguz should have 0 transform points", 0, lion.getTransform());
		assertFalse("a initialization, laguz should can't transform", lion.canTransform());

		// transform < 30
		ExpectedException exception = ExpectedException.none();
		initializeLaguz();
		lion = (Lion) fighters[0];
		lion.setTransform((short) 25);
		assertFalse("laguz shouldn't be transformed", lion.isTransformed());
		assertFalse("laguz should can't transform", lion.canTransform());

		lion.setTransform((short) 25);
		exception.expect(TransformNotPossibleException.class);
		try {
			lion.transform();
		} catch (TransformNotPossibleException e1) {}

		// transform = 30
		initializeLaguz();
		lion = (Lion) fighters[0];
		lion.setTransform((short) 30);
		assertFalse("laguz shouldn't be transformed", lion.isTransformed());
		assertTrue("laguz should can transform", lion.canTransform());

		// transformation
		initializeLaguz();
		lion = (Lion) fighters[0];
		lion.setTransform((short) 30);
		try {
			lion.transform();
		} catch (TransformNotPossibleException e) {	}
		assertTrue("laguz should be transformed", lion.isTransformed());
		assertFalse("laguz shouldn't can transform", lion.canTransform());

		// untransformed Fight
		initializeLaguz();
		lion = (Lion) fighters[0];
		Lion lion2 = (Lion)fighters[1];
		fighters[0].fight(fighters[1]);
		assertFalse("laguz shouldn't be transformed", lion.isTransformed());
		assertFalse("laguz shouldn't can transform", lion.canTransform());
		assertEquals("laguz transform points should increase after each attack", Laguz.TRANFORM_POINTS_ON_ATTACK, lion2.getTransform());

		// transformed Fight
		initializeLaguz();
		lion = (Lion) fighters[0];
		lion.setTransform((short)30);
		lion2 = (Lion) fighters[1];
		lion2.setTransform((short) 30);
		try {
			lion.transform();
			lion2.transform();
		} catch (TransformNotPossibleException e) {	}
		lion.setTransform(Laguz.TRANFORM_POINTS_ON_ATTACK);

		assertTrue("laguz should be transformed", lion.isTransformed());
		assertFalse("laguz shouldn't can transform", lion.canTransform());
		lion.fight(lion2);
		assertEquals("laguz transform points should decrease after each assault", 0, lion.getTransform());
		assertEquals("laguz transform points should decrease after each attack", 30 - Laguz.TRANFORM_POINTS_ON_ATTACK, lion2.getTransform());
		assertFalse("laguz who have decrease his transfom points to 0 should be unstransformed", lion.isTransformed());
		assertFalse("laguz who have more at least 1 transform points shouldn't unstranformed", lion.isTransformed());

	}

	private void initializeLaguz() {
		try {
			map = Map.load("Test", 2);
			Fighter.setMap(map);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		fighters = new Laguz[2];
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Lion();
		}
		map.addFighter(fighters[0], 0, 0);
		map.addFighter(fighters[1], 1, 0);
	}
}
