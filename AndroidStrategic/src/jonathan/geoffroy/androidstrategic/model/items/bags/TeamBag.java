package jonathan.geoffroy.androidstrategic.model.items.bags;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.items.Item;

public class TeamBag implements Bag {
	public static final int CURE = 0, TERRAIN = 1, LAGUZ = 2, AX = 3, BOOK = 4, KNIFE = 5, SCEPTER = 6, SPIRE = 7, SWORD = 8, BOW = 9;
	public static final int NB_TYPES = 10;
	private ArrayList<ArrayList<Item>> items;
	
	public TeamBag() {
		items = new ArrayList<ArrayList<Item>>(9);
		for(int i = 0; i < NB_TYPES; i++) {
			items.add(new ArrayList<Item>());
		}
	}
	
	/**
	 * automatically add an item at that's place
	 * @param item the item to add
	 */
	public void add(Item item) {
		items.get(item.getType()).add(item);
	}
	
	/**
	 * get type's items 
	 * @param type the types of items returned
	 * @return an ArrayList of type's items. ArrayList is never null, but can be empty (returned.isEmpty() == true)
	 */
	public ArrayList<Item> getItems(int type) {
		return items.get(type);
	}

	public int size() {
		int size = 0;
		for(ArrayList<Item> list : items) {
			size += list.size();
		}
		return size;
	}
}
