package com.practise.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 同步整个方法
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();

	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			pair.incrementX();
			pair.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}

	}
}

// 使用临界区
class ExplicitPairManager2 extends PairManager {

	private Lock lock = new ReentrantLock();

	@Override
	public void increment() {
		Pair temp;
		lock.lock();
		try{
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
		}finally {
			lock.unlock();

		}
		store(temp);
	}
}

public class ExplicitCriticalSection {
	public static void main(String[] args) {
		PairManager
				pm1 = new ExplicitPairManager1(),
				pm2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pm1, pm2);
	}
}
