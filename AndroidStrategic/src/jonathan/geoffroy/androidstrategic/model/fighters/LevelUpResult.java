package jonathan.geoffroy.androidstrategic.model.fighters;

public class LevelUpResult {
	private int upLevel;
	private int upHpMax;
	private int upConstitution;
	private int upDefense;
	private int upResistance;
	private int upStrength;
	private int upMagic;
	private int upSpeed;
	private int upMovementMax;
	private int upLuck;
	private int upSkill;
	
	public int getUpLevel() {
		return upLevel;
	}
	public void setUpLevel(int upLevel) {
		this.upLevel = upLevel;
	}
	public void incLevel() {
		upLevel++;
	}
	public int getUpHpMax() {
		return upHpMax;
	}
	public void incHpMax() {
		upHpMax++;
	}
	public void setUpHpMax(int upHpMax) {
		this.upHpMax = upHpMax;
	}
	public int getUpConstitution() {
		return upConstitution;
	}
	public void incConstitution() {
		upConstitution++;
	}
	public void setUpConstitution(int upConstitution) {
		this.upConstitution = upConstitution;
	}
	public int getUpDefense() {
		return upDefense;
	}
	public void incDefense() {
		upDefense++;
	}
	public void setUpDefense(int upDefense) {
		this.upDefense = upDefense;
	}
	public int getUpResistance() {
		return upResistance;
	}
	public void incResistance() {
		upResistance++;
	}
	public void setUpResistance(int upResistance) {
		this.upResistance = upResistance;
	}
	public int getUpStrength() {
		return upStrength;
	}
	public void incStrength() {
		upStrength++;
	}
	public void setUpStrength(int upStrength) {
		this.upStrength = upStrength;
	}
	public int getUpMagic() {
		return upMagic;
	}
	public void intMagic() {
		upMagic++;
	}
	public void setUpMagic(int upMagic) {
		this.upMagic = upMagic;
	}
	public int getUpSpeed() {
		return upSpeed;
	}
	public void setUpSpeed(int upSpeed) {
		this.upSpeed = upSpeed;
	}
	public int getUpMovementMax() {
		return upMovementMax;
	}
	public void setUpMovementMax(int upMovementMax) {
		this.upMovementMax = upMovementMax;
	}
	public int getUpLuck() {
		return upLuck;
	}
	public void setUpLuck(int upLuck) {
		this.upLuck = upLuck;
	}
	public int getUpSkill() {
		return upSkill;
	}
	public void setUpSkill(int upSkill) {
		this.upSkill = upSkill;
	}
}
