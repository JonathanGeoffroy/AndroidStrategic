package jonathan.geoffroy.androidstrategic.model.dialogs;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Dialog {
	public static String DIAL_TEXT = App.DIALOGS_DIR + "dialogs.png";
	private String title;
	private ArrayList<Speak> speaks;

	public static Dialog load(String scenarioName, int chapterNum) {
		Dialog dialog = null;

		Json json = new Json();
		String dialText = Gdx.files.internal(Map.SCENARII_DIR + scenarioName + "/" + chapterNum + ".json").readString();
		dialog = json.fromJson(Dialog.class, dialText);
		return dialog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<Speak> getSpeaks() {
		return speaks;
	}
}
