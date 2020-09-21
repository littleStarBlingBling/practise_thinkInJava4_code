package com.practise.concurrency;

public class SelfManaged implements Runnable {
	private int countDown = 3;
	private Thread thread = new Thread(this);

	public SelfManaged() {
		thread.start();
	}

	@Override
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + "), ";
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
			new SelfManaged();
		}
	}
}
