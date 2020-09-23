package com.practise.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {

	private AtomicInteger i = new AtomicInteger(0);

	private void evenIncrement() {
		i.addAndGet(2);
	}

	public int getValue() {
		return i.get();
	}


	@Override
	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		// 在5秒后停止
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);

		ExecutorService executorService = Executors.newCachedThreadPool();
		AtomicIntegerTest test = new AtomicIntegerTest();
		executorService.execute(test);
		while (true) {
			int val = test.getValue();
			if (val % 2 != 0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
