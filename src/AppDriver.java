import java.util.Scanner;

import javax.swing.SwingUtilities;

public class AppDriver {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//int grid[][] = new int[9][9];
		int[][] puzzle = {
				{3, 0, 6, 5, 0, 8, 4, 0, 2},
				{5, 2, 9, 1, 3, 4, 7, 6, 8},
				{4, 8, 7, 6, 2, 9, 0, 3, 1},
				{2, 6, 3, 4, 1, 5, 9, 8, 7},
				{9, 7, 4, 8, 6, 3, 1, 2, 5},
				{8, 5, 1, 7, 9, 2, 6, 4, 3},
				{1, 3, 8, 9, 4, 7, 2, 5, 6},
				{6, 9, 2, 3, 5, 1, 8, 7, 4},
				{7, 4, 5, 2, 8, 6, 3, 1, 9}
		};
		int j;
		int grid[][] = {
				{3, 0, 6, 5, 0, 8, 4, 0, 0},
				{5, 2, 0, 0, 0, 0, 0, 0, 0},
				{0, 8, 7, 0, 0, 0, 0, 3, 1},
				{0, 0, 3, 0, 1, 0, 0, 8, 0},
				{9, 0, 0, 8, 6, 3, 0, 0, 5},
				{0, 5, 0, 0, 9, 0, 6, 0, 0},
				{1, 3, 0, 0, 0, 0, 2, 5, 0},
				{0, 0, 0, 0, 0, 0, 0, 7, 4},
				{0, 0, 5, 2, 0, 6, 3, 0, 0}
		};
		/*for(int i = 0; i < 9; i++)
		{
			j=0;
			String ip = sc.nextLine();
			String[] arr = ip.split("\\s+");
			for(String x:arr){
				grid[i][j] = Integer.parseInt(x);
				puzzle[i][j] = Integer.parseInt(x);
				j++;
			}
			System.out.println();
		}*/
		
		 SudokuLogic.solveSudoku(grid);
		 printGrid(grid);
		 SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 new SudokuUI(puzzle,grid);
	         }
	      });
		
		System.out.println("App ran successfully!");
	}
	
	static void printGrid (int grid[][])
    {
       
        for(int i = 0; i < 9; i++)
	    {
	        for(int j = 0; j < 9; j++)
	        {
	        	System.out.print(grid[i][j] + " ");
	           
	        }
	        System.out.println();
	    }
	    
    }
}
