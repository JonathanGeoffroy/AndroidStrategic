package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ChoosableFighter extends Actor {
	private Fighter fighter;
	private boolean choosed;
	
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
		Color fontColor = font.getColor();
		
		if(choosed) {
			font.setColor(Color.RED);
		}
		batch.draw(fighterText, getX(), getY(), getWidth() / 3, getHeight());
		font.draw(batch, fighter.getName(), getX() + getWidth() / 3 , getY());
		if(choosed) {
			font.setColor(fontColor);
		}
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
}