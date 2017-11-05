/*
 * @author Salih Sevgican
 * 2013400219
 */
package cmpe436hw3_1_1;

import java.util.ArrayList;

public class main {
	static int digit = 1;
	static ArrayList<Semaphore> locksheld = new ArrayList<Semaphore>();
	static ArrayList<Semaphore> c = new ArrayList<Semaphore>();
	static ArrayList<Semaphore> set = new ArrayList<Semaphore>();
	static Semaphore mutex = new Semaphore(1,99);
	static boolean flag = true;
	public static void main(String[] args) {
		for(int i = 1;i<4;i++) {
			set.add(new Semaphore(1,i));
		}
		
		for(int i = 1;i<4;i++) {
			c.add(set.get(i-1));
		}
		System.out.println("Control :" + (set.contains(c.get(0))));
		
		Thread t1 = new MyThread(digit,0);
		Thread t2 = new MyThread(digit,1);
		t1.start();
		t2.start();
		System.out.println("No race is detected.");
		
	}

}
