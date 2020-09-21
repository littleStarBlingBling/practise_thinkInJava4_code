package com.practise.concurrency;

public class SimpleThread extends Thread {
	private int countDown = 3;
	private static int threadCount = 0;

	// 为线程赋予自定义的名称
	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
	}

	@Override
	public String toString() {
		return "#" + getName() + "(" + countDown + "), ";
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0) return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new SimpleThread();
		}
	}
}
