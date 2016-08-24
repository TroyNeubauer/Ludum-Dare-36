package com.troy.ludumdare.util;

import java.util.*;

public class StressTest implements Runnable{
	
	private static volatile boolean doingTest = false;
	
	public static void stress(int threadCount){
		doingTest = true;
		for(int i = 0; i < threadCount; i++){
			Thread thread = new Thread(new StressTest(), "Test-Thread-" + i);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
	}
	
	public static void endTest(){
		doingTest = false;
	}

	@Override
	public void run() {
		Random random = new Random();
		int a = Integer.MIN_VALUE + 10;
		while(StressTest.doingTest){
			for(int i = 0; i < 100; i++){
				a += i * random.nextInt(2);
			}
		}
	}

}
