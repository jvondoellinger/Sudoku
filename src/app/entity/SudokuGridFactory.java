package app.entity;

import app.rules.ColumnConstraintRule;
import app.rules.RowConstraintRule;
import app.valueObject.Coordinate;
import app.valueObject.Range;

import java.util.LinkedList;
import java.util.stream.Collectors;

import static app.helpers.CoordinateHelper.forEachSquareCoordinates;

public class SudokuGridFactory {
	// Default property values
	private static final int HEIGHT = 9;
	private static final int WIDTH = 9;

	// Properties
	private final int height;
	private final int width;

	// Constructors
	public SudokuGridFactory(int height, int width) {
		this.height = height;
		this.width = width;
	}
	public SudokuGridFactory() {
		height = HEIGHT;
		width = WIDTH;
	}

	// Factories
	private static final SudokuCellFactory subgridFactory = new SudokuCellFactory();

	public SudokuGrid make() {
		var cells = new LinkedList<SudokuCell>();
		var factory = new SudokuCellFactory();

		forEachSquareCoordinates(height, width, coordinate -> {
			cells.add(factory.makeCell((byte) 2, coordinate));
		});

		var grid = new SudokuGrid(cells);
		return grid;
	}
}
