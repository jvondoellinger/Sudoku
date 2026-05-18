package app.entity;

import app.CoordinateAccessible;
import app.GridSized;
import app.valueObject.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SudokuGrid
	   extends BaseEntity<SudokuGrid>
	   implements GridSized<SudokuSubgrid>, CoordinateAccessible<SudokuSubgrid> {

	private final LinkedList<SudokuSubgrid> subgrids;

	public SudokuGrid(LinkedList<SudokuSubgrid> subgrids) {
		super(new ArrayList<>());
		this.subgrids = subgrids;
	}

	public SudokuGrid(List<Rule<SudokuGrid>> rules,
	                  LinkedList<SudokuSubgrid> subgrids) {
		super(rules);
		this.subgrids = subgrids;
	}

	@Override
	public LinkedList<SudokuSubgrid> getRow(int y) {
		return subgrids.stream()
			   .filter(ssg -> ssg.getCoordinate().heightEquals(y))
			   .collect(Collectors.toCollection(LinkedList::new));
	}

	// ! Helpers
	@Override
	public LinkedList<SudokuSubgrid> getColumn(int x) {
		return subgrids.stream()
			   .filter(ssg -> ssg.getCoordinate().widthEquals(x))
			   .collect(Collectors.toCollection(LinkedList::new));
	}

	// ! Overrides
	@Override
	public int getWidth() {
		return subgrids.stream()
			   .map(SudokuSubgrid::getCoordinate)
			   .map(Coordinate::getX)
			   .max(Integer::compareTo)
			   .orElse(0);
	}

	@Override
	public int getHeight() {
		return subgrids.stream()
			   .map(SudokuSubgrid::getCoordinate)
			   .map(Coordinate::getY)
			   .max(Integer::compareTo)
			   .orElse(0);
	}

	@Override
	public Optional<SudokuSubgrid> getByCoordinate(Coordinate coordinate) {
		return subgrids.stream()
			   .filter(x -> x.getCoordinate().equals(coordinate))
			   .findFirst();
	}


	// Problema:
	// Ele pega uma subgrid e printa como se fosse 1 linha! Preciso pegar 3 subgrids da Y= Index e depois as cells de Y= index

	@Override
	public String toString() {
		var stringBuilder = new StringBuilder();

		stringBuilder.append("==================================================================\n");
		for (byte y = 1; y <= getHeight() * getHeight(); y++) {
			byte finalY = y;

			var rowSubgrids = getRow(finalY);

			var rowCells = rowSubgrids
				   .stream()
				   .flatMap(ssg -> ssg.getRow(finalY).stream())
				   .collect(Collectors.toCollection(LinkedList::new));

			var rowBuilderString = rowStringBuilder(rowCells);
			stringBuilder.append(rowBuilderString);
		}

		stringBuilder.append("==================================================================\n");

		stringBuilder.append("X -> Eixo horizontal \n");
		stringBuilder.append("Y -> Eixo vertical \n");

		return stringBuilder.toString();
	}

	public StringBuilder rowStringBuilder(final LinkedList<SudokuCell> cells) {
		var stringBuilder = new StringBuilder();

		cells.forEach(cell -> {
			String cellText = cell.getValue() == null
				   ? " "
				   : cell.getValue().toString();

			stringBuilder.append(" | ")
				   .append(cellText)
				   .append(" | ");
		});

		stringBuilder.append('\n');

		return stringBuilder;
	}


}
