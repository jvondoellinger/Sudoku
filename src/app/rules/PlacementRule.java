package app.rules;

import app.entity.SudokuGrid;
import app.valueObject.Coordinate;

public interface PlacementRule {
	boolean canPlace(SudokuGrid grid,
	                          Coordinate coordinate,
						 Byte value);
}
