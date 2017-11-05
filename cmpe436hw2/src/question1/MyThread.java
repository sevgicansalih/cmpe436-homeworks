package question1;

public class MyThread extends Thread {
	
	public static final int GRID_ROWS = 3;
	public static final int GRID_COLUMNS = 3;
	public static final int RES_ROW = 1;
	public static final int RES_COLUMN = 1;
	Grid threadmat;
	Grid threadresult;
	Semaphore semRead;
	Semaphore semWrite;
	Semaphore mutex;
	int threadID;
	/*
	 * Objects created with 3 semaphores but at first object creation a grid has been assigned to the thread
	 */
	public MyThread(Grid grid, Semaphore semRead, Semaphore semWrite,Semaphore mutex,int threadID) {
		threadmat = new Grid();
		threadresult = new Grid();
		threadmat = grid;
		this.semRead = semRead;
		this.semWrite = semWrite;
		this.threadID=threadID;
		this.mutex=mutex;
	}

	public MyThread(Semaphore semRead, Semaphore semWrite,Semaphore mutex,int threadID) {
		threadmat = new Grid();
		threadresult = new Grid();
		this.semRead = semRead;
		this.semWrite = semWrite;
		this.threadID=threadID;
		this.mutex=mutex;
		
	}

	@Override
	public void run() {
		
		/*
		 * runs until equals to number of generations
		 */
		if (!Hw2_Q1.initial.flag && Hw2_Q1.correct) {
			for (int i = 0; i < Hw2_Q1.numGen; i++) {
				//sem.setPrintedBefore (false);
				//System.out.println("numGen : "+Hw2_Q2.numGen);
				/*
				 * first barrier locks write part unlocks read part.
				 */
				mutex.P();
				Hw2_Q1.count+=1;
				if(Hw2_Q1.count == Hw2_Q1.numthread){
					semWrite.P();
					semRead.V();
				}
				mutex.V();
				/*
				 * grids of new matrices are generated in order to pass them to threads
				 */
				semRead.P();
				//System.out.println("id: "+threadID);
				Hw2_Q1.initial.nextGen(Hw2_Q1.grids,threadID);
				semRead.V();
				/*
				 * all threads have their grids now,reading
				 */
				semRead.P();
				for (int j = 0; j < Hw2_Q1.numthread; j++) {
					Hw2_Q1.tr[j].setThreadmat(Hw2_Q1.grids[j]);
				}
				semRead.V();
				/*
				 * threads start.and writes on its own grid
				 */
				threadmat.nextGenMiddle(threadresult);
				
				/*
				 * barrier 2 locks read part and unlocks write part
				 */
				mutex.P();
				Hw2_Q1.count-=1;
				if(Hw2_Q1.count==0){
					semRead.P();
					semWrite.V();
				}
				mutex.V();
				/*
				 * threads starts writing to main matrix: initial
				 */
				semWrite.P();
				Hw2_Q1.initial.mat[threadmat.location_row][threadmat.location_columns] = threadresult.gr[RES_ROW][RES_COLUMN];
				semWrite.V();
				/*
				 * one thread should print out the matrix after initial matrix has been overriden.
				 */
				mutex.P();
				Hw2_Q1.count+=1;
				if(Hw2_Q1.count==Hw2_Q1.numthread){
					System.out.println("Generation # : " + (i + 1));
					Hw2_Q1.initial.printMatrix();
					Hw2_Q1.count=0;
				}
				mutex.V();
				
				
			}
		
		}

	}

	public Grid getThreadmat() {
		return threadmat;
	}

	public void setThreadmat(Grid threadmat) {
		this.threadmat = threadmat;
	}

}
