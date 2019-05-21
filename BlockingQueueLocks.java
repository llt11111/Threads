package blockingqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockingQueueClassLocks{
	List<Integer> queue = new LinkedList<Integer>();
	int size = 5;
	Lock lock = new ReentrantLock();
	Condition isFull = lock.newCondition();
	Condition isEmpty = lock.newCondition();
	Object obj = new Object();
	public void add(int num){
		lock.lock();
		while(queue.size() == size) {
			try {
				isFull.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (obj) {
			queue.add(num);
			System.out.println("added   : "+ num + " size : "+ queue.size());
		}
		isEmpty.signalAll();
		lock.unlock();
	}
	public void remove(){
		lock.lock();
		while(queue.size() == 0) {
			try {
				isEmpty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (obj) {
			int x = queue.remove(0);
			System.out.println("removed : "+ x + " size : "+ queue.size());
		}
		isFull.signalAll();
		lock.unlock();
	}
}
public class BlockingQueueLocks {
	public static void main(String[] args) {
		BlockingQueueClassLocks bk = new BlockingQueueClassLocks();
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
