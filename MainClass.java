package com.test;
/**
 * This program prints a series of numbers using multiple threads.
 * Numbers are printed by threads sequentially in round-robin format.
 * 
*/
public class MainClass {

	/* 
		Main method which creates printer objects and thread objects.
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		int iMaxNumber  = 100;
		int totalNumberOFThreads =4;
		objectPrinter obeObjectPrinter = new objectPrinter(iMaxNumber, totalNumberOFThreads);

		for(int i = 1 ;i <= totalNumberOFThreads; i++){
			ThreadObject TH = new ThreadObject(i==totalNumberOFThreads ? 0 : i,obeObjectPrinter);
			Thread thread = new Thread(TH);
			thread.start();
		}
	}

}

class objectPrinter{

	int iMaxNumber;
	volatile int i = 1;
	int TotalNumberOFThreads;


	public objectPrinter(int iMaxNumber, int totalNumberOFThreads) {
		super();
		this.iMaxNumber = iMaxNumber;
		TotalNumberOFThreads = totalNumberOFThreads;
	}


	synchronized void printNumber(int iCurrentThread){
		while(isPrintNumber()){
			if(i%TotalNumberOFThreads == iCurrentThread){
				System.out.println("Thread => "+ iCurrentThread  + " | Value=> " + i++);
				notifyAll();
			}else{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	boolean isPrintNumber(){
		return iMaxNumber >= i;
	}
}

class ThreadObject implements Runnable{
	int iThreadNUmber;
	objectPrinter objectPrinter;

	public ThreadObject(int iThreadNUmber, com.test.objectPrinter objectPrinter) {
		super();
		this.iThreadNUmber = iThreadNUmber;
		this.objectPrinter = objectPrinter;
	}

	public void run(){
	//	while(objectPrinter.isPrintNumber())
			objectPrinter.printNumber(iThreadNUmber);
	}
}
