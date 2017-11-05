package question1;

public class Grid {
	int grid_rows;
	int grid_columns;
	int gr[][];
	int location_row;
	int location_columns;
	public Grid(){
		grid_rows = 3;
		grid_columns = 3;
		gr = new int[grid_rows][grid_columns];
		for (int i = 0; i < grid_rows; i++)
			for (int j = 0; j < grid_columns; j++)
				gr[i][j] = 0;
	}
	

	/**
	 * Prints Grid to the CONSOLE
	 */
	public void printGrid() {

		for (int i = 0; i < grid_rows; i++) {
			for (int j = 0; j < grid_rows; j++) {
				System.out.print(gr[i][j] + " ");
			}
			System.out.println("");
		}

	}
	public void calculateGrid(int[][] matrix,int i, int j) {
		
		location_row=i;
		location_columns=j;
		//System.out.println("matrix len: "+matrix.length+" : "+matrix[0].length);
		//System.out.println("grid len: "+gr.length+" : "+gr[0].length);
		//System.out.println("locations are :"+i+" : "+j);
		
		gr[0][0] = matrix[i - 1][j - 1];
		gr[0][1] = matrix[i - 1][j];
		gr[0][2] = matrix[i - 1][j + 1];
		gr[1][0] = matrix[i][j - 1];
		gr[1][1] = matrix[i][j];
		gr[1][2] = matrix[i][j + 1];
		gr[2][0] = matrix[i + 1][j - 1];
		gr[2][1] = matrix[i + 1][j];
		gr[2][2] = matrix[i + 1][j + 1];
		

	}


	public int[][] getGr() {
		return gr;
	}


	public void setGr(int[][] gr) {
		this.gr = gr;
	}
	public void nextGenMiddle(Grid result) {
		int sumNeighbours = 0;
		/**
		 * basically calculate sum of neighbours, matrix validity has been
		 * checked before no need to worry about corrupted matrices if
		 * conditions for game rules.
		 */
		for (int i = 1; i < (grid_rows - 1); i++) {
			for (int j = 1; j < (grid_columns - 1); j++) {
				sumNeighbours = gr[i - 1][j] + gr[i + 1][j] + gr[i][j - 1]
						+ gr[i][j + 1] + gr[i - 1][j - 1] + gr[i - 1][j + 1]
						+ gr[i + 1][j - 1] + gr[i + 1][j + 1];

				if (gr[i][j] == 1) {
					if (sumNeighbours < 4 && sumNeighbours > 1) {
						result.gr[i][j] = 1;
					} else {
						result.gr[i][j] = 0;

					}
				} else if (gr[i][j] == 0) {
					if (sumNeighbours == 3)
						result.gr[i][j] = 1;
					else
						result.gr[i][j] = 0;
				} else
					System.out.println("Wrong input");

			}
		}
	}
}
