package app;
import java.lang.*; 

public class PuzzleGenerator {
	private int[] grid[]; 
    private int N; // number of columns/rows. 
    private int SQRTN; // square root of N 
    private int K; // No. Of missing digits 
  
    // Constructor 
    PuzzleGenerator(int N, int K) 
    { 
        this.N = N; 
        this.K = K; 
  
        // Compute square root of N 
        Double SQRTD = Math.sqrt(N); 
        SQRTN = 3; 
  
        grid = new int[N][N]; 
    } 
    public int[][] getPuzzle(){
    	return this.grid;
    }
    // Sudoku Generator 
    public void fillValues() 
    { 
        // Fill the diagonal of SRN x SRN matrices 
        fillDiagonal(); 
  
        // Fill remaining blocks 
        fillRemaining(0, SQRTN); 
        
        // Remove Randomly K digits to make game 
        removeKDigits(); 
    } 
   
    // Fill the diagonal SRN number of SRN x SRN matrices 
    void fillDiagonal() 
    { 
  
        for (int i = 0; i<N; i=i+SQRTN) 
  
            // for diagonal box, start coordinates->i==j 
            fillBox(i, i); 
    } 
  
    // Returns false if given 3 x 3 block contains num. 
    boolean unUsedInBox(int rowStart, int colStart, int num) 
    { 
        for (int i = 0; i<SQRTN; i++) 
            for (int j = 0; j<SQRTN; j++) 
                if (grid[rowStart+i][colStart+j]==num) 
                    return false; 
  
        return true; 
    } 
  
    // Fill a 3 x 3 matrix. 
    void fillBox(int row,int col) 
    { 
        int num; 
        for (int i=0; i<SQRTN; i++) 
        { 
            for (int j=0; j<SQRTN; j++) 
            { 
                do
                { 
                    num = randomGenerator(N); 
                } 
                while (!unUsedInBox(row, col, num)); 
  
                grid[row+i][col+j] = num; 
            } 
        } 
    } 
  
    // Random generator 
    int randomGenerator(int num) 
    { 
        return (int) Math.floor((Math.random()*num+1)); 
    } 
  
    // Check if safe to put in cell 
    boolean CheckIfSafe(int i,int j,int num) 
    { 
        return (unUsedInRow(i, num) && 
                unUsedInCol(j, num) && 
                unUsedInBox(i-i%SQRTN, j-j%SQRTN, num)); 
    } 
  
    // check in the row for existence 
    boolean unUsedInRow(int i,int num) 
    { 
        for (int j = 0; j<N; j++) 
           if (grid[i][j] == num) 
                return false; 
        return true; 
    } 
  
    // check in the row for existence 
    boolean unUsedInCol(int j,int num) 
    { 
        for (int i = 0; i<N; i++) 
            if (grid[i][j] == num) 
                return false; 
        return true; 
    } 
  
    // A recursive function to fill remaining  
    // matrix 
    boolean fillRemaining(int i, int j) 
    { 
        //  System.out.println(i+" "+j); 
        if (j>=N && i<N-1) 
        { 
            i = i + 1; 
            j = 0; 
        } 
        if (i>=N && j>=N) 
            return true; 
  
        if (i < SQRTN) 
        { 
            if (j < SQRTN) 
                j = SQRTN; 
        } 
        else if (i < N-SQRTN) 
        { 
            if (j==(int)(i/SQRTN)*SQRTN) 
                j =  j + SQRTN; 
        } 
        else
        { 
            if (j == N-SQRTN) 
            { 
                i = i + 1; 
                j = 0; 
                if (i>=N) 
                    return true; 
            } 
        } 
  
        for (int num = 1; num<=N; num++) 
        { 
            if (CheckIfSafe(i, j, num)) 
            { 
            	grid[i][j] = num; 
                if (fillRemaining(i, j+1)) 
                    return true; 
  
                grid[i][j] = 0; 
            } 
        } 
        return false; 
    } 
  
    // Remove the K no. of digits to 
    // complete game 
    public void removeKDigits() 
    { 
        int count = K; 
        while (count != 0) 
        { 
            int cellId = randomGenerator(N*N); 
  
            // System.out.println(cellId); 
            // extract coordinates i  and j 
            int i = (cellId/N); 
            int j = cellId%9; 
            if (j != 0) 
                j = j - 1; 
  
            // System.out.println(i+" "+j); 
            if (grid[i][j] != 0) 
            { 
                count--; 
                grid[i][j] = 0; 
            } 
        } 
    } 
    
}
