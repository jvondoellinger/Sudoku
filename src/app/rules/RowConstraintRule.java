package app.rules;

import app.entity.SudokuCell;

import java.util.ArrayList;
import java.util.LinkedList;

public class RowConstraintRule implements SudokuConstraintRule{
	@Override
	public boolean violates(LinkedList<SudokuCell> cells, SudokuCell candidate) {
		var coordinate = candidate.getCoordinate();

		var containsEquals = new ArrayList<>(cells).stream()
			   .filter(cell -> cell.getCoordinate().getY() == coordinate.getY())
			   .anyMatch(cell -> cell.getValue().equals(candidate.getValue()));

		 return containsEquals;
	}
}
