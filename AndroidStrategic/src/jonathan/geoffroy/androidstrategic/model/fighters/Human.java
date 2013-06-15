package jonathan.geoffroy.androidstrategic.model.fighters;

import jonathan.geoffroy.androidstrategic.model.items.weapons.Weapon;

public abstract class Human extends Fighter {
	protected Weapon equiped;
	protected short swordClass;
	protected short axClass;
	protected short bowClass;
	protected short lightBookClass;
	protected short darkBookClass;
	protected short fireBookClass;
	protected short spearClass;
	protected short scepterClass;
	protected short knifeClass;
	protected short axExp;
	protected short swordExp;
	protected short bowExp;
	protected short darkBookExp;
	protected short fireBookExp;
	protected short spearExp;
	protected short scepterExp;
	protected short knifeExp;

	@Override
	public int hitRate() {
		int rate = super.hitRate();
		if(equiped != null) {
			rate += equiped.getHitRate();
			if(rate > 100)
				rate = 100;
		}
		return rate;
	}

	@Override
	public short criticalRates() {
		short critical = super.criticalRates();
		if(equiped != null) {
			critical += equiped.getCriticalRate();
		}
		return critical;
	}

	public Weapon getEquiped() {
		return equiped;
	}
	public void setEquiped(Weapon equiped) {
		this.equiped = equiped;
	}
	public short getSwordClass() {
		return swordClass;
	}
	public void setSwordClass(short swordClass) {
		this.swordClass = swordClass;
	}
	public short getAxClass() {
		return axClass;
	}
	public void setAxClass(short axClass) {
		this.axClass = axClass;
	}
	public short getBowClass() {
		return bowClass;
	}
	public void setBowClass(short bowClass) {
		this.bowClass = bowClass;
	}
	public short getLightBookClass() {
		return lightBookClass;
	}
	public void setLightBookClass(short lightBookClass) {
		this.lightBookClass = lightBookClass;
	}
	public short getDarkBookClass() {
		return darkBookClass;
	}
	public void setDarkBookClass(short darkBookClass) {
		this.darkBookClass = darkBookClass;
	}
	public short getFireBookClass() {
		return fireBookClass;
	}
	public void setFireBookClass(short fireBookClass) {
		this.fireBookClass = fireBookClass;
	}
	public short getspearClass() {
		return spearClass;
	}
	public void setspearClass(short spearClass) {
		this.spearClass = spearClass;
	}
	public short getAxExp() {
		return axExp;
	}
	public void setAxExp(short axExp) {
		this.axExp = axExp;
	}
	public short getSwordExp() {
		return swordExp;
	}
	public void setSwordExp(short swordExp) {
		this.swordExp = swordExp;
	}
	public short getBowExp() {
		return bowExp;
	}
	public void setBowExp(short bowExp) {
		this.bowExp = bowExp;
	}
	public short getDarkBookExp() {
		return darkBookExp;
	}
	public void setDarkBookExp(short darkBookExp) {
		this.darkBookExp = darkBookExp;
	}
	public short getFireBookExp() {
		return fireBookExp;
	}
	public void setFireBookExp(short fireBookExp) {
		this.fireBookExp = fireBookExp;
	}
	public short getSpearExp() {
		return spearExp;
	}
	public void setSpearExp(short spearExp) {
		this.spearExp = spearExp;
	}
	public short getScepterClass() {
		return scepterClass;
	}
	public void setScepterClass(short scepterClass) {
		this.scepterClass = scepterClass;
	}
	public short getScepterExp() {
		return scepterExp;
	}
	public void setScepterExp(short scepterExp) {
		this.scepterExp = scepterExp;
	}
	public short getSpearClass() {
		return spearClass;
	}
	public void setSpearClass(short spearClass) {
		this.spearClass = spearClass;
	}
	public short getKnifeClass() {
		return knifeClass;
	}
	public void setKnifeClass(short knifeClass) {
		this.knifeClass = knifeClass;
	}
	public short getKnifeExp() {
		return knifeExp;
	}
	public void setKnifeExp(short knifeExp) {
		this.knifeExp = knifeExp;
	}
}