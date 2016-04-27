package org.windbell.lab.hamster.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		Thread t4 = new MyThread("t4");
		Thread t5 = new MyThread("t5");

		pool.execute(t2);
		pool.execute(t1);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		
		pool.shutdown();
	}

}
