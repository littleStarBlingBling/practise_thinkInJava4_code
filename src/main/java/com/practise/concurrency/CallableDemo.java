package com.practise.concurrency;


import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() {
		return "result of TaskWithResult " + id;
	}
}

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			results.add(executorService.submit(new TaskWithResult(i)));
		}

		for (Future<String> future : results) {
			try {
				// 直接调用get()方法会阻塞，直到结果准备就绪
				System.out.println(future.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				executorService.shutdown();
			}
		}
	}
}
