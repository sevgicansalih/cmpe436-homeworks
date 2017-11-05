package cmpe436hw3_1_1;

import java.util.ArrayList;

public class MyThread extends Thread {
	int val;
	int id;
	ArrayList<Semaphore> locksheld = new ArrayList<Semaphore>();
	ArrayList<Semaphore> temp = new ArrayList<Semaphore>();


	public MyThread(int x, int id) {
		val = x;
		this.id = id;

	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * If you want to see race detection example, use lock1 and unlock1, o/w use lock2 and unlock2.
	 */
	@Override
	public void run() {
		System.out.println("Thread: " + id + " is running");
		lock1();
		//lock2();
		main.mutex.P();
		temp = check(main.c, locksheld);
		main.c = temp;
		main.mutex.V();
		val++;
		// System.out.println(val);
		unlock1();
		//unlock2();
	}

	/*
	 * lock1 and lock2 takes different locks which results to an empty C(v) thus
	 * race is detected
	 */
	public void lock1() {
		if (id == 0) {
			main.set.get(id).P();// 0
			main.set.get(id + 1).P();// 1
			locksheld.add(main.set.get(id));
			locksheld.add(main.set.get(id + 1));

		} else if (id == 1) {
			main.set.get(id + 1).P();// 2
			locksheld.add(main.set.get(id + 1));
		}
	}

	public void unlock1() {
		if (id == 0) {
			main.set.get(id).V();// 0
			main.set.get(id + 1).V();// 1
			locksheld.clear();
		} else if (id == 1) {
			main.set.get(id + 1).V();// 2
			locksheld.clear();
		}
	}

	/*
	 * lock2 and unlock2 share a common mutex. Therefore C(v) will not be empty, no
	 * race is detected.
	 */
	public void lock2() {
		if (id == 0) {
			main.set.get(id).P();
			main.set.get(id + 1).P();
			locksheld.add(main.set.get(id));
			locksheld.add(main.set.get(id + 1));
		} else if (id == 1) {
			main.set.get(id).P();
			main.set.get(id + 1).P();
			locksheld.add(main.set.get(id));
			locksheld.add(main.set.get(id + 1));
		}
	}

	public void unlock2() {
		if (id == 0) {
			main.set.get(id).V();
			main.set.get(id + 1).V();
			locksheld.clear();
		} else if (id == 1) {
			main.set.get(id).V();
			main.set.get(id + 1).V();
			locksheld.clear();
		}
	}

	public void printList(ArrayList<Semaphore> c) {
		int size = c.size();
		System.out.println("I am thread : " + id);
		for (int i = 0; i < size; i++) {
			System.out.print(c.get(i).id + " ");
		}
		System.out.println();
	}
	/*
	 * Checks C(v) for intersections.
	 */
	public ArrayList<Semaphore> check(ArrayList<Semaphore> c, ArrayList<Semaphore> locksheld) {
		ArrayList<Semaphore> res = new ArrayList<Semaphore>();
		/*
		 * get intersection of C(v) and locksheld lists, and update C(v)
		 */

		//printList(c);

		for (Semaphore t : c) {
			if (locksheld.contains(t)) {
				res.add(t);
			}
		}
		/*
		 * int size = c.size(); for(int i = 0;i<size;i++) {
		 * if(locksheld.get(i).id==c.get(i).id) res.add(c.get(i)); }
		 */
		//System.out.println("res");
		//printList(res);
		//System.out.println(res.isEmpty());

		// System.out.println("sem id "+res.get(1).id+" my id: "+id);
		if (res.isEmpty() && main.flag) {
			main.flag = false;
			System.out.println("Race detected");
			System.exit(1);
		}
		return res;
	}
}
