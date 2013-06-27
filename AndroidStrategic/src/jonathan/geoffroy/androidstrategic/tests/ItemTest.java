package jonathan.geoffroy.androidstrategic.tests;

import static org.junit.Assert.*;
import jonathan.geoffroy.androidstrategic.model.items.BlockItem;
import jonathan.geoffroy.androidstrategic.model.items.CureItem;
import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.items.TransformItem;
import jonathan.geoffroy.androidstrategic.model.items.bags.Bag;
import jonathan.geoffroy.androidstrategic.model.items.bags.FighterBag;
import jonathan.geoffroy.androidstrategic.model.items.bags.TeamBag;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Ax;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Bow;
import jonathan.geoffroy.androidstrategic.model.items.weapons.FireBook;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Knife;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Scepter;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Spire;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Sword;

import org.junit.Test;

public class ItemTest {
	private Bag bag;

	@Test
	public void teamBag() {
		int nb = 0;
		bag = new TeamBag();
		TeamBag teamBag = (TeamBag) bag;
		assertEquals("at initialization, bag should be empty", 0, teamBag.size());

		Item addedItems[] = { new CureItem(), new BlockItem(),
				new TransformItem(), new Ax(), new FireBook(), new Knife(),
				new Scepter(), new Spire(), new Sword(), new Bow() };
		
		//first adding
		for(int i = 0; i < addedItems.length; i++) {
			teamBag.add(addedItems[i]);
			nb++;
			int j;
			for(j = 0; j <= i; j++) {
				assertEquals("should have 1 occurence of item", 1, teamBag.getItems(j).size());
			}
			for(; j < addedItems.length; j++) {
				assertEquals("shouldn't have any occurence of item", 0, teamBag.getItems(j).size());
			}
			assertEquals("should have " + nb + " items into bag", nb, teamBag.size());
		}
		addedItems = null;
		
		// second adding
		Item[] addedItems2 = { new CureItem(), new BlockItem(),
				new TransformItem(), new Ax(), new FireBook(), new Knife(),
				new Scepter(), new Spire(), new Sword(), new Bow() };
		
		for(int i = 0; i < addedItems2.length; i++) {
			teamBag.add(addedItems2[i]);
			nb++;
			int j;
			for(j = 0; j <= i; j++) {
				assertEquals("should have 2 occurences of item", 2, teamBag.getItems(j).size());
			}
			for(; j < addedItems2.length; j++) {
				assertEquals("should have only 1 occurence of item", 1, teamBag.getItems(j).size());
			}
			assertEquals("should have " + nb + " items into bag", nb, teamBag.size());
		}
	}
	
	
	@Test
	public void fighterBag() {
		bag = new FighterBag();
		FighterBag fighterBag = (FighterBag)bag;
		boolean added;
		
		for(int i = 0; i < FighterBag.NB_ITEMS_MAX; i++) {
			added = fighterBag.add(new CureItem());
			assertTrue("Item should be added", added);
			assertEquals("bag should contains " + i +1 + " items", i+1, fighterBag.itemSize());
			assertEquals("bag shouln't contains any weapon", 0, fighterBag.weaponsSize());
		}
		
		added = fighterBag.add(new CureItem());
		assertFalse("The bag is full, item shouln't be added", added);
		assertEquals("bag should contains MAX items", FighterBag.NB_ITEMS_MAX, fighterBag.itemSize());
		assertEquals("bag shouln't contains any weapon", 0, fighterBag.weaponsSize());
		
		
		for(int i = 0; i < FighterBag.NB_WEAPONS_MAX; i++) {
			added = fighterBag.add(new Sword());
			assertTrue("Item should be added", added);
			assertEquals("bag shoul contains " + (i + 1) + " weapons", i + 1, fighterBag.weaponsSize());
			assertEquals("bag should contains MAX items", FighterBag.NB_ITEMS_MAX, fighterBag.itemSize());
		}
		
		added = fighterBag.add(new Sword());
		assertFalse("The bag is full, item shouln't be added", added);
		assertEquals("bag should contains MAX items", FighterBag.NB_ITEMS_MAX, fighterBag.itemSize());
		assertEquals("bag should contains MAX weapons", FighterBag.NB_WEAPONS_MAX, fighterBag.weaponsSize());
		
		
		//add weapons with no items.
		bag = new FighterBag();
		fighterBag = (FighterBag)bag;

		for(int i = 0; i < FighterBag.NB_WEAPONS_MAX; i++) {
			added = fighterBag.add(new Sword());
			assertTrue("Item should be added", added);
			assertEquals("bag shoul contains " + (i + 1) + " weapons", i + 1, fighterBag.weaponsSize());
			assertEquals("bag shouldn't contains any item", 0, fighterBag.itemSize());
		}
		
		added = fighterBag.add(new Sword());
		assertFalse("The bag is full, item shouln't be added", added);
		assertEquals("bag shouldn't contains MAX any item", 0, fighterBag.itemSize());
		assertEquals("bag shoul contains MAX weapons", FighterBag.NB_WEAPONS_MAX, fighterBag.weaponsSize());
		
		for(int i = 0; i < FighterBag.NB_ITEMS_MAX; i++) {
			added = fighterBag.add(new CureItem());
			assertTrue("Item should be added", added);
			assertEquals("bag should contains " + i + 1 + " items", i + 1, fighterBag.itemSize());
			assertEquals("bag should contains MAX weapons", FighterBag.NB_WEAPONS_MAX, fighterBag.weaponsSize());
		}
		
		added = fighterBag.add(new CureItem());
		assertFalse("The bag is full, item shouln't be added", added);
		assertEquals("bag should contains MAX items", FighterBag.NB_ITEMS_MAX, fighterBag.itemSize());
		assertEquals("bag should contains MAX weapons", FighterBag.NB_WEAPONS_MAX, fighterBag.weaponsSize());
	}
}
