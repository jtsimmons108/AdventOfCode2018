package utilities;

public class MovingPoint extends Point{

	
	public int vx, vy;
	
	public MovingPoint(int x, int y, int vx, int vy) {
		super(x, y);
		this.vx = vx;
		this.vy = vy;
	}
	
	
	public void move() {
		x += vx;
		y += vy;
	}
	public void move(int times) {
		x += vx * times;
		y += vy * times;
	}
	public String toString() {
		return String.format("%s moving at <%d, %d>", super.toString(), vx, vy);
	}
}
