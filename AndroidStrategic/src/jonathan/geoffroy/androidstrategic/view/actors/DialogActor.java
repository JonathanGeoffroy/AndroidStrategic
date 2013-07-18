package jonathan.geoffroy.androidstrategic.view.actors;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.dialogs.Dialog;
import jonathan.geoffroy.androidstrategic.model.dialogs.Speaker;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class DialogActor extends Actor {
	private Dialog dialog;
	private ArrayList<Sprite> speakers;
	private Texture wallpaper, speakText, nameText;
	private BitmapFont font;

	public DialogActor(Dialog dialog) {
		super();
		this.dialog = dialog;
		wallpaper = (Texture) HelpScreen.getApp().getAsset(dialog.getPathWallpaper());
		speakText = (Texture) HelpScreen.getApp().getAsset(Dialog.DIAL_TEXT);
		nameText = (Texture) HelpScreen.getApp().getAsset(Dialog.NAMES_TEXT);
		font = (BitmapFont) HelpScreen.getApp().getAsset(FighterInfoActor.FONT);
		speakers = new ArrayList<Sprite>();

		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				if(!DialogActor.this.dialog.next()) {
					HelpScreen.getApp().setScreen(App.MAP);
				}
				return true;
			}
		});
	}

	/**
	 * Load the speakers' Sprite using the current Speak of the Dialog
	 */
	public void loadSpeakers() {
		float widthSpeaker = getWidth() / 5;
		float heightSpeaker = getHeight() / 3;
		float ySpeaker = (getHeight() / 3);

		Sprite sprite;
		for(Speaker s : dialog.getCurrentSpeakers()) {
			sprite = new Sprite((Texture) HelpScreen.getApp().getAsset(s.getTextureName()));
			sprite.setBounds(s.getPlaceNumber() * widthSpeaker, ySpeaker, widthSpeaker, heightSpeaker);
			speakers.add(sprite);
		}
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		loadSpeakers();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(wallpaper, getX(), getY(), getWidth(), getHeight());
		batch.draw(speakText, getX(), getY(), getWidth(), getHeight() / 3);
		font.drawWrapped(batch, dialog.getCurrentText(), getX(), getY() + getHeight() / 3, getWidth());

		for(Sprite s : speakers) {
			s.draw(batch);
		}
	}
}
