package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new ExceptionThread());
	}
}
