package utilities;

public class Point {
	
	public int x, y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int getTaxiCabDistance(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y-other.y);
	}
	
	
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}
}
