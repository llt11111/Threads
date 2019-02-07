package printEvenOdd;

class TaskEvenOdd implements Runnable{
	int max;
	Printer print;
	boolean isEvenNumber;
	
	public TaskEvenOdd(Printer print, int max, boolean isEvenNumber) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int number = (isEvenNumber == true) ? 2 : 1;
		while(number <= max){
			if(isEvenNumber){
				print.printEven(number);
			}else{
				print.printOdd(number);
			}
			number += 2;
		}
	}
}
public class App2 {
public static void main(String[] args) {
	Printer print = new Printer();
	Thread t1 = new Thread(new TaskEvenOdd(print, 10, true));
	Thread t2 = new Thread(new TaskEvenOdd(print, 10, false));
	
	
	t1.start();
	
	t2.start();
	
	
}
}
