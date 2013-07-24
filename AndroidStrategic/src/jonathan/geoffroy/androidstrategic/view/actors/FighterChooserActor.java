package jonathan.geoffroy.androidstrategic.view.actors;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;
import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.view.utils.ListActor;

public class FighterChooserActor extends ListActor<ChoosableFighter> {

	public FighterChooserActor(Team team) {
		super();

		ChoosableFighter fighterActor;
		for(Fighter f : team.getFighters()) {
			fighterActor = new ChoosableFighter(f);
			fighterActor.setSize(getWidth(), getHeight() / 8);
			addActor(fighterActor);
		}
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		for(ChoosableFighter cf : getActors()) {
			cf.setSize(getWidth(), getHeight() / 8);
		}
	}

	@Override
	protected void onChoose(ChoosableFighter previouslySelected, ChoosableFighter nextlySelected) {
		if(previouslySelected != null)
			previouslySelected.setChoosed(false);
		nextlySelected.setChoosed(true);
	}
}
