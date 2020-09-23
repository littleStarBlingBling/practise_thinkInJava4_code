package com.practise.concurrency;

public class SynchronizedEvenGenerator extends IntGenerator{
	private int currentEventValue = 0;

	@Override
	public synchronized int next() {
		++currentEventValue;
		Thread.yield();
		++currentEventValue;
		return currentEventValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
