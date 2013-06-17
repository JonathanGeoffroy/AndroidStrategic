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
	Fighter fighter1, fighter2;

	@Before
	public void preConditions() {
		if(fighter1 != null) {
			conditions(fighter1);
		}
		if (fighter2 != null) {
			conditions(fighter2);
		}
	}

	@After
	public void postConditions() {
		if(fighter1 != null) {
			conditions(fighter1);
		}
		if (fighter2 != null) {
			conditions(fighter2);
		}
	}


	public void conditions(Fighter f) {
		assertTrue("hp should be least or equals than hpMax", f.getHp() <= f.getHpMax());
		assertTrue("if hp <= 0,\n fighter should be dead", f.getHp() > 0 || f.isDead());
		assertTrue("hp shouldn't be negative", f.getHp() >= 0);
	}


	@Test
	public void fighterInitialization() {
		Fighter[] fighters = {
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
		for(Fighter f: fighters) {
			assertTrue(f.getName() + ": at initialization, hp should be > 0", f.getHp() > 0);
			assertEquals(f.getName() + ": at initialization, hp should be equals hpMax", f.getHp(), f.getHpMax());
			assertEquals(f.getName() + ": at initialization, movement should be equals movementMax", f.getMovement(), f.getMovementMax());
			assertFalse(f.getName() + ": at initialization, fighter should not be dead", f.isDead());
		}
	}

	@Test
	public void hitNumber() {
		Archer archer = new Archer();
		Archer ennemy = new Archer();
		assertEquals("should hit only 1 time", archer.hitNumber(ennemy), 1);
		archer.setSpeed((short) (archer.getSpeed() + 3));
		assertEquals("should hit twice", archer.hitNumber(ennemy), 2);
		archer.setSpeed((short) (archer.getSpeed() + 3));
		assertEquals("should hit twice", archer.hitNumber(ennemy), 2);
	}

	@Test
	public void accuracy() {
		Archer archer = new Archer();
		Archer ennemy = new Archer();
		archer.setTerrain(new Road());
		ennemy.setTerrain(new Desert());
		Bow bow = new Bow();
		bow.setHitRate((short)50);

		int accuracyWithNoWeapon = archer.accuracy(ennemy);
		archer.setEquiped(bow);

		assertTrue("accuracy should be between 0 & 100", archer.accuracy(ennemy) >= 0 && archer.accuracy(ennemy) <= 100);
		assertTrue("accuracy should be between 0 & 100", ennemy.accuracy(archer) >= 0 && ennemy.accuracy(archer) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", archer.accuracy(ennemy) == accuracyWithNoWeapon + bow.getHitRate() || archer.accuracy(ennemy) == 100);

		bow.setHitRate((short)90);
		assertTrue("accuracy should be between 0 & 100", archer.accuracy(ennemy) >= 0 && archer.accuracy(ennemy) <= 100);
		assertTrue("accuracy should be between 0 & 100", ennemy.accuracy(archer) >= 0 && ennemy.accuracy(archer) <= 100);
		assertTrue("fighter acccuracy should include equiped weapon", archer.accuracy(ennemy) == accuracyWithNoWeapon + bow.getHitRate() || archer.accuracy(ennemy) == 100);
	}

	@Test
	public void touchedRate() {
		Fighter fighters[] = new Fighter[2];
		Terrain terrain = new Grass();
		terrain.setAvoid((short)0);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
		}

		FightResult result;

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
		Human fighters[] = new Human[2];
		Sword sword = new Sword();
		sword.setHitRate((short) 80);
		Terrain terrain = new Grass();
		terrain.setAvoid((short)0);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			fighters[i].setEquiped(sword);
		}

		FightResult result;

		int nbCritical[] = new int[2];
		for(int i = 0; i < 1000000; i++) {
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
				nbCritical[0] >= nbCritical[1] - 200 &&
				nbCritical[0] <= nbCritical[1] + 200
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
		Human fighters[] = new Human[2];
		Sword sword = new Sword();
		sword.setHitRate((short) 80);
		Terrain terrain = new Grass();
		terrain.setAvoid((short)0);
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			fighters[i].setEquiped(sword);
		}		

		FightResult result = fighters[0].fight(fighters[1]);

		//Equals strength & speed case
		for(int i = 0; i < 2; i++) {
			assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(i), 1);
			assertTrue("Fighter missed attack should hit 0 pv", result.getTouched()[i][0] || result.getInflictedDamages()[i][0] == 0);
			assertTrue("Fighter critical attack should hit 3 * strength", 
					!result.getTouched()[i][0] || 
					result.getCriticalDamages()[i][0] && result.getInflictedDamages()[i][0] == 3 * (fighters[i].calculatePower() - fighters[(i+1)%2].calculateDefense(true)) ||
					!result.getCriticalDamages()[i][0] && result.getInflictedDamages()[i][0] == fighters[i].calculatePower() - fighters[(i+1)%2].calculateDefense(true)
					);
			assertEquals("Fighter should not be dead", result.isDead(i), false);
		}

		//twice hitting case
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			fighters[i].setEquiped(sword);
		}
		fighters[0].setSpeed((short) (fighters[0].getSpeed() + 3));
		result = fighters[0].fight(fighters[1]);
		assertEquals("Fighter should be hit twice", result.getNumberAttacks(0), 2);
		assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(1), 1);

		//Dead case
		for(int i = 0; i < 2; i++) {
			fighters[i] = new Ranger();
			fighters[i].setTerrain(terrain);
			fighters[i].setEquiped(sword);
		}
		fighters[0].setStrength((short)100);
		result = fighters[0].fight(fighters[1]);
		assertEquals("Fighter should be hit only 1 time", result.getNumberAttacks(0), 1);
		assertEquals("Fighter should not be dead", result.isDead(0), false);
		assertEquals("Fighter should be dead", result.isDead(1), true);
		assertEquals("Fighter should not be hit because is dead", result.getNumberAttacks(1), 0);
	}
}
