package jonathan.geoffroy.androidstrategic.model.dialogs;

import java.util.ArrayList;

public class Speak {
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
