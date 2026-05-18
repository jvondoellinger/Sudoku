package app.helpers;

import app.valueObject.Coordinate;
import app.valueObject.Pair;

import java.util.function.Consumer;

public class CoordinateHelper {
	/**
	 *
	 * @param maxHeight Altura maxima que o loop poderá atingir
	 * @param maxWidth Largura maxima que o loop poderá atingir
	 * @param consumer Coordenada atual do loop
	 * @apiNote Atenção, por enquanto, foi testado somente em cubos (3x3, 5x5, 7x7, etc...)
	 */
	public static void forEachCoordinate(final int maxHeight, final int maxWidth, final Consumer<Coordinate> consumer) {
		for (int x = 1; x <= maxHeight; x++) {
			for (int y = 1; y <= maxWidth; y++) {
				var coordinate = new Coordinate(x, y);
				consumer.accept(coordinate);
			}
		}
	}
}
