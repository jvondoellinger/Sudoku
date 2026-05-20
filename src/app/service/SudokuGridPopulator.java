package app.service;

import app.entity.SudokuGrid;
import app.valueObject.Range;

public class SudokuGridPopulator {
	public SudokuGrid populate(SudokuGrid notFilled) {
		var range = new Range(1, 9);
		for (int i = 1; i < notFilled.getCells().size(); i++) {
			if (i % 9 == 0)
			notFilled.getCells().forEach(cell -> {

			});
		}

		return null;
	}
}
