package jonathan.geoffroy.androidstrategic.model.items.bags;

import jonathan.geoffroy.androidstrategic.model.items.Item;


public class EnnemyBag extends FighterBag {
	private Item onKillWeapon;

	public Item getOnKillWeapon() {
		return onKillWeapon;
	}

	public void setOnKillWeapon(Item onKillWeapon) {
		this.onKillWeapon = onKillWeapon;
	}

}