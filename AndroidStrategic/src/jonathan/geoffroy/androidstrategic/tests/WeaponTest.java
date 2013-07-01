package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import jonathan.geoffroy.androidstrategic.model.fighters.Archer;
import jonathan.geoffroy.androidstrategic.model.fighters.Axman;
import jonathan.geoffroy.androidstrategic.model.fighters.Human;
import jonathan.geoffroy.androidstrategic.model.fighters.Mage;
import jonathan.geoffroy.androidstrategic.model.fighters.Priest;
import jonathan.geoffroy.androidstrategic.model.fighters.Soldier;
import jonathan.geoffroy.androidstrategic.model.fighters.Ranger;
import jonathan.geoffroy.androidstrategic.model.fighters.Thief;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Ax;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.items.weapons.FireBook;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Knife;
import jonathan.geoffroy.androidstrategic.model.items.weapons.LightBook;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Scepter;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Spear;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Sword;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;
import jonathan.geoffroy.androidstrategic.model.items.weapons.WindBook;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;

import org.junit.Test;

public class WeaponTest {

	@Test
	public void equipableTypeTest() {
		Human[] fighters = {
				new Archer(),
				new Axman(),
				new Priest(),
				new Soldier(),
				new Ranger(),
				new Thief()
		};

		Weapon[] weapons = {
				new Bow(),
				new Ax(),
				new Scepter(),
				new Spear(),
				new Sword(),
				new Knife()
		};

		for(int i = 0; i < fighters.length; i++) {
			for(int j = 0; j < weapons.length; j++) {
				if(i == j) {
					assertTrue(fighters[i].getName() + " should can equip " + weapons[j].getName(), weapons[j].canEquip(fighters[i]));
				}
				else {
					assertFalse(fighters[i].getName() + " should not can equip " + weapons[j].getName(), weapons[j].canEquip(fighters[i]));
				}
			}
		}
	}

	@Test
	public void equipableLvlTest() {
		Human[] fighters = {
				new Archer(),
				new Axman(),
				new Priest(),
				new Soldier(),
				new Ranger(),
				new Thief()
		};
		fighters[0].setWeaponClass(Weapon.BOW, (short) 2);
		fighters[1].setWeaponClass(Weapon.AX, (short) 2);
		fighters[2].setWeaponClass(Weapon.SCEPTER, (short) 2);
		fighters[3].setWeaponClass(Weapon.SPEAR, (short) 2);
		fighters[4].setWeaponClass(Weapon.SWORD, (short) 2);
		fighters[5].setWeaponClass(Weapon.KNIFE, (short) 2);

		Weapon[] weapons = {
				new Bow(),
				new Ax(),
				new Scepter(),
				new Spear(),
				new Sword(),
				new Knife()
		};

		for(int i = 0; i < fighters.length; i++) {
			assertTrue(fighters[i].getName() + "should can equip " + weapons[i].getName(), weapons[i].canEquip(fighters[i]));
			weapons[i].setWeaponClass((short)2);
			assertTrue(fighters[i].getName() + "should can equip " + weapons[i].getName(), weapons[i].canEquip(fighters[i]));
			weapons[i].setWeaponClass((short)3);
			assertFalse(fighters[i].getName() + "shouldn't can equip " + weapons[i].getName(), weapons[i].canEquip(fighters[i]));
		}
	}

	@Test
	public void PhysicalEffectiveness() {
		Human[] humans = {new Ranger(), new Axman(), new Soldier()};
		Weapon[] weapons = {new Sword(), new Ax(), new Spear()};

		//none equiped effectiveness
		for(int i = 0; i < humans.length - 1; i++) {
			humans[i].setEquiped(weapons[i]);
			assertFalse("with an ennemy with no equipement, shouldn't be effectiveness", humans[i].getEquiped().isEffectiveness(humans[i + 1]));
		}
		humans[2].setEquiped(weapons[2]);

		for(int i = 0; i < humans.length; i++) {
			for(int j = 0; j < humans.length; j++) {
				if(i == j) continue;
				if(j == (i+1) % 3) {
					assertTrue("should be effectiveness", humans[i].getEquiped().isEffectiveness(humans[j]));
				}
				else {
					assertFalse("shouldn't be effectiveness", humans[i].getEquiped().isEffectiveness(humans[j]));
				}
			}
		}
	}
	
	@Test
	public void magicalEffectiveness() {
		Human[] humans = {new Mage(), new Mage(), new Mage()};
		Weapon[] weapons = {new FireBook(), new WindBook(), new LightBook()};

		//none equiped effectiveness
		for(int i = 0; i < humans.length - 1; i++) {
			humans[i].setEquiped(weapons[i]);
			assertFalse("with an ennemy with no equipement, shouldn't be effectiveness", humans[i].getEquiped().isEffectiveness(humans[i + 1]));
		}
		humans[2].setEquiped(weapons[2]);

		for(int i = 0; i < humans.length; i++) {
			for(int j = 0; j < humans.length; j++) {
				if(i == j) continue;
				if(j == (i+1) % 3) {
					assertTrue("should be effectiveness", humans[i].getEquiped().isEffectiveness(humans[j]));
				}
				else {
					assertFalse("shouldn't be effectiveness", humans[i].getEquiped().isEffectiveness(humans[j]));
				}
			}
		}
	}
	
	@Test
	public void weaponExp() {
		Map map = null;
		try {
			map = Map.load("Test", 2);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		Human[] humans = new Human[2];
		humans[0] = new Mage();
		humans[0].setEquiped(new LightBook());
		map.addFighter(humans[0], 0, 0);
		humans[1] = new Ranger();
		humans[1].setEquiped(new Sword());
		map.addFighter(humans[1], 0, 1);
		
		for(int i = 0; i < Human.WEAPON_EXP_LVL; i++) {
			for(int j = 0; j < humans.length; j++) {
				humans[j].setHp(humans[j].getHpMax());
			}
			assertEquals("mage should be level 1 for LightBook", 1, humans[0].getWeaponClass(Weapon.LIGHTBOOK));
			assertEquals("mage should have " + i + " exp with light book", i, humans[0].getWeaponExp(Weapon.LIGHTBOOK));
			assertEquals("ranger should be level 1 for sword", 1, humans[1].getWeaponClass(Weapon.SWORD));
			assertEquals("ranger should have " + i + "exp with sword", i, humans[1].getWeaponExp(Weapon.SWORD));
			humans[0].fight(humans[1]);
		}
		assertEquals("mage should have level up for lightBook", 2, humans[0].getWeaponClass(Weapon.LIGHTBOOK));
		assertEquals("mage should have 0 exp with light book", 0, humans[0].getWeaponExp(Weapon.LIGHTBOOK));
		assertEquals("ranger should be level 1 for sword", 2, humans[1].getWeaponClass(Weapon.SWORD));
		assertEquals("ranger should have 0 exp with sword", 0, humans[1].getWeaponExp(Weapon.SWORD));
	}
}
