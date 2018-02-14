package top.choppy.util;

public class Coordinate2D {
	protected int x,y;
	
	public Coordinate2D(int x, int y) {
		this.x = x;
		this.y = y;
	} 
	
	public Coordinate2D() {}
	
	public int getX () {
		return x;
	}
	public int getY () {
		return y;
	}
	public void setX (int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
