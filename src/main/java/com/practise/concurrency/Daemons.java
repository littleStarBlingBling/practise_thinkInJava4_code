package com.practise.concurrency;


import java.util.concurrent.TimeUnit;

class Daemon implements Runnable {
	private Thread[] threads = new Thread[5];

	@Override
	public void run() {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new DaemonSpawn());
			threads[i].start();
			System.out.print("DaemonSpawn " + i + " started, ");
		}

		for (int i = 0; i < threads.length; i++) {
			System.out.print("t[" + i + "].isDaemon = " + threads[i].isDaemon() + ". ");
		}

		while (true) Thread.yield();
	}
}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}

public class Daemons {
	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Daemon());
		thread.setDaemon(true);
		thread.start();
		System.out.print("thread.isDaemon() = " + thread.isDaemon() + ", ");
		TimeUnit.SECONDS.sleep(1);
	}

}
