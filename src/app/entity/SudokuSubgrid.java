package app.entity;

import app.CoordinateAccessible;
import app.GridSized;
import app.valueObject.Coordinate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SudokuSubgrid
	   extends BaseEntity<SudokuSubgrid>
	   implements GridSized<SudokuCell>,
	   CoordinateAccessible<SudokuCell> {
	private final LinkedList<SudokuCell> cells;
	private final Coordinate coordinate;

	protected SudokuSubgrid(final LinkedList<SudokuCell> cells,
	                        final List<Rule<SudokuSubgrid>> rules,
	                        final Coordinate coordinate) {
		super(rules);
		this.cells = cells;
		this.coordinate = coordinate;
	}


	public void addValue(final Coordinate coordinate, final byte value) {
		var cell = this.cells.stream()
			   .filter(x -> x.getCoordinate().equals(coordinate))
			   .findAny();
		if (cell.isEmpty()) {
			return;
		}

		// Por ser a mesma referencia em memoria, não é necessario atualizar a lista novamente, pois o objeto foi atualizado em memoria!
		var selectedCell = cell.get();
		selectedCell.setValue(value);
	}

	// ! Getter and Setters
	public List<SudokuCell> getCells() {
		return List.copyOf(cells);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	// ! Overrides
	@Override
	public int getWidth() {
		return cells
			   .stream()
			   .map(SudokuCell::getCoordinate)
			   .map(Coordinate::getX)
			   .max(Integer::compareTo)
			   .orElse(0);
	}
	@Override
	public int getHeight() {
		return cells
			   .stream()
			   .map(SudokuCell::getCoordinate)
			   .map(Coordinate::getY)
			   .max(Integer::compareTo)
			   .orElse(0);
	}

	@Override
	public Optional<SudokuCell> getByCoordinate(Coordinate coordinate) {
		return cells.stream()
			   .filter(x -> x.getCoordinate().equals(coordinate))
			   .findFirst();
	}

	@Override
	public LinkedList<SudokuCell> getRow(final int y) {

		return cells.stream()
				   .filter(cell -> {
					   System.out.println(cell);
					   return cell.getCoordinate().heightEquals(y);
				   })
				   .collect(Collectors.toCollection(LinkedList::new));
	}


	@Override
	public LinkedList<SudokuCell> getColumn(final int x) {

		return cells.stream()
			   .filter(cell -> cell.getCoordinate().widthEquals(x))
			   .collect(Collectors.toCollection(LinkedList::new));
	}


	public HashSet<Optional<Byte>> getRowValues(final int y) {
		return getRow(y)
			   .stream()
			   .map(sc -> Optional.of(sc.getValue()))
			   .collect(Collectors.toCollection(HashSet::new));
	}
	public HashSet<Optional<Byte>> getColumnValues(final int x) {
		return getColumn(x)
			   .stream()
			   .map(sc -> Optional.of(sc.getValue()))
			   .collect(Collectors.toCollection(HashSet::new));
	}

	public int countEmptyCells() {
		return Math.toIntExact(cells.stream()
			   .filter(cell -> cell.getValue() == null)
			   .count());
	}
	public int countCells() {
		return cells.size();
	}





	// ! Overrides
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int y = 1; y <= getHeight(); y++) {
			var rows = getRow(y);
			var stringRow = stringBuilderRow(rows);

			builder.append("-----".repeat(rows.size() + 1));
			builder.deleteCharAt(builder.lastIndexOf("-"));

			builder.append("\n");
			builder.append(stringRow);
			builder.append("\n");
		}

		builder.append("-------------------\n");
		builder.append("\n");
		builder.append("X -> Eixo horizontal \n");
		builder.append("Y -> Eixo vertical \n");
		return builder.toString();
	}

	private StringBuilder stringBuilderRow(final LinkedList<SudokuCell> rowCells) {
		StringBuilder str = new StringBuilder();

		for (final var cell : rowCells) {
			str.append(" | ");
			str.append(cell.getValue());
			str.append(" | ");
	   	}
		str.delete(0, 1);
		return str;
	}
}
