package app.entity;

import app.CoordinateAccessible;
import app.GridSized;
import app.properties.SudokuProperties;
import app.valueObject.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SudokuGrid
	   implements GridSized<SudokuCell>, CoordinateAccessible<SudokuCell> {
	private final SudokuProperties properties;
	private final LinkedList<SudokuCell> cells;

	// Constructors
	public SudokuGrid(LinkedList<SudokuCell> cells,
				   SudokuProperties properties) {
		this.cells = cells;
		this.properties = properties;
	}

	// Getter
	public LinkedList<SudokuCell> getCells() {
		return cells;
	}

	// Overrides
	@Override
	public LinkedList<SudokuCell> getRow(int y) {
		return cells.stream()
			   .filter(ssg -> ssg.getCoordinate().heightEquals(y))
			   .collect(Collectors.toCollection(LinkedList::new));
	}
	@Override
	public LinkedList<SudokuCell> getColumn(int x) {
		return cells.stream()
			   .filter(ssg -> ssg.getCoordinate().widthEquals(x))
			   .collect(Collectors.toCollection(LinkedList::new));
	}
	@Override
	public int getWidth() {
		return properties.getWidth();
	}
	@Override
	public int getHeight() {
		return properties.getHeight();
	}

	@Override
	public Optional<SudokuCell> getByCoordinate(Coordinate coordinate) {
		return cells.stream()
			   .filter(x -> x.getCoordinate().equals(coordinate))
			   .findFirst();
	}


	// Problema:
	// Ele pega uma subgrid e printa como se fosse 1 linha! Preciso pegar 3 subgrids da Y= Index e depois as cells de Y= index
	// Vai pegar as 3 linhas que há dentro da grid (que são 3 x 3 subgrids)

	@Override
	public String toString() {
		var stringBuilder = new StringBuilder();

		stringBuilder.append("==================================================================\n");
		for (byte y = 1; y <= getHeight(); y++) {

			var rowSubgrids = getRow(y);

			byte finalY = y;
			var rowCells = rowSubgrids
				   .stream()
				   .filter(cell -> cell.getCoordinate().getY() == finalY)
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
