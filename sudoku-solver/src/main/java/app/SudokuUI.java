package app;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
/**
 * author: Puja Burman
 * created on 18/11/2020
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SudokuUI extends JFrame {


	private static final long serialVersionUID = 1L;
	// Name-constants for the game properties
	public static final int GRID_SIZE = 9;    // Size of the board
	public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

	// Name-constants for UI control (sizes, colors and fonts)
	public static final int CELL_SIZE = 60;   // Cell width/height in pixels
	public static final int CANVAS_WIDTH  = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
	// Board width/height in pixels
	public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
	public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0);  // RGB
	public static final Color OPEN_CELL_TEXT_NO = Color.RED;
	public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
	public static boolean solved = false;
	// The game board composes of 9x9 JTextFields,
	// each containing String "1" to "9", or empty String
	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
	private int[][] puzzle;
	private int[][] unsolved;
	/*Button b=new Button("Click Here");
	b.setBounds(50,100,80,30);*/
	//cp.add(b);
	public SudokuUI(int grid[][],int solvedGrid[][]) {
		this.puzzle = solvedGrid;
		this.unsolved = grid;
		Container cp = getContentPane();
		
		cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // 9x9 GridLayout

		// Allocate a common listener as the ActionEvent listener for all the
		InputListener listener = new InputListener();
		// Construct 9x9 JTextFields and add to the content-pane
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				tfCells[row][col] = new JTextField(); // Allocate element of array
				cp.add(tfCells[row][col]);            // ContentPane adds JTextField
				if (grid[row][col] == 0) {
					tfCells[row][col].setText("");     // set to empty string
					tfCells[row][col].setEditable(true);
					tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);

					// Add ActionEvent listener to process the input
					tfCells[row][col].addActionListener(listener);
				} else {
					tfCells[row][col].setText(grid[row][col] + "");
					tfCells[row][col].setEditable(false);
					tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
					tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
				}
				// Beautify all the cells
				tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
				tfCells[row][col].setFont(FONT_NUMBERS);
			}
		}

		// Set the size of the content-pane and pack all the components
		//  under this container.
		cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
		setTitle("Sudoku");
		setVisible(true);
		
	}

	private class InputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// All the 9*9 JTextFileds invoke this handler. We need to determine
			// which JTextField (which row and column) is the source for this invocation.
			int rowSelected = -1;
			int colSelected = -1;

			// Get the source object that fired the event
			JTextField source = (JTextField)e.getSource();
			// Scan JTextFileds for all rows and columns, and match with the source object
			boolean found = false;
			for (int row = 0; row < GRID_SIZE && !found; ++row) {
				for (int col = 0; col < GRID_SIZE && !found; ++col) {
					if (tfCells[row][col] == source) {
						rowSelected = row;
						colSelected = col;
						found = true;  // break the inner/outer loops
					}
				}
			}

			/*
			 *    Assume that the solution is unique. Compare the input number with
			 *    the number in the puzzle[rowSelected][colSelected].  If they are the same,
			 *    set the background to green (Color.GREEN); otherwise, set to red (Color.RED).
			 */
			int inputVal = Integer.parseInt(tfCells[rowSelected][colSelected].getText());
			if(inputVal == puzzle[rowSelected][colSelected]){
				tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
				unsolved[rowSelected][colSelected] = inputVal;
			}	
			else
				tfCells[rowSelected][colSelected].setBackground(Color.RED);
				
			/* 
			 * Check if the player has solved the puzzle after this move.
			 * You could update the masks[][] on correct guess, and check the masks[][] if
			 * any input cell pending.
			 */
			
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					if(unsolved[row][col] == 0 || unsolved[row][col]!= puzzle[row][col]){
						solved = false;
						break;
					}	
					if(unsolved[row][col]== puzzle[row][col])
						solved = true;
				}
				if(!solved)
					break;
			}
			if(solved){
				System.out.println("Congratulation! Puzzle solved!");
				JOptionPane.showMessageDialog(null, "Congratulation! Puzzle solved!");
				System.exit(0);
			}
			solved = false;
		}

	}

}
