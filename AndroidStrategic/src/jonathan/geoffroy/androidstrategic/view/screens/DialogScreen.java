package jonathan.geoffroy.androidstrategic.view.screens;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import jonathan.geoffroy.androidstrategic.model.dialogs.Dialog;
import jonathan.geoffroy.androidstrategic.model.dialogs.Speak;
import jonathan.geoffroy.androidstrategic.model.dialogs.Speaker;
import jonathan.geoffroy.androidstrategic.view.utils.StageScreen;

public class DialogScreen extends StageScreen {
	private Dialog dialog;

	@Override
	public void show() {
		assert(app.getScenario() != null);
		assert(app.getChapter() != 0);
		dialog = Dialog.load(app.getScenario(), app.getChapter());
		super.show();
	}

	@Override
	public ArrayList<AssetDescriptor<Object>> getAssetDescriptors() {
		ArrayList<AssetDescriptor<Object>> result = new ArrayList<AssetDescriptor<Object>>();
		result.add(new AssetDescriptor(Dialog.DIAL_TEXT, Texture.class));

		ArrayList<Speak> speaks = dialog.getSpeaks();
		for(Speak speak: speaks) {
			for(Speaker s: speak.getSpeakers()) {
				if(!result.contains(s)) {
					result.add(new AssetDescriptor(s.getTextureName(), Texture.class));
				}
			}
		}
		return result;
	}

	@Override
	protected void onEndLoaded() {
		// TODO Auto-generated method stub

	}

}
