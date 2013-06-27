package jonathan.geoffroy.androidstrategic.model.items;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;


public class CureItem extends FighterItem {

	private int cure;

	public int getCure() {
		return cure;
	}

	public void setCure(int cure) {
		this.cure = cure;
	}

	@Override
	public void use(Fighter fighter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getType() {
		return 0;
	}

}