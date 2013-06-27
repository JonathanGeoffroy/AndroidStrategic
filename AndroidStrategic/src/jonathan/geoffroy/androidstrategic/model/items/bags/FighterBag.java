package jonathan.geoffroy.androidstrategic.model.items.bags;

import java.util.LinkedList;

import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;


public class FighterBag implements Bag {
	public static final int NB_ITEMS_MAX = 4, NB_WEAPONS_MAX = 4;
	private LinkedList<Item> items;
	private LinkedList<Weapon> weapons;

	public FighterBag() {
		items = new LinkedList<Item>();
		weapons = new LinkedList<Weapon>();
	}

	/**
	 * Add an item at the end of queue, if there is at least 1 empty case.
	 * @param item the item to add into bag
	 * @return TRUE if the bag was not full.
	 */
	public boolean add(Item item) {
		if(items.size() < NB_ITEMS_MAX) {
			items.add(item);
			return true;
		}
		return false;
	}

	/**
	 * Add an item at rank, if there is at least 1 empty case.
	 * @param item the item to add into bag
	 * @param rank the rank of the item to add
	 * @return TRUE if the bag was not full.
	 */
	public boolean add(Item item, int rank) {
		if(items.size() < NB_ITEMS_MAX) {
			items.add(rank, item);
			return true;
		}
		return false;
	}
	
	public Item getItem(int rank) {
		return items.get(rank);
	}

	/**
	 * Add a weapon at the end of queue, if there is at least 1 empty case.
	 * @param weapon the weapon to add into bag
	 * @return TRUE if the bag was not full.
	 */
	public boolean add(Weapon weapon) {
		if(weapons.size() < NB_ITEMS_MAX) {
			weapons.add(weapon);
			return true;
		}
		return false;
	}

	/**
	 * Add a weapon at rank, if there is at least 1 empty case.
	 * @param weapon the weapon to add into bag
	 * @param rank the rank of the weapon to add
	 * @return TRUE if the bag was not full.
	 */
	public boolean add(Weapon weapon, int rank) {
		if(weapons.size() < NB_ITEMS_MAX) {
			weapons.add(rank, weapon);
			return true;
		}
		return false;
	}
	
	public Item getWeapon(int rank) {
		return weapons.get(rank);
	}

	public int itemSize() {
		return items.size();
	}
	
	public int weaponsSize() {
		return weapons.size();
	}
}