package app.rules;

import app.entity.SudokuCell;

import java.util.LinkedList;

public class ColumnConstraintRule implements SudokuConstraintRule {
	@Override
	public boolean violates(LinkedList<SudokuCell> cells, SudokuCell candidate) {

		var coordinate = candidate.getCoordinate();

		var containsEquals = cells.stream()
			   .filter(cell -> cell != candidate)
			   .filter(cell -> cell.getCoordinate().getX() == coordinate.getX())
			   .anyMatch(x -> x.getValue().equals(candidate.getValue()));

		return containsEquals;
	}
}
