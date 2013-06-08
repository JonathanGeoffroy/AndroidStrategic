package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Cat;
import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Hawk;
import jonathan.geoffroy.androidstrategic.model.fighters.Heron;
import jonathan.geoffroy.androidstrategic.model.fighters.Lion;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Spireman;
import jonathan.geoffroy.androidstrategic.model.fighters.Swordman;
import jonathan.geoffroy.androidstrategic.model.fighters.Thief;

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
	}


	@Test
	public void fighterInitialization() {
		Fighter[] fighters = {
				new Axman(),
				new Cat(),
				new Hawk(),
				new Heron(),
				new Lion(),
				new Mage(),
				new Priest(),
				new Spireman(),
				new Swordman(),
				new Thief()
		};
		for(Fighter f: fighters) {
			assertEquals("at initialization, hp should be equals hpMax", f.getHp(), f.getHpMax());
			assertEquals("at initialization, movement should be equals movementMax", f.getMovement(), f.getMovementMax());
			assertFalse("at initialization, fighter should not be dead", f.isDead());
		}
	}
	@Test
	public void fight() {
		fighter1 = new Swordman();
		fighter2 = new Swordman();
		fighter1.fight(fighter2);
		assertEquals("both fighters should be hit at the same strength", fighter1.getHp(), fighter2.getHp());
	}

}
