package jonathan.geoffroy.androidstrategic.model.dialogs;

import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.utils.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Dialog {
	public static String DIAL_TEXT = App.DIALOGS_DIR + "dialogs.png", NAMES_TEXT = App.DIALOGS_DIR + "names.png", WALLPAPER_DIR = App.DIALOGS_DIR + "wallpapers/";
	private String wallpaper;
	private String title;
	private ArrayList<Speak> speaks;
	private int current;

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

	public String getCurrentText() {
		return speaks.get(current).getText();
	}

	public ArrayList<Speaker> getCurrentSpeakers() {
		return speaks.get(current).getSpeakers();
	}

	public String getPathWallpaper() {
		assert(wallpaper != null);
		return WALLPAPER_DIR + wallpaper + ".png";
	}

	/**
	 * Change the current dialog to the next
	 * @return true if there is a next dialog
	 */
	public boolean next() {
		boolean hasNext = hasNext();
		if(hasNext) {
			current++;
		}
		return hasNext;
	}

	/**
	 * 
	 * @return true if there is a next dialog
	 */
	public boolean hasNext() {
		return current < speaks.size() - 1;
	}
}
