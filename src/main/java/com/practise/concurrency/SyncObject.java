package com.practise.concurrency;

class DualSynch {
	private Object syncObject = new Object();

	// 在 this 上同步
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	// 在 syncObject 上同步
	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch dualSynch = new DualSynch();
		new Thread(){
			@Override
			public void run() {
				dualSynch.f();
			}
		}.start();
		dualSynch.g();
	}
}
