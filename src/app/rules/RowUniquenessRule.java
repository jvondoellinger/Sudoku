package app.rules;

import app.entity.Rule;
import app.entity.SudokuSubgrid;

import java.util.Objects;

public class RowUniquenessRule implements Rule<SudokuSubgrid> {
	private final SudokuSubgrid subgrid;

	public RowUniquenessRule(SudokuSubgrid subgrid) {
		this.subgrid = subgrid;
	}

	@Override
	public boolean apply() {
		var cells = subgrid.getCells();;
		var width = subgrid.getWidth();

		for (int vertical = 1; vertical <= width; vertical++) {
			var rowValues = subgrid.getRowValues(vertical);
			var emptyValues = rowValues
				   .stream()
				   .filter(Objects::isNull)
				   .count();
			if (emptyValues == width) {
				System.out.printf("Linha %s completamente vazia! \n", vertical);
				return false;
			}

			if (rowValues.size() != width) {
				System.out.println("Row size: " );
				System.out.println("Cells size: ");
				System.out.println("Row size: ");
				System.out.println("Row size: ");
				System.out.printf("Tamanho da linha diferente do tamanho da subgrid! \n", vertical);
				return false;
			}
		}
		return true;
	}

}
