package jonathan.geoffroy.androidstrategic.model.dialogs;

import jonathan.geoffroy.androidstrategic.view.utils.App;

public class Speaker {
	public static final int LEFT = 0, CENTER = 1, RIGHT = 2;
	private String speaker;
	private int placeNumber;
	private int orientation;

	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public int getPlaceNumber() {
		return placeNumber;
	}
	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getTextureName() {
		return App.DIALOGS_DIR + speaker + ".png";
	}	
}
