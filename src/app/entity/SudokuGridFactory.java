package app.entity;

import app.rules.factory.ColumnConstraintRule;
import app.rules.factory.RowConstraintRule;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static app.helpers.CoordinateHelper.forEachCoordinate;

public class SudokuGridFactory {
	private static byte LOOP_COUNTER = 0;
	private static final int HEIGHT = 9;
	private static final int WIDTH = 9;

	private final static byte MIN_VALUE = 1;
	private final static byte MAX_VALUE = 9;

	private static final SudokuCellFactory subgridFactory = new SudokuCellFactory();

	private final ColumnConstraintRule columnConstraintRules = new ColumnConstraintRule();
	private final RowConstraintRule rowConstraintRules = new RowConstraintRule();

	// Ao inves de recriar a GRID, somente reordenar a linha para que siga as regras!
	public SudokuGrid make() {
		var cells = new LinkedList<SudokuCell>();
		var rangeList = getByteRange();

		forEachCoordinate(HEIGHT, WIDTH, (coordinates) -> {
			var removed = rangeList.remove(0);
			var candidate = subgridFactory.makeCell(removed, coordinates);

			while (violatesAny(cells, candidate)) {
				LOOP_COUNTER++;
				rangeList.add(removed);
				Collections.shuffle(rangeList);
				removed = rangeList.remove(0);
				candidate = subgridFactory.makeCell(removed, coordinates);
				System.out.println(LOOP_COUNTER);
				if (LOOP_COUNTER == 127) break;
			}

			cells.add(candidate);

			// Re-populating
			if (rangeList.isEmpty()) {
				rangeList.addAll(getByteRange());
			}
		});

		LOOP_COUNTER = 0;

		var grid = new SudokuGrid(cells);
		return grid;
	}

	private boolean violatesAny(LinkedList<SudokuCell> cells,
	                            SudokuCell candidate) {
		return columnConstraintRules.violates(cells, candidate)
			   || rowConstraintRules.violates(cells, candidate);
	}

	/**
 	 * @return Devolve um Range sem replicas de 1 ao 9 (sem nulos)
	 */
	private List<Byte> getByteRange() {
		var rangeList = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
			   .boxed()
			   .map(Integer::byteValue)
			   .collect(Collectors.toList());
		Collections.shuffle(rangeList);
		return rangeList;
	}
}
