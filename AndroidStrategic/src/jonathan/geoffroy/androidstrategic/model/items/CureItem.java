package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;


public class CureItem extends FighterItem {

	private int cure;

	public CureItem() {
		use = useMax = 3;
		cure = 10;
	}
	
	public CureItem(int cure) {
		this.cure = cure;
	}

	public CureItem(String name, int cure, short nbUseMax) {
		this.name = name;
		this.cure = cure;
		this.use = this.useMax = nbUseMax;
	}

	public int getCure() {
		return cure;
	}

	public void setCure(int cure) {
		this.cure = cure;
	}

	@Override
	public void use(Fighter fighter) {
		fighter.addHp(cure);
		isUsed();
	}

	@Override
	public int getType() {
		return 0;
	}

}