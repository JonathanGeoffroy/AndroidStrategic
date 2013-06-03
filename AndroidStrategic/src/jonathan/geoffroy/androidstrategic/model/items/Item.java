package jonathan.geoffroy.androidstrategic.model.items;


public abstract class Item {

	private short use;
	private short useMax;
	private short weight;
	private int price;
	private String name;

	public boolean isDestructible() {
		return useMax < 0;
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
}