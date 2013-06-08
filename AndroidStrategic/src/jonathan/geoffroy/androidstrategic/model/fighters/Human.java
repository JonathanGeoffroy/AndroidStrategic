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
	protected short spireClass;
	protected short axExp;
	protected short swordExp;
	protected short bowExp;
	protected short darkBooExp;
	protected short fireBookExp;
	protected short spireExp;
	protected short scepterClass;
	protected short scpeterExp;
	
 
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
	public short getSpireClass() {
		return spireClass;
	}
	public void setSpireClass(short spireClass) {
		this.spireClass = spireClass;
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
	public short getDarkBooExp() {
		return darkBooExp;
	}
	public void setDarkBooExp(short darkBooExp) {
		this.darkBooExp = darkBooExp;
	}
	public short getFireBookExp() {
		return fireBookExp;
	}
	public void setFireBookExp(short fireBookExp) {
		this.fireBookExp = fireBookExp;
	}
	public short getSpireExp() {
		return spireExp;
	}
	public void setSpireExp(short spireExp) {
		this.spireExp = spireExp;
	}
	public short getScepterClass() {
		return scepterClass;
	}
	public void setScepterClass(short scepterClass) {
		this.scepterClass = scepterClass;
	}
	public short getScpeterExp() {
		return scpeterExp;
	}
	public void setScpeterExp(short scpeterExp) {
		this.scpeterExp = scpeterExp;
	}
}