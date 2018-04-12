package com.ronglexie.concurrency.atomic;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * 原子性：AtomicLongArray并发测试类
 *
 * @author ronglexie
 * @version 2018/4/12
 */
@ThreadSafe
@Slf4j
public class AtomicLongArrayConcurrency {

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
	private static AtomicLongArray count = new AtomicLongArray(5);

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
		count.incrementAndGet(0);
		count.incrementAndGet(1);
		count.incrementAndGet(2);
		count.incrementAndGet(3);
		count.incrementAndGet(4);
//		count.getAndIncrement();
	}
}
