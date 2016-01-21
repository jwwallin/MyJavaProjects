
public class GaussJordan {
	  
	  /*
	   * Uses the Gaussian elimination to reduce matrixB to row echelon form without making changes to the original matrix
	   * 
	   * PRE: matrixA != null
	   */
	  public double[][] reduce(double[][] matrixA){
//		copy the matrix into a new matrix
	    double[][] matrixB = new double[matrixA.length][matrixA[0].length];
	    
	    for (int i = 0; i < matrixA.length;i++) {
	    	for (int j = 0; j < matrixA[i].length;j++) {
	    		matrixB[i][j] = matrixA[i][j];
	    	}
	    }
//		this is used for moving through the matrix diagonally
	    int[] temp = {0,0};
	    
	    while (temp[0] < matrixB.length) {
//		this performes row operation of type 2
	    	simplifyRow(matrixB, temp[0], temp[1]);
//		this performes row operation of type 3 for all rows below using the current row
	    	zeroBelow(matrixB, temp[0], temp[1]);
	    
	    	temp[0]++;
	    	temp[1]++;
	    }
	    
	    temp[0] = 1;
	    temp[1] = 1;
	    
	    while (temp[0] < matrixB.length) {
		    
//		this performes row operation of type 3 for all rows above using the current row
		zeroAbove(matrixB, temp[0], temp[1]);
		    
		temp[0]++;
		temp[1]++;
	    }
	    
	    return matrixB;
	  }
	  
	  /*
	   * simplifyRow makes the first non zero number in the row one by multiplying
	   * the entire row with multiplicative inverse of the rows first non zero number
	   * according to the Gaussian elimination row operation of type 2
	   */
	  private static void simplifyRow (double[][] a, int row, int column) {
	    
//	    set multiplier for row simplification equal to multiplicative inverse of first non zero number 
	    double mult = 1.0/a[row][column];
	    
//	    multiply every number in row with the multiplier
	    for (int i = 0; i < a[row].length; i++) {
	      a[row][i] *= mult;
	    }
	  }
	  
	  
	    /*
	     * zeroBelow makes all the numbers below a simplified rows first number (=1)
	     * zeros using Gaussian elimination row operation of type 3
	     * */
	  private static void zeroBelow (double[][] a,int row, int column) {
	    
//	    iterates through rows below the simplified row
	    for (int i = row + 1; i < a.length; i++) {
	      
//	      iterates through items in row beginning from the end until defined column
	      for (int j = a[i].length-1; j >= column; j--) {
	        
//	        adds to the chosen item 
	        a[i][j] += ((-1)*a[i][column])*(a[row][j]);
	      }
	    }
	  }
	  
	  /*
	   * 
	   */
	  private static void zeroAbove (double[][] a, int row, int column) {

//		  iterates through rows above the simplified row
		  for (int i = row - 1; i >= 0; i--) {
		      
//		      iterates through items in row beginning from the end until defined column
			  for (int j = a[i].length-1; j >= column; j--) {
		        
//		        adds to the chosen item 
		        a[i][j] += ((-1)*a[i][column])*(a[row][j]);
		        }
			  
			  }
	  }
	
	/*
	 * Can be used to print out a matrix to a command line window
	 * 
	 * PRE: m != null
	*/
	public void printMatrix(double[][] m) {
	    for (int i = 0; i < m.length; i++){
	      for (int j = 0; j < m[i].length; j++){
	        System.out.print(m[i][j] + "\t");
	      }
	      System.out.println();
	    }
	}
	
}
