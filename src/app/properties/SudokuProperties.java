package app.properties;

public class SudokuProperties {
	private final int height;
	private final int width;

	public SudokuProperties(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public SudokuProperties() {
		this.height = 9;
		this.width = 9;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
