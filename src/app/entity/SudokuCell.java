package app.entity;

import app.valueObject.Coordinate;
import java.util.ArrayList;
import java.util.Objects;

public class SudokuCell {
	private final Coordinate coordinate;
	private Byte value;

	protected SudokuCell(Byte value, Coordinate coordinate) {
		this.coordinate = coordinate;
		this.value = value;
	}

	// Getters
	public final Coordinate getCoordinate() {
		return coordinate;
	}
	public final Byte getValue() {
		return value;
	}
	public final void setValue(Byte value) {
		if (this.value != null) {
			return;
		}

		this.value = value;
	}




	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		SudokuCell that = (SudokuCell) o;
		return Objects.equals(coordinate, that.coordinate)
			   && Objects.equals(value, that.value);
	}
	@Override
	public int hashCode() {
		return Objects.hash(coordinate, value);
	}


	@Override
	public String toString() {
		return "SudokuCell{" +
			   "coordinate=" + coordinate +
			   ", value=" + value +
			   '}';
	}
}
