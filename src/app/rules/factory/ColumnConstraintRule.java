package app.rules.factory;

import app.entity.SudokuCell;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class ColumnConstraintRule implements SudokuConstraintRule {
	@Override
	public boolean violates(LinkedList<SudokuCell> cells, SudokuCell candidate) {
		var coordinate = candidate.getCoordinate();

		var distinctCells = cells.stream()
			   .filter(cell -> cell
					 .getCoordinate()
					 .getX() == coordinate.getX()
			   ) // Vamos buscar pelo numero da coluna
			   .collect(Collectors.toSet());

		System.out.println();
		System.out.println("Quantidade de celulas: " + cells.size());
		System.out.println("Celuals distintas: " + distinctCells.size());
		System.out.println(distinctCells.size() != cells.size());
		System.out.println();
		return distinctCells.size() != cells.size();
	}
}
