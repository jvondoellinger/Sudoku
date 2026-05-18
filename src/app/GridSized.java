package app;

import java.util.LinkedList;

public interface GridSized<T> extends Dimensioned {
	/**
	 * Retorna todos os elementos presentes na linha informada.
	 *
	 * @param y índice da linha no eixo vertical
	 * @return lista contendo todos os elementos da linha especificada
	 *
	 * @apiNote Considere o eixo Y como o número da linha da estrutura.
	 */
	LinkedList<T> getRow(final int y);

	/**
	 * Retorna todos os elementos presentes na coluna informada.
	 *
	 * @param x índice da coluna no eixo horizontal
	 * @return lista contendo todos os elementos da coluna especificada
	 *
	 * @apiNote Considere o eixo X como o número da coluna da estrutura.
	 */
	LinkedList<T> getColumn(final int x);

}
