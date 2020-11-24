package app;

public class SudokuLogic {

	static boolean solveSudoku(int grid[][])
	{
		int i = 0, j = 0;
		for(i = 0; i < 9; i++)
		{
			boolean flag = false;
			for(j = 0; j < 9; j++)
			{
				if(grid[i][j] == 0)
				{
					flag = true;
					break;
				}
			}
			if(flag == true)
			{
				break;
			}
		}

		if(i == 9 && j == 9)
		{
			return true;
		}


		for(int n = 1; n <= 9; n++)
		{

			if(isSafe(grid, i, j, n) == true)
			{
				grid[i][j] = n;

				if(solveSudoku(grid) == true)
				{
					return true;
				}
				grid[i][j] = 0;
			}
		}

		return false;
	}
	static boolean isSafe(int grid[][], int r, int c, int n)
	{
		for(int k = 0; k < 9; k++)
		{
			if(grid[r][k] == n || grid[k][c] == n)
			{
				return false;
			}
		}

		int s = (int) Math.sqrt(9);
		int rs = r - r%s;
		int cs = c - c%s;

		for(int i = 0; i < s; i++)
		{
			for(int j = 0; j < s; j++)
			{
				if(grid[i+rs][j+cs] == n)
				{
					return false;
				}
			}
		}

		return true;
	}
}
