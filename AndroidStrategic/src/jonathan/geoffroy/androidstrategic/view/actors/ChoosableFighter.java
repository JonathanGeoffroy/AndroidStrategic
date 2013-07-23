package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ChoosableFighter extends Actor {
	private Fighter fighter;
	
	public ChoosableFighter(Fighter fighter) {
		super();
		this.fighter = fighter;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		App app = HelpScreen.getApp();
		Texture fighterText = (Texture) app.getAsset(App.FIGHTERS_DIR + fighter.getTextureName());
		BitmapFont font = (BitmapFont) app.getAsset(FighterInfoActor.FONT);
		batch.draw(fighterText, getX(), getY(), getWidth() / 3, getHeight());
		font.draw(batch, fighter.getName(), getX() + getWidth() / 3 , getY());
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
}