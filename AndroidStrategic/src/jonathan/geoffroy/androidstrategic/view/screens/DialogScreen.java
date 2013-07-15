package jonathan.geoffroy.androidstrategic.view.screens;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetDescriptor;

import jonathan.geoffroy.androidstrategic.model.dialogs.Dialog;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onEndLoaded() {
		// TODO Auto-generated method stub

	}

}
