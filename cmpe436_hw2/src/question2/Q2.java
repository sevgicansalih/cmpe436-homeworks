package question2;

public class Q2 {
	static int value=0;
	/*
	 * Creates 3 threads and runs them
	 */
	public static void main(String[] args) {
		Semaphore sem1 = new Semaphore(1);
		Semaphore sem2 = new Semaphore(1);
		MyThread tr[]=new MyThread[3];
		for(int i =1;i<=3;i++){
			tr[i-1]= new MyThread(sem1,sem2,i);
			tr[i-1].start();
		}
		
	}

}
