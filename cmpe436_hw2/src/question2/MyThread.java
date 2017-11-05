package question2;

public class MyThread extends Thread{
	
	int ID;
	
	Semaphore sem1;
	Semaphore sem2;
	/*
	 * object takes 2 semaphores and its own id derived by main class.
	 */
	public MyThread(Semaphore sem1,Semaphore sem2,int ID){
		this.ID=ID;
		
		this.sem1=sem1;
		this.sem2=sem2;
	}
	
	@Override
	public void run(){
			
		if(ID==1){
			sem1.P();
			System.out.println(ID+" sem1 locked , waiting for lock 2");
			sem2.P();
			System.out.println(ID+" sem2 locked");
			sem2.V();
			sem1.V();
		}else if(ID==2){
			sem2.P();
			System.out.println(ID+" sem2 locked, waiting for lock 1");
			sem1.P();
			System.out.println(ID+" sem1 locked");
			sem1.V();
			sem2.V();
		}
		else{
			sem1.P();
			sem2.P();
			System.out.println(ID+" I got both of the locks");
			sem1.V();
			sem2.V();
		}
						
	}
}
