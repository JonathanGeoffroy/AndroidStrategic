package jonathan.geoffroy.androidstrategic.model.games;

public class AllKilledCondition implements WinCondition {

	@Override
	public boolean hasWon(Game game) {
		return false;
		
	}
}