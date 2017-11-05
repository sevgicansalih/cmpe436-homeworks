package cmpe436hw3_1_2;

public class MyThread extends Thread {
	int id;
	public MyThread(int y) {
		id = y;
	}

	@Override
	public void run() {
		Semaphore sem = main.mutex;
		sem.P();
		main.x = id;
		sem.V();
		if(main.flag)
			System.out.println(main.x);
	}
}
