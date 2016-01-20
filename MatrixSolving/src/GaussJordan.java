
public class GaussJordan {
	  
	  /*
	   * Uses the Gaussian elimination to reduce matrixB to row echelon form
	   * 
	   * PRE: matrixA != null
	   */
	  public double[][] reduce(double[][] matrixA){
	    double[][] matrixB = new double[matrixA.length][matrixA[0].length];
	    
	    for (int i = 0; i < matrixA.length;i++) {
	    	for (int j = 0; j < matrixA[i].length;j++) {
	    		matrixB[i][j] = matrixA[i][j];
	    	}
	    }
	    int[] temp = {0,0};
	    
	    while (temp[0] < matrixB.length) {
	    
	    	simplifyRow(matrixB, temp[0], temp[1]);
	    
	    	zeroBelow(matrixB, temp[0], temp[1]);
	    
	    	temp[0]++;
	    	temp[1]++;
	    }
	    
	    temp[0] = 1;
	    temp[1] = 1;
	    
	    while (temp[0] < matrixB.length) {
		    
		    zeroAbove(matrixB, temp[0], temp[1]);
		    
		    temp[0]++;
		    temp[1]++;
	    }
	    
	    return matrixB;
	  }
	  
	  /*
	   * simplifyRow makes the first non zero number in the row one by multiplying
	   * the entire row with multiplicative inverse of the rows first non zero number
	   * according to the Gauss-Jordan -method
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
	     * zeros
	     * */
	  private static void zeroBelow (double[][] a,int row, int column) {
	    
//	    iterates through rows below the simplified row
	    for (int i = row + 1; i < a.length; i++) {
	      
//	      iterates through items in row beginning from the defined column
	      for (int j = a[i].length-1; j >= column; j--) {
	        
//	        adds to the chosen item 
	        a[i][j] += ((-1)*a[i][column]/*the first non zero item in defined row*/)*(a[row][j]);
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
	  
	  
	  public void printMatrix(double[][] m) {
	    for (int i = 0; i < m.length; i++){
	      for (int j = 0; j < m[i].length; j++){
	        System.out.print(m[i][j] + "\t");
	      }
	      System.out.println();
	    }
	  }
	
}
