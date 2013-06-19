package jonathan.geoffroy.androidstrategic.model.fighters;

public class FightResult {
	private Fighter fighters[];
	private int inflictedDamages[][];
	private boolean criticalDamages[][];
	private boolean touched[][];
	private int experienceWon[];
	private int hitNumber[];

	public FightResult(Fighter assailant, Fighter assaulted) {
		fighters = new Fighter[2];
		fighters[0] = assailant;
		fighters[1] = assaulted;

		hitNumber = new int[2];

		inflictedDamages = new int[2][2];
		for(int i = 0; i < inflictedDamages.length; i++) {
			inflictedDamages[0][i] = -1;
			inflictedDamages[1][i] = -1;
		}

		criticalDamages = new boolean[2][2];

		touched = new boolean[2][2];
		experienceWon = new int[2];
	}

	/**
	 * Store an attack's result
	 * @param fighterNumber the fighter who attack
	 * @param the inflicted damage
	 * @param hasTouched true if the attack succeed
	 * @param critical true if it's a critical attack
	 */
	public void addAttack(int fighterNumber, int damage, boolean hasTouched, boolean critical) {
		assert(fighterNumber == 0 || fighterNumber == 1);
		inflictedDamages[fighterNumber][hitNumber[fighterNumber]] = damage;
		touched[fighterNumber][hitNumber[fighterNumber]] = hasTouched;
		criticalDamages[fighterNumber][hitNumber[fighterNumber]] = critical;
		
		hitNumber[fighterNumber]++;
		assert(hitNumber[fighterNumber] >= 0 && hitNumber[fighterNumber] <= 2);
	}

	public int getNumberAttacks(int player) {
		assert(player == 0 || player == 1);
		return hitNumber[player];
	}

	public boolean isDead(int player) {
		assert(player == 0 || player == 1);
		return fighters[player].isDead();
	}

	public Fighter[] getFighters() {
		return fighters;
	}

	public void setFighters(Fighter[] fighters) {
		this.fighters = fighters;
	}

	public int[][] getInflictedDamages() {
		return inflictedDamages;
	}

	public boolean[][] getCriticalDamages() {
		return criticalDamages;
	}

	public boolean[][] getTouched() {
		return touched;
	}

	public int[] getExperienceWon() {
		return experienceWon;
	}

	public void setExperienceWon(int experienceWon[]) {
		this.experienceWon = experienceWon;
	}

	public int getSumDamages(int fighterNum) {
		assert(fighterNum == 0 || fighterNum == 1);
		assert(fighters[fighterNum] != null);
		
		int sum = 0;
		for(int i = 0; i < hitNumber[fighterNum]; i++) {
			assert(inflictedDamages[fighterNum][i] >= 0) : "inflicted damages should be > 0 (actual: " + inflictedDamages[fighterNum][i] + ")";
			sum += inflictedDamages[fighterNum][i];
		}
		return sum;
	}

	public void calculateExperienceWon() {
		for(int i = 0; i < fighters.length; i++) {
			experienceWon[i] = fighters[i].experienceWon(this);
		}
	}
}
