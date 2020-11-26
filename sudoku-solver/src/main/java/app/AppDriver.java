package app;
import java.lang.*; 

import javax.swing.SwingUtilities;

public class AppDriver {

	public static void main(String[] args) {

		int N = 9, K = 20; 
		PuzzleGenerator sudoku = new PuzzleGenerator(N, K); 
		sudoku.fillValues();
		// unsolved version of the puzzle
		int[][] unsolvedPuzzle = new int[9][9];
		getGeneratedPuzzle(sudoku.getPuzzle(), unsolvedPuzzle);
		
		// solved version of the same
		int[][] grid = sudoku.getPuzzle();
		SudokuSolver.solveSudoku(grid);
		printGrid(grid);

		// invoke UI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SudokuUI(unsolvedPuzzle,grid);
			}
		});
	}
	
	static void getGeneratedPuzzle(int source[][], int target[][]){
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					target[i][j] = source[i][j];    
				}
			}
	}
	static void printGrid (int grid[][])
	{
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(); 
	}
}
