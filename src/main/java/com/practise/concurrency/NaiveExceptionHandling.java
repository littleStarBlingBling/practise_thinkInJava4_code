package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {
	public static void main(String[] args) {
		try{
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.execute(new ExceptionThread());
		}catch (RuntimeException e){
			// 打印输出并不会执行
			System.out.println("Exception has been handled!");
		}
	}
}
