package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SharedQueue1{
	BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
	
	public void add(int e) {
		// TODO Auto-generated method stub
		try {
			queue.put(e);
			System.out.println("added "+ e + " size : "+ queue.size());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	public void remove() {
		try {
			int e = queue.take();
			System.out.println("removed  "+ e + " size : "+ queue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
public class ProductConsumerBlockingQueueTest {
	
public static void main(String[] args) {
	SharedQueue1 queue = new SharedQueue1();
	Thread t1 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i = 0 ; i < 200 ; i++) {
				queue.add(i);
				//					System.out.println("added : "+ i + " queue.size : "+ queue.queue.size());
			}
		}
	} );

	Thread t2 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i = 0 ; i < 200 ; i++) {
				queue.remove();
				//					System.out.println("removed : "+ i+ " queue.size : "+ queue.queue.size());
			}
		}
	} );
	t1.start();
	t2.start();
}
}
