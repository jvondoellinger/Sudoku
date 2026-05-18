package app.rules;

import app.entity.Rule;
import app.entity.SudokuCell;
import app.entity.SudokuSubgrid;

import java.util.stream.Collectors;

public class SubgridDistinctsRule implements Rule<SudokuSubgrid> {
	private final SudokuSubgrid grid;

	public SubgridDistinctsRule(SudokuSubgrid grid) {
		this.grid = grid;
	}

	// Não precisa verificar a quantidade de objeto, pode ser validada em outra rule

	@Override
	public boolean apply() {
		var values = grid.getCells()
			   .stream()
			   .map(SudokuCell::getValue)
			   .collect(Collectors.toSet());
		var total = grid.countCells();
		var emptyCells = grid.countEmptyCells();

		var filled = total - emptyCells;

		// Verificando a quantidade de celulas que possuem nessa subgrid
		// Verifico quantidade de celulas vazias
		// Realizo a subtração para pegar a quantidade de celulas preenchidas
		// Por fim, comparo com os valores de Set<Byte>
		// Caso haja algum byte repetido, o Set<> ficará com o tamanho menor do que o esperado e será considerado violação de regra

		return filled == values.size();
	}
}
