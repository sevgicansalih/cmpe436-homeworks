/*
 * @author Salih Sevgican
 * 2013400219
 */
package cmpe436hw3_1_2;

public class main {
	/*
	 * Threads starts and 
	 */
	static Semaphore mutex = new Semaphore (1);
	static int x = 0;
	static boolean flag = true;
	public static void main(String[] args) {
		Thread t1 = new MyThread(12);
		Thread t2 = new MyThread(44);
		t1.start();t2.start();
		
		System.out.println("Later on value is : " + x);
	}

}
