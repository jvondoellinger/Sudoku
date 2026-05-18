package app.entity;

import java.time.OffsetDateTime;
import java.util.List;

public class Sudoku {

	private OffsetDateTime playedAt;

	public Sudoku(List<SudokuCell> cells) {
		playedAt = OffsetDateTime.now();
	}
}
