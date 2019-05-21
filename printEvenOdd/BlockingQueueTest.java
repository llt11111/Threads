package blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;;

class sharedQueue{
	LinkedList<Integer> queue = new LinkedList<Integer>();

	Semaphore empty = new Semaphore(5);
	Semaphore full = new Semaphore(0);
	Object lock = new Object();
	public void add(int number) {
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (lock) {
			queue.addFirst(number);
		}
		
		System.out.println("added : " + number+ " size : "+ queue.size());
		full.release();
	}

	public void remove() {
		int x;
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (lock) {
			x = queue.removeLast();
		}
		
		System.out.println("added : " + x+ " size : "+ queue.size());
		empty.release();
	}
}
public class BlockingQueueTest {
	public static void main(String[] args) {
		sharedQueue queue = new sharedQueue();
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
