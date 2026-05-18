package app.valueObject;


import java.util.Objects;

public class Coordinate {
	private int x, y;

	/**
	 *
	 * @param x Deve representar as coordenadas do vertice horizonal
	 * @param y Deve representar as coordenadas do vertice vertical
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void moveToLeft() {
		if (x <= 1) return;
		x--;
	}

	void moveToRight() {
		x++;
	}

	void moveToDown() {
		if (y <= 1) return;
		y--;
	}

	void moveToUp() {
		y++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Coordinate{" +
			   "x=" + x +
			   ", y=" + y +
			   '}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;

		return x == that.x
			   && y == that.y;
	}

	public boolean heightEquals(int y) {
		return this.y == y;
	}
	public boolean widthEquals(int x) {
		return this.x == x;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

