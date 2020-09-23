package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator generator;

	private final int id;

	public EvenChecker(IntGenerator generator, int id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				// 取消所有的EvenCheckers
				generator.cancel();
			}
		}
	}

	// 测试各种类型的IntGenerator
	public static void test(IntGenerator intGenerator, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			executorService.execute(new EvenChecker(intGenerator, i));
		}

		executorService.shutdown();
	}

	// 默认的测试方法
	public static void test(IntGenerator intGenerator) {
		test(intGenerator, 20);
	}
}
