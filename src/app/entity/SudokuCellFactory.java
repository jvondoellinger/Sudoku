package app.entity;

import app.valueObject.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuCellFactory {
	public SudokuCell makeCell(Byte value, Coordinate coordinate) {
		return new SudokuCell(value, coordinate);
	}




}
