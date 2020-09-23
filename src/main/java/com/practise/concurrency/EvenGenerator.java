package com.practise.concurrency;

public class EvenGenerator extends IntGenerator{
	private int currentEvenValue = 0;

	@Override
	public int next() {
		// ++ 操作并不是元子操作
		++currentEvenValue;
		// 用yield()方法更快地发现失败
//		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
