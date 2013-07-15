package jonathan.geoffroy.androidstrategic.model.dialogs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import jonathan.geoffroy.androidstrategic.model.mapping.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Dialog {
	private String title;
	ArrayList<Speak> speaks;

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
}

class Speaker {
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
}

class Speak {
	private ArrayList<Speaker> speakers;
	private int speakerNum;
	private String text;

	public ArrayList<Speaker> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(ArrayList<Speaker> speakers) {
		this.speakers = speakers;
	}
	public int getSpeakerNum() {
		return speakerNum;
	}
	public void setSpeakerNum(int speakerNum) {
		this.speakerNum = speakerNum;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
