package printEvenOdd;

public class App1 {
	
	public static void main(String[] args) {
		Printer p = new Printer();
		Thread evenThreads = new Thread(new Runnable() {
			
			int count = 2 ; 
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < 10 ; i++){
					p.printEven(count);
					count += 2;
				}
			}
		});
		
		Thread oddThread = new Thread(new Runnable() {
			int count = 1 ; 
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < 10 ; i++){
					p.printOdd(count);
					count += 2;
				}
			}
		});
		
		evenThreads.start();
		oddThread.start();
	
	}
}
