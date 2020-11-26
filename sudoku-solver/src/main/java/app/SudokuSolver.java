package app;

public class SudokuSolver {

	static boolean solveSudoku(int grid[][]){
		int i = 0, j = 0;
		//check for empty cells
		for(i = 0; i < 9; i++){
			boolean flag = false;
			for(j = 0; j < 9; j++){
				if(grid[i][j] == 0){
					flag = true;
					break;
				}
			}
			if(flag == true){
				break;
			}
		}
		//if no empty cells then return true implying all the cells have been filled
		if(i == 9 && j == 9){
			return true;
		}

		//else pick correct entry from (n=1 to 9) for the empty cell grid[i][j]
		for(int n = 1; n <= 9; n++){
			if(isSafe(grid, i, j, n) == true){
				grid[i][j] = n;
				/*for each safe n=1 to 9, call solveSudoku to check if using a
				 *  particular n in an empty cell proceeds towards solution
				*/ 
				if(solveSudoku(grid) == true){
					return true;
				}
				/*if particular n in an empty cell doesn't proceed
				towards solution, then backtrack using n=n+1 */
				grid[i][j] = 0;
			}
		}
		return false;
	}
	static boolean isSafe(int grid[][], int r, int c, int n){
		//check if none of the elements in row r and column c contain n
		for(int k = 0; k < 9; k++){
			if(grid[r][k] == n || grid[k][c] == n){
				return false;
			}
		}
		//starting row & column index for the 3X3 sub grid in which grid[i][j] is contained
		int s = (int) Math.sqrt(9);
		int rs = r - r%s;  
		int cs = c - c%s;

		for(int i = 0; i < s; i++){
			for(int j = 0; j < s; j++){
				if(grid[i+rs][j+cs] == n){
					return false;
				}
			}
		}
		return true;
	}
}
