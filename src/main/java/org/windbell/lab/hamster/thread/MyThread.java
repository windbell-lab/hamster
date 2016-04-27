package org.windbell.lab.hamster.thread;

public class MyThread extends Thread {
	private String name;
	
	public MyThread() {
		super();
	}

	public MyThread(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(" thread name :" + Thread.currentThread().getName() + " thread id is :"
				+ Thread.currentThread().getId() + " is runnig~" + " name is " + name);
	}
}
