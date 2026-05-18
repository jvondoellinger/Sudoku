import app.entity.SudokuGrid;
import app.entity.SudokuGridFactory;
import app.entity.SudokuSubgridFactory;
import app.layout.SudokuFrame;

import javax.swing.*;
import java.awt.image.ImageObserver;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		var factory = new SudokuGridFactory();
		var grid = factory.make();

		System.out.println(grid.toString());

		var frame = new SudokuFrame();
	}
}