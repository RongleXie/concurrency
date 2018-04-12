package com.ronglexie.concurrency.atomic;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性：AtomicInteger并发测试类
 *
 * @author ronglexie
 * @version 2018/4/11
 */
@ThreadSafe
@Slf4j
public class AtomicIntegerConcurrency {

	/**
	 * 请求总数
	 */
	private static int clientTotal = 5000;

	/**
	 * 线程并发数
	 */
	private static int threadTotal = 200;

	/**
	 * 计数
	 */
	private static java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger(0);

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++){
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("exception:",e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",count);
	}

	private static void add(){
		count.incrementAndGet();
//		count.getAndIncrement();
	}
}
