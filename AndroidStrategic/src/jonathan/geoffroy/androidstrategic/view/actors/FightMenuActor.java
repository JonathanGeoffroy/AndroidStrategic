package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;
import jonathan.geoffroy.androidstrategic.view.screens.MapScreen;
import jonathan.geoffroy.androidstrategic.view.utils.App;
import jonathan.geoffroy.androidstrategic.view.utils.HelpScreen;
import jonathan.geoffroy.androidstrategic.view.utils.TableActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class FightMenuActor extends TableActor {
	private TextButton[] buttons;
	private TextButtonStyle style;

	public FightMenuActor(MapScreen mapScreen) {
		super(mapScreen);
	}

	private TextButton createEndTurnButton(TextButtonStyle style) {
		TextButton button = new TextButton("end turn", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "end turn!");
			}
		});

		return button;
	}

	private TextButton createEndFightButton(TextButtonStyle style) {
		TextButton button = new TextButton("end fight", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "end fight!");
			}
		});

		return button;
	}

	private TextButton createSuspendButton(TextButtonStyle style) {
		TextButton button = new TextButton("suspend", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "suspend");
			}
		});

		return button;
	}

	private TextButton createLeaveButton(TextButtonStyle style) {
		TextButton button = new TextButton("leave", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "leave!");
			}
		});

		return button;
	}

	private TextButton createSaveButton(TextButtonStyle style) {
		TextButton button = new TextButton("save", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "save");
			}
		});

		return button;
	}

	private TextButton createExchangeButton(TextButtonStyle style) {
		TextButton button = new TextButton("exchange", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "exchange");
			}
		});

		return button;
	}

	private TextButton createObjectButton(TextButtonStyle style) {
		TextButton button = new TextButton("objects", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "objects!");
			}
		});

		return button;
	}

	private TextButton createSuperPushButton(TextButtonStyle style) {
		TextButton button = new TextButton("super push", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "super push!");
			}
		});

		return button;
	}

	private TextButton createPushButton(TextButtonStyle style) {
		TextButton button = new TextButton("push", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "push!");
			}
		});

		return button;
	}

	private TextButton createAttackButton(TextButtonStyle style) {
		TextButton button = new TextButton("attack", style);
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.log("test", "attack!");
			}
		});

		return button;
	}

	@Override
	public void loadTable() {
		style = new TextButtonStyle();
		style.font = (BitmapFont) HelpScreen.getApp().getAsset(App.FONTS_DIR + "fighterInfo.fnt");
		style.fontColor = Color.WHITE;

		buttons = new TextButton[Map.NB_MENUS];
		buttons[0] = createAttackButton(style);
		buttons[1] = createPushButton(style);
		buttons[2] = createSuperPushButton(style);
		buttons[3] = createObjectButton(style);
		buttons[4] = createExchangeButton(style);
		buttons[5] = createSaveButton(style);
		buttons[6] = createLeaveButton(style);
		buttons[7] = createSuspendButton(style);
		buttons[8] = createEndFightButton(style);
		buttons[9] = createEndTurnButton(style);
	}

	@Override
	public void reloadTable() {
		assert(table != null);
		assert(style != null);

		Fighter fighter = mapScreen.getSelectedFighter();
		Map map = Fighter.getMap();

		table.clear();
		for(Integer i : map.possibilities(fighter)) {
			table.add(buttons[i]).expandX();
			table.row();
		}
		table.pack();
		table.setPosition(getX() + getWidth() /2 - table.getWidth() / 2, getY() + getHeight() / 2 - table.getHeight() / 2);
	}
}
