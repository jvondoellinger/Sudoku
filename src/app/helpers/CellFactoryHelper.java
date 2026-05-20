package app.helpers;

import app.entity.SudokuCell;
import app.entity.SudokuCellFactory;
import app.valueObject.Coordinate;
import app.valueObject.Range;

import java.util.LinkedList;

public class CellFactoryHelper {

	public static LinkedList<SudokuCell> factoryCells(final int maxHeight, final int maxWidth) {
		var linkedList = new LinkedList<SudokuCell>();
		for (int y = 1; y <= maxWidth; y++) {
			var range = new Range(1, 9);
			for (int x = 1; x <= maxHeight; x++) {
				var coordinate = new Coordinate(x, y);
				var cell = new SudokuCellFactory().makeCell((byte) 0, coordinate);
				linkedList.add(cell);
			}
		}
		return linkedList;
	}
}
