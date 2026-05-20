package app.rules;

import app.entity.SudokuCell;

import java.util.LinkedList;

public interface SudokuConstraintRule {
	boolean violates(LinkedList<SudokuCell> cells,
				  SudokuCell candidate);
}
