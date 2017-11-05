package question1;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Salih Sevgican 2013400219 sevgicansalih@gmail.com CmpE436 Assignment
 *         2
 * 
 *         Question1 Matrix Class
 * 
 * 
 */

public class Matrix {

	static int rows;
	static int columns;
	int[][] mat;
	static boolean flag;
	private static final int GRID_ROW = 3;
	private static final int GRID_COLUMN = 3;

	/**
	 * 
	 * @param r
	 *            rows
	 * @param c
	 *            columns Constructor
	 */
	public Matrix(int r, int c) {

		rows = r;
		// System.out.println(rows + "  this : "+r);
		columns = c;
		flag = false;
		// System.out.println(columns + "  this : "+c);
		mat = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				mat[i][j] = 0;

	}

	/**
	 * @param args
	 *            checks whether there is "input.txt" or not
	 * 
	 * @return created Matrix object
	 */
	@SuppressWarnings("resource")
	public static Matrix createMatrix(String[] args) {

		Matrix mtx = new Matrix(rows, columns);
		Scanner fileScan;
		int tmp = 0;

		
		if (args.length == 4) {
			File file = new File(args[3]);
			//File file = new File("input.txt");

			try {

				fileScan = new Scanner(file);
				for (int i = 1; i < rows - 1; i++) {
					for (int j = 1; j < columns - 1; j++) {
						tmp = fileScan.nextInt();
						/**
						 * Controls input validity
						 */
						if (tmp == 1 || tmp == 0)
							mtx.mat[i][j] = tmp;
						else {
							System.out.println("Wrong input !!");
							flag = true;
							System.exit(0);
						}
					}

				}

			} catch (IOException e) {
				System.out.println("Oops, something is wrong!");
			}
		} else {
			/*
			 * If there is no input.txt Random matrix is generated here.
			 */
			System.out.println("Random matrix will be generated.");
			Random rand = new Random();
			for (int i = 1; i < rows - 1; i++) {
				for (int j = 1; j < columns - 1; j++) {
					mtx.mat[i][j] = rand.nextInt(2);
				}
			}
		}

		return mtx;
	}

	/**
	 * Prints Matrix to the CONSOLE
	 */
	public void printMatrix() {

		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < columns - 1; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}

	}

	/**
	 * 
	 *        for every thread the grids of main matrix(initial) is calculated here.
	 */
	public void nextGen(Grid[] grids, int threadID) {
		int curRow = 0;
		int curCol = 0;
		curRow = (threadID / (columns-2)) + 1;
		curCol = (threadID % (columns-2)) + 1;
		//System.out.println("columns: "+columns);
		/*
		 * Thread is going to read its spesific part from Matrix.
		 * 
		 * grids which are going to be given to threads are prepared
		 */
		grids[threadID] = new Grid();
		grids[threadID].calculateGrid(mat, curRow, curCol);

		// nextGenMiddle(result);

	}

	/**
	 * 
	 * @param result
	 *            takes result Matrix and changes its middle part to next
	 *            generation No edges are calculated
	 */
	public void nextGenMiddle(Matrix result) {
		int sumNeighbours = 0;
		/**
		 * basically calculate sum of neighbours, matrix validity has been
		 * checked before no need to worry about corrupted matrices if
		 * conditions for game rules.
		 */
		for (int i = 1; i < (rows - 1); i++) {
			for (int j = 1; j < (columns - 1); j++) {
				sumNeighbours = mat[i - 1][j] + mat[i + 1][j] + mat[i][j - 1]
						+ mat[i][j + 1] + mat[i - 1][j - 1] + mat[i - 1][j + 1]
						+ mat[i + 1][j - 1] + mat[i + 1][j + 1];

				if (mat[i][j] == 1) {
					if (sumNeighbours < 4 && sumNeighbours > 1) {
						result.mat[i][j] = 1;
					} else {
						result.mat[i][j] = 0;

					}
				} else if (mat[i][j] == 0) {
					if (sumNeighbours == 3)
						result.mat[i][j] = 1;
					else
						result.mat[i][j] = 0;
				} else
					System.out.println("Wrong input");

			}
		}
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		Matrix.rows = rows;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		Matrix.columns = columns;
	}

}
