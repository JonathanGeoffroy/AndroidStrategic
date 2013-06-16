package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;
import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Cat;
import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Hawk;
import jonathan.geoffroy.androidstrategic.model.fighters.Heron;
import jonathan.geoffroy.androidstrategic.model.fighters.Lion;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Soldier;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
import jonathan.geoffroy.androidstrategic.model.fighters.Thief;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.mapping.Desert;
import jonathan.geoffroy.androidstrategic.model.mapping.Road;

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
	public void fight() {
		fighter1 = new Ranger();
		fighter2 = new Ranger();
		fighter1.fight(fighter2);
		assertEquals("both fighters should be hit at the same strength", fighter1.getHp(), fighter2.getHp());
	}

}
