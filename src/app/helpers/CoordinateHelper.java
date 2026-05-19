package app.helpers;

import app.valueObject.Coordinate;
import app.valueObject.Pair;

import java.util.function.Consumer;

public class CoordinateHelper {
	/**
	 * Percorre uma matriz bidimensional linha por linha,
	 * iniciando em X=0 até {@code maxWidth}, depois avançando para a próxima linha (Y++).
	 *
	 * <p>Ordem de execução:
	 * <pre>
	 * (0,0) -> (1,0) -> (2,0) ...
	 * quebra linha
	 * (0,1) -> (1,1) -> (2,1) ...
	 * </pre>
	 *
	 * @param maxHeight altura máxima da iteração
	 * @param maxWidth largura máxima da iteração
	 * @param consumer consumidor responsável por processar a coordenada atual
	 *
	 * @apiNote Atualmente validado apenas para matrizes quadradas
	 * (ex.: 3x3, 5x5, 9x9).
	 */
	public static void forEachCoordinate(final int maxHeight, final int maxWidth, final Consumer<Coordinate> consumer) {
		for (int y = 1; y <= maxWidth; y++) {
			for (int x = 1; x <= maxHeight; x++) {
				var coordinate = new Coordinate(x, y);
				consumer.accept(coordinate);
			}
		}
	}
}
