package app.entity;

import app.rules.ColumnUniquenessRule;
import app.rules.RowUniquenessRule;
import app.rules.SubgridDistinctsRule;
import app.valueObject.Coordinate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static app.helpers.CoordinateHelper.forEachCoordinate;

public class SudokuSubgridFactory {
	private static int retries = 0;

	private final static byte MIN = 1;
	private final static byte MAX = 9;

	/**
	 * @apiNote Atenção, retona uma implementação default de subgrid
	 * @return Retorna uma subgrod padrão na proporção 3x3 localizada em  x=1 e y=1 da tela (padrão da factory)
	 */
	public SudokuSubgrid makeSubgrid() {
		return makeSubgrid(3, 3, new Coordinate(1, 1));
	}

	public SudokuSubgrid makeSubgrid(final Coordinate coordinate) {
		return makeSubgrid(3, 3, coordinate);
	}

	public SudokuSubgrid makeSubgrid(final int height, final int width, final Coordinate subgridCoordinate) {
		retries++;

		var cells = new LinkedList<SudokuCell>();
		var subgrid =  new SudokuSubgrid(cells, new ArrayList<>(), subgridCoordinate);

/*		subgrid.addRule(new RowUniquenessRule(subgrid));
		subgrid.addRule(new ColumnUniquenessRule(subgrid));*/
		subgrid.addRule(new SubgridDistinctsRule(subgrid));

		var rangeList = IntStream.rangeClosed(MIN, MAX)
			   .boxed()
			   .collect(Collectors.toList());
		Collections.shuffle(rangeList);

		forEachCoordinate(height, width, coordinates -> {
			var random = rangeList.remove(0);
			cells.add(new SudokuCell(random.byteValue(), coordinates));
		});

		if (retries > 250) {
			retries = 0;
			throw new RuntimeException("Can't apply rules on subgrid.");
		}

		if (subgrid.hasViolations()) {
			retries++;
			return makeSubgrid();
		}

		return subgrid;
	}

	/**
	 *
	 * @param howManySubgrids Quantidade de subgrids você quer fabricar
	 * @return List<Subgrids> do tamanho que solicitou
	 */
	public LinkedList<SudokuSubgrid> makeMany(final int howManySubgrids) {
		return IntStream.range(0, howManySubgrids)
			   .mapToObj(ignored -> makeSubgrid())
			   .collect(Collectors.toCollection(LinkedList::new));
	}

}
