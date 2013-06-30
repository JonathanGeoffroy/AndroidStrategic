package jonathan.geoffroy.androidstrategic.model.items;

public abstract class Item {

	protected short use;
	protected short useMax;
	private short weight;
	private int price;
	protected String name;

	public Item() {
		name = defaultName();
	}

	public String defaultName() {
		return getClass().getName();
	}

	/**
	 * Decrements use number
	 */
	public void isUsed() {
		assert(use > 0);
		assert(use <= useMax);
		use--;
	}

	public boolean isDestructible() {
		return useMax < 0;
	}

	public boolean isDestroyed() {
		return use == 0;
	}

	public short getUse() {
		return use;
	}

	public void setUse(short use) {
		this.use = use;
	}

	public short getUseMax() {
		return useMax;
	}

	public void setUseMax(short useMax) {
		this.useMax = useMax;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract int getType();
}