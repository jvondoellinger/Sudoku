package app;

import app.valueObject.Coordinate;

import java.util.Optional;

public interface CoordinateAccessible<T> {
	Optional<T> getByCoordinate(Coordinate coordinate);
}
