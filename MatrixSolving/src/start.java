public class start {

	public static void main(String[] args) {
		
			    double[][] matriisi = new double[][] { 
			      {8,7,6,2},
			      {4,8,5,7},
			      {9,7,3,5}
			    };
			    
			    GaussJordan reduct = new GaussJordan();
			    reduct.printMatrix(reduct.reduce(matriisi));
			    
			    matriisi = new double[][] { 
				  {8,7,6,2},
				  {4,8,5,7},
				  {9,7,3,5}
				};  
			    reduct.printMatrix(reduct.reduce(matriisi));
	}

}