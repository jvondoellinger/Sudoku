package app.layout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static app.helpers.CoordinateHelper.forEachCoordinate;

public class SudokuFrame extends JFrame {
	public SudokuFrame() {
		setTitle("Sudoku");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		var board = new JPanel();
		board.setLayout(new GridLayout(9, 9));

		forEachCoordinate(9, 9, coordinate -> {
			var cell = new JTextField();

			cell.setHorizontalAlignment(SwingConstants.CENTER);
			cell.setFont(new Font("Arial", Font.BOLD, 50));
			cell.setBorder(new LineBorder(Color.BLACK));

			board.add(cell);
		});

		add(board);
		setVisible(true);
	}
}
