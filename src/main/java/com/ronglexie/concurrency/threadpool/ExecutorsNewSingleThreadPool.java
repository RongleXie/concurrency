package com.ronglexie.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Executors.newSingleThreadExecutor()
 * 单线程执行
 *
 * @author ronglexie
 * @version 2018/4/28
 */
@Slf4j
public class ExecutorsNewSingleThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final Integer count = i;
			executorService.execute(() -> {
				log.info("task: {} is running",count);
			});
		}
		executorService.shutdown();
	}
}
