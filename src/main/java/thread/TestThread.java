package thread;

import java.util.Scanner;

public class TestThread {
	public static void main(String[] args) {
		Thread t1=new Thread1();
		Runnable r1=new Runnable1();	
		Thread t2=new Thread(r1);
		Thread t3=new Thread() {
			public void run() {
				// get current thread for the method run()
				Thread t3=Thread.currentThread();
				// joint(): run after thread t2 ends
				try {
					t2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// informations about thread
				System.out.println(t3.getName()+" "+t3.getId());
				System.out.println(t3.isAlive()+" "+t3.isInterrupted()+
				" "+t3.isDaemon());
			}
		};
		
		// set priority 
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);	
		t3.setPriority(Thread.MAX_PRIORITY);
		
		// start thread, then call run()
		t1.start();
		t2.start();		
		t3.start();
		TestThread obj=new TestThread();
		Thread t4=new Thread() {
			public void run()  {
				obj.Timer();
			}
		};
		Thread t5=new Thread() {
			public void run() {
				obj.Timer();
			}
		};
		t4.start();
		t5.start();
	} 
	
	/**
	 * Thread.sleep(int): sleep for int ms
	 * synchronized: run one thread each time
	 * @param time
	 */
	//public synchronized void Timer()
	public void Timer() {	
		Scanner scanner=new Scanner(System.in);
		int time=0;
		//synchronize block, this: same object, exclude other thread and blocks
		synchronized (this) {
			System.out.println("Please enter a number:");
			time=Integer.parseInt(scanner.nextLine());
		}
		synchronized (this) {
			while (time>0) {
				try {
					Thread.sleep(1000);
					System.out.println("current time:"+time--);
				} catch (InterruptedException e) {// interrupt the sleep
					e.printStackTrace();
				}				
			}
			System.out.println("timer ends");
		}	
	}
}

/**
 * first way: inherit Thread and overwrite run method
 */
	class Thread1 extends Thread{
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("Thread 1");
			}
		}
	}
	/**
	 * second way: implement runnable with task, then construct Thread
	 */
	class Runnable1 implements Runnable{
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("Thread 2");
			}
		}
	}
