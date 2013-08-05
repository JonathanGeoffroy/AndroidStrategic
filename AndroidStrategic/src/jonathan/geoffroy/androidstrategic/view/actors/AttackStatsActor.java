package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class AttackStatsActor extends Actor {
	public static final String WALLPAPER = "data/img/textures/battleStats.bmp";
	
	private MapScreen mapScreen;
	private Fighter fighters[];
	private Texture fightersHead[];
	private Table fightersTable[];
	private Texture wallpaper;
	private TextButton attack, cancel;
	private Skin skin;
	private LabelStyle style;

	public AttackStatsActor(MapScreen mapScreen) {
		super();
		this.mapScreen = mapScreen;
		BitmapFont font = (BitmapFont) HelpScreen.getApp().getAsset(FighterInfoActor.FONT);
		style = new LabelStyle(font, Color.WHITE);

		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		attack = new TextButton("attack!", textButtonStyle);
		attack.setBounds(getX() + getWidth() / 6, getY(), getWidth() / 3, getHeight() / 5);
		attack.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				System.out.println("ATTACK !");
				AttackStatsActor.this.mapScreen.disableAttackStats();
				return false;
			}
		});

		cancel = new TextButton("cancel...", textButtonStyle);
		cancel.setBounds(getX() + getWidth() * 4 / 6, getY(), getWidth() / 3, getHeight() / 5);
		cancel.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				System.out.println("ATTACK !");
				AttackStatsActor.this.mapScreen.disableAttackStats();
				return false;
			}
		});

		fighters = new Fighter[2];
		fightersHead = new Texture[2];
		fightersTable = new Table[2];
		fightersTable[0] = new Table();
		fightersTable[1] = new Table();
		
		wallpaper = (Texture) HelpScreen.getApp().getAsset(WALLPAPER);
	}

	/**
	 * Reload this Actor when changing fighters 
	 * @param assailant
	 * @param defender
	 */
	public void reload(Fighter assailant, Fighter defender) {
		assert(assailant != null && defender != null);
		App app = HelpScreen.getApp();

		fighters[0] = assailant;
		fighters[1] = defender;

		fightersTable[0].setBounds(getX(), getY() + getHeight() * 3 / 5, getWidth() * 2 / 5, getHeight());
		fightersTable[1].setBounds(getX() + getWidth() * 3 / 5, getY(), getWidth() * 2 / 5, getHeight());

		for(int i = 0; i < 2; i++) {
			Fighter other = fighters[(i+1) % 2];
			fightersHead[i] = (Texture) app.getAsset(App.DIALOGS_DIR + assailant.getTextureName());
			fightersTable[i] = new Table();

			fightersTable[i].add(new Label(fighters[i].getName(), style));
			fightersTable[i].row();

			fightersTable[i].add(new Label("ATK:", style));
			fightersTable[i].add(new Label("" + fighters[i].calculatePower(other), style));
			fightersTable[i].add(new Label("X " + fighters[i].hitNumber(other), style));
			fightersTable[i].row();

			fightersTable[i].add(new Label("PRE:", style));
			fightersTable[i].add(new Label("" + fighters[i].accuracy(other), style));
			fightersTable[i].row();

			fightersTable[i].add(new Label("CRT:", style));
			fightersTable[i].add(new Label("" + fighters[i].criticalAccuracy(other), style));
			fightersTable[i].row();
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(wallpaper, getX(), getY(), getWidth(), getHeight());
		batch.draw(fightersHead[0], getX(), getY() + getHeight() / 2, getWidth() * 2 / 5, getHeight() * 2 / 5);
		batch.draw(fightersHead[1], getX() + getWidth() * 3 / 5, getY() + getHeight() / 2, getWidth() * 2 / 5, getHeight() * 2 / 5);
		for(int i = 0; i < fightersTable.length; i++)
			fightersTable[i].draw(batch, parentAlpha);
		attack.draw(batch, parentAlpha);
		cancel.draw(batch, parentAlpha);
		
		System.out.println("drawed");
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		fightersTable[0].setBounds(getX(), getY() + getHeight() * 3 / 5, getWidth() * 2 / 5, getHeight());
		fightersTable[1].setBounds(getX() + getWidth() * 3 / 5, getY(), getWidth() * 2 / 5, getHeight());
	}	
}