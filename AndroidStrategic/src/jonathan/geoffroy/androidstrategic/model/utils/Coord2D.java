package jonathan.geoffroy.androidstrategic.model.utils;

public class Coord2D {
	public int x, y;
	
	public Coord2D() {}
	public Coord2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord2D) {
			Coord2D other = (Coord2D) obj;
			return x == other.x && y == other.y;
		}
		return false;
	}
}
