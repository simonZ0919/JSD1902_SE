package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thread pool:limit thread numbers, reuse thread
 * @author zxh
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		// create a thread pool
		ExecutorService threadPool=Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			Runnable run=new Runnable() {
				public void run() {
					Thread thread=Thread.currentThread();
					try {
						String name=thread.getName();
						System.out.println(name+" is running");
						Thread.sleep(3000);
						System.out.println(name+" is finished");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			// assign a task to thread pool
			threadPool.execute(run);
		}
		
		/** shutdown():stop the thread pool after all tasks finish
		*	shutdownNow(): force to stop thread pool now
		*/
		threadPool.shutdown();
		//threadPool.shutdownNow();
	}
}
