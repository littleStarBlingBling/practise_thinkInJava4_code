package com.practise.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 非线程安全类
class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	@Override
	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	public class PairValuesNotEqualException extends RuntimeException {
		public PairValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}

	public void checkState() {
		if (x != y)
			throw new PairValuesNotEqualException();
	}
}

// 在线程安全的类的内部保护Pair类
abstract class PairManager {

	// 用于跟踪可以运行测试的频度
	AtomicInteger checkCounter = new AtomicInteger(0);

	protected Pair pair = new Pair();

	private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

	// 对公开的方法进行同步控制
	public synchronized Pair getPair() {
		// 返回副本，保证本体的安全
		return new Pair(pair.getX(), pair.getY());
	}

	// 假设这是一个耗时操作
	protected void store(Pair pair) {
		// 将Pair加入一个线程安全的List中
		storage.add(pair);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException ignore) {
		}
	}

	public abstract void increment();

}

// 同步整个方法
class PairManager1 extends PairManager {
	// synchronized不属于方法特征签名的一部分，可以在覆盖方法时加上
	@Override
	public synchronized void increment() {
		pair.incrementX();
		pair.incrementY();
		store(getPair());
	}
}

// 使用同步控制块
class PairManager2 extends PairManager {
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
		}

		store(temp);
	}
}

// 一个任务负责调用increment()
class PairManipulator implements Runnable {

	private PairManager pairManager;

	public PairManipulator(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while (true)
			pairManager.increment();
	}

	@Override
	public String toString() {
		return "Pair: " + pairManager.getPair() + " checkCounter = " + pairManager.checkCounter.get();
	}
}

// 另一个任务在获取到pairManager时递增checkCounter
class PairChecker implements Runnable {

	private PairManager pairManager;

	public PairChecker(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while (true) {
			pairManager.checkCounter.incrementAndGet();
			pairManager.getPair().checkState();
		}
	}
}


public class CriticalSection {

	public static void testApproaches(PairManager pairManager1, PairManager pairManager2) {
		ExecutorService exec = Executors.newCachedThreadPool();

		PairManipulator
				pm1 = new PairManipulator(pairManager1),
				pm2 = new PairManipulator(pairManager2);

		PairChecker
				pc1 = new PairChecker(pairManager1),
				pc2 = new PairChecker(pairManager2);

		exec.execute(pm1);
		exec.execute(pm2);

		exec.execute(pc1);
		exec.execute(pc2);

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}

		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}

	public static void main(String[] args) {
		PairManager
				pm1 = new PairManager1(),
				pm2 = new PairManager2();

		testApproaches(pm1, pm2);
	}
}
