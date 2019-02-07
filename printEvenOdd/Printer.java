package printEvenOdd;

public class Printer {
	volatile boolean isEven = false;
	Object o1 = new Object();
	
	public synchronized void printEven(int count){
		while(isEven == false){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "   " + count);
		isEven = false;
		notify();
	}
	public synchronized void printOdd(int count){
		while(isEven == true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "   " + count);
		isEven = true;
		notify();
	}
}
