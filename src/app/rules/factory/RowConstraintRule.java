package app.rules.factory;

import app.entity.SudokuCell;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class RowConstraintRule implements SudokuConstraintRule{
	@Override
	public boolean violates(LinkedList<SudokuCell> cells, SudokuCell candidate) {
		var coordinate = candidate.getCoordinate();

		var distinctCells = cells.stream()
			   .filter(cell -> cell
					 .getCoordinate()
					 .getY() == coordinate.getY()
			   )// Vamos comparar as alturas das celulas para pegar a linha toda
			   .collect(Collectors.toSet());

		 return distinctCells.size() != cells.size();
	}
}
