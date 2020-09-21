package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		// 这里使用线程自动生成的名字，如pool-1-thread-1
		return Thread.currentThread() + ": " + countDown;
	}

	@Override
	public void run() {
		// 获取到驱动当前任务的线程，设置优先级
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}

			System.out.println(this);
			if (--countDown == 0) return;
		}
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}

		executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		executorService.shutdown();
	}
}
