package jonathan.geoffroy.androidstrategic.model.mapping.magic;

import jonathan.geoffroy.androidstrategic.model.fighters.Fighter;

public abstract class Magic {

	public abstract void magic(Fighter fighter);

	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		return getClass().equals(other.getClass());
	}
}