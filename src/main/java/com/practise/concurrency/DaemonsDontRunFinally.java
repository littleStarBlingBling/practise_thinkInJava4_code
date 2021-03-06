package com.practise.concurrency;

import java.util.concurrent.TimeUnit;

class ADaemon implements Runnable {
	@Override
	public void run() {
		try {

			System.out.println("Start ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);
		thread.start();
	}
}
