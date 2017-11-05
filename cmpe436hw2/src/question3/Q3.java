package question3;

public class Q3 {
	
	static int counter = 0;
	
	public static void main(String[] args) {
		
		ThreadOne one = new ThreadOne();
		ThreadTwo two = new ThreadTwo();
		ThreadThree three = new ThreadThree();
		one.start();
		two.start();
		three.start();
		System.out.println("Expected value is 3, real value is : "+counter);
		System.out.println("Race Condition...");
	}
	private static class ThreadOne extends Thread{
		@Override
		public void run(){
			int tmp = counter;
			tmp++;
			counter=tmp;
		}
	}
	private static class ThreadTwo extends Thread{
		@Override
		public void run(){
			int tmp = counter;
			tmp++;
			counter=tmp;
		}
	}
	private static class ThreadThree extends Thread{
		@Override
		public void run(){
			int tmp = counter;
			tmp++;
			counter=tmp;
		}
	}
	

}
