package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FighterInfoActor extends Actor {
	public static final String FONT = App.FONTS_DIR + "fighterInfo.fnt";
	public static final String WALLPAPER = App.TEXTURES_DIR + "fighterInfo_wallpaper.png";
	private MapScreen mapScreen;

	public FighterInfoActor(MapScreen mapscreen) {
		this.mapScreen = mapscreen;
	}

	/**
	 * Draw a default state when there no selected Fighter
	 * 
	 * @param batch
	 * @param parentAlpha
	 */
	private void drawDefault(SpriteBatch batch, float parentAlpha) {
		BitmapFont font = (BitmapFont) HelpScreen.getApp().getAsset(FONT);
		font.setScale(1);
		font.setColor(Color.WHITE);
		font.drawMultiLine(batch, "Informations\nnon\ndisponible", 
				getX(),getY(), getWidth(), HAlignment.CENTER);
	}

	/**
	 * Draw the selected fighter status
	 * 
	 * @param fighter
	 * @param batch
	 * @param parentAlpha
	 */
	private void drawFighter(Fighter fighter, SpriteBatch batch,
			float parentAlpha) {
		Texture fighterText = (Texture) HelpScreen.getApp().getAsset(
				App.DIALOGS_DIR + fighter.getTextureName());
		batch.draw(fighterText, 
				getX(), getY() + getHeight() * 2.f / 3.f,
				getWidth(), getHeight() / 3.f);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Fighter fighter = mapScreen.getSelectedFighter();
		Texture wallpaper = (Texture) (HelpScreen.getApp().getAsset(WALLPAPER));
		batch.draw(wallpaper, getX(), getY(), getWidth(), getHeight());

		if (fighter == null) {
			drawDefault(batch, parentAlpha);
		} else {
			drawFighter(fighter, batch, parentAlpha);
		}
	}
}
