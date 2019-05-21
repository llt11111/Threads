package blockingqueue;

import java.util.LinkedList;
import java.util.List;

class BlockingQueueClass{
	List<Integer> queue = new LinkedList<Integer>();
	int size = 5;
	boolean isFull = false;
	boolean isEmpty = true;

	public synchronized void add(int num){
		while(isFull) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.add(num);
		isEmpty = false;
		if(queue.size() == size) {
			isFull = true;
		}
		System.out.println("added   : "+ num + " size : "+ queue.size());
		notifyAll();
	}
	public synchronized void remove(){
		while(isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int x = queue.remove(0);
		isFull = false;
		if(queue.size() == 0) {
			isEmpty = true;
		}
		System.out.println("removed : "+ x + " size : "+ queue.size());
		notify();

	}
}
public class BlockingQueueWaitNotify {
	public static void main(String[] args) {
		BlockingQueueClass bk = new BlockingQueueClass();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0 ; i < 10 ; i++) {
					bk.add(i);
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0 ; i < 20 ; i++) {
					bk.remove();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0 ; i < 10 ; i++) {
					bk.add(i*2);
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}
}
