package com.ronglexie.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * Executors.newScheduledThreadPool()
 * 定时执行
 *
 * @author ronglexie
 * @version 2018/4/28
 */
@Slf4j
public class ExecutorsNewScheduledThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		for (int i = 0; i < 10; i++) {
			final Integer count = i;

//			scheduledExecutorService.schedule(() -> {
//				log.info("task: {} is running",count);
//			},3, TimeUnit.SECONDS);

			scheduledExecutorService.scheduleAtFixedRate(() -> {
				log.info("task: {} is running",count);
			},1,3, TimeUnit.SECONDS);
		}
	}
}
