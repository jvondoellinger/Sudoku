package app.entity;

import app.valueObject.Coordinate;

import java.util.LinkedList;

import static app.helpers.CoordinateHelper.forEachCoordinate;

public class SudokuGridFactory {
	private static final int HEIGHT = 3;
	private static final int WIDTH = 3;
	private static final SudokuSubgridFactory subgridFactory = new SudokuSubgridFactory();

	public SudokuGrid make() {
		var subgrids = new LinkedList<SudokuSubgrid>();

		forEachCoordinate(HEIGHT, WIDTH, (coordinates) -> {
			var subgrid = subgridFactory.makeSubgrid(coordinates);
			subgrids.add(subgrid);
		});

		var grid = new SudokuGrid(subgrids);
		return grid;
	}
}
