package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;
import jonathan.geoffroy.androidstrategic.view.utils.TableActor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class FighterInfoActor extends TableActor {
	public static final String FONT = App.FONTS_DIR + "fighterInfo.fnt";

	private LabelStyle style;
	private MapScreen mapScreen;
	
	public FighterInfoActor(MapScreen mapScreen) {
		super();
		this.mapScreen = mapScreen;
	}

	/**
	 * Should be called when the selected fighter has changed
	 * while there is no fighter selected, method should be called (with fighter = null) 
	 * @param fighter the new selected fighter
	 */


	@Override
	public void loadTable() {
		BitmapFont font = (BitmapFont) HelpScreen.getApp().getAsset(FONT);
		style = new LabelStyle(font, Color.WHITE);
	}

	@Override
	public void reloadTable() {
		assert(table != null);
		assert(style != null);

		table.clear();
		Fighter fighter = mapScreen.getSelectedFighter();

		if(fighter != null) {
			Texture fighterText = (Texture) HelpScreen.getApp().getAsset(
					App.DIALOGS_DIR + fighter.getTextureName());
			table.add(new Image(fighterText)).expandX();
			table.add(new Label(fighter.getName(), style));
			table.row();

			table.add(new Label("LVL: " + fighter.getLevel(), style));
			table.add(new Label("EXP: " + fighter.getExperience(),style));
			table.row();

			table.add(new Label("HP: " + fighter.getHp(), style));
			table.add(new Label("MAX: " + fighter.getHpMax(), style));
			table.row();

			table.add(new Label("STR: " + fighter.getStrength(), style));
			table.add(new Label("MAG: " + fighter.getMagic(), style));
			table.row();

			table.add(new Label("DEF: " + fighter.getDefense(), style));
			table.add(new Label("RES: " + fighter.getResistance(), style));
			table.row();

			table.add(new Label("SPD: " + fighter.getSpeed(), style));
			table.add(new Label("MVT: " + fighter.getMovementMax(), style));
			table.row();

			table.add(new Label("LCK: " + fighter.getLuck(), style));
			table.add(new Label("SKL: " + fighter.getSkill(), style));
			table.row();

			table.add(new Label("WEI: " + fighter.getWeight(), style));
		}
		table.pack();
		table.setPosition(getX() + getWidth() /2 - table.getWidth() / 2, getY() + getHeight() / 2 - table.getHeight() / 2);
	}
}
