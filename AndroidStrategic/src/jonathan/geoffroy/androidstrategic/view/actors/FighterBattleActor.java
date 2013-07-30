package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.utils.TableActor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FighterBattleActor extends TableActor {
	private Fighter fighter;
	
	public FighterBattleActor(Fighter fighter, boolean left) {
		super();
		this.fighter = fighter;
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}

	@Override
	public void loadTable() {
		
	}
}
