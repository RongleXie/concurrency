package com.ronglexie.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock控制锁有三种模式(写,读,乐观读)
 * 乐观读取锁
 * JDK8新增锁
 *
 * @author ronglexie
 * @version 2018/4/24
 */
@Slf4j
public class LockStampedLock {

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
	private static int count = 0;

	/**
	 * 锁
	 */
	private final static StampedLock stampedLock = new StampedLock();

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
		long writeLock = stampedLock.writeLock();
		try {
			count ++;
		}finally {
			stampedLock.unlock(writeLock);
		}
	}
}
