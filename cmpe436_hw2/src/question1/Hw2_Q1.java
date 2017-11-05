package question1;

import java.util.Scanner;

/**
 * @author Salih Sevgican 2013400219	
 * sevgicansalih@gmail.com	
 * CmpE436 Assignment 2
 * 
 * Question2
 */

public class Hw2_Q1 {

	/**
	 * @param args
	 * 
	 * Takes row, column number and #ofMaxGenerations as argument
	 * creates a Matrix object by using Matrix Class methods(create matrix)
	 * then put into simulation
	 */
	static int numthread;
	static Matrix initial;
	static boolean correct = true;
	static MyThread[] tr;
	static Grid[] grids;
	static int numGen,rows,columns;
	static int count=0;
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		//System.out.println("Enter #Rows,#Columns and #of maxGenerations");
		
		try{
			rows = Integer.parseInt(args[0]);
			columns = Integer.parseInt(args[1]);
			numGen = Integer.parseInt(args[2]);
			//rows = scan.nextInt();
			//columns = scan.nextInt();
			//numGen = scan.nextInt();
			numthread = rows*columns;
		}catch(NumberFormatException e){
			correct=false;
			System.out.println("Argument is not proper!");
		}
		
		/**
		 * Matrix initialazition
		 */
		tr = new MyThread[numthread];
		grids = new Grid[numthread];
		initial = new Matrix(rows+2, columns+2);
		
		initial = Matrix.createMatrix(args);
		System.out.println("Generation # : 0");
		initial.printMatrix();
		/*
		 * semaphore initialized semRead is initialized with 0 in order to barrier first segment.
		 */
		Semaphore mutex = new Semaphore(1);
		Semaphore semRead = new Semaphore(0);
		Semaphore semWrite = new Semaphore(1);
		
		/*
		 * threads initialized
		 */
		for(int j = 0;j<numthread;j++){
			tr[j] = new MyThread(semRead,semWrite,mutex,j);
		}
		/**
		 * thread array runs
		 */
		for(int j =0;j<numthread;j++){
			tr[j].start();
		}
		int count = 0;
		
	
	}

}
