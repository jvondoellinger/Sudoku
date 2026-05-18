package app.rules;

import app.entity.Rule;
import app.entity.SudokuSubgrid;

import java.util.Objects;

public class ColumnUniquenessRule implements Rule<SudokuSubgrid> {
	private final SudokuSubgrid grid;

	public ColumnUniquenessRule(SudokuSubgrid grid) {
		this.grid = grid;
	}

	@Override
	public boolean apply() {
		var height = grid.getHeight();

		for (int horizontal = 1; horizontal <= height; horizontal++) {
			var columnValues = grid.getColumnValues(horizontal);

			var emptyValues = columnValues
				   .stream()
				   .filter(Objects::isNull)
				   .count();

			if (emptyValues == height) {
				System.out.println("Coluna %s %s completamente vazia!".formatted(height, horizontal));
				return false;
			}
			if (columnValues.size() != height) {
				System.out.println("Quantidade de celulas diferentes do tamanho da subgrid!".formatted(height, horizontal));
				return false;
			}
		}

		return true;
	}

}
