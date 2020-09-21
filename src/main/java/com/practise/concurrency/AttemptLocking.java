package com.practise.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try{
			System.out.println("tryLock(): " + captured);
		}finally {
			if (captured){
				lock.unlock();
			}
		}
	}

	public void timed(){
		boolean captured = false;
		try{
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		}catch (InterruptedException e){
			throw new RuntimeException(e);
		}

		try{
			System.out.println("tryLock(2, TimeUnit.SECONDS: " + captured);
		}finally {
			if(captured){
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		final  AttemptLocking attemptLocking = new AttemptLocking();
		attemptLocking.untimed();
		attemptLocking.timed();

		// 现在来创建一个单独的线程争抢锁
		new Thread(){
			{setDaemon(true);}

			@Override
			public void run() {
				attemptLocking.lock.lock();
				System.out.println("acquired");
			}
		}.start();

		Thread.yield();
		attemptLocking.untimed();
		attemptLocking.timed();
	}
}
