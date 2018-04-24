package com.ronglexie.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock（重入锁）
 * 独有功能：
 * 1、可指定是公平锁不是非公平锁
 * 2、提供了一个Condition类，可以分组唤醒需要唤醒的线程
 * 3、提供能够中断等待锁的线程的机制，lock.lockInterruptibly()
 *
 * @author ronglexie
 * @version 2018/4/24
 */
@Slf4j
public class LockReentrantLock {

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
	private static Lock lock = new ReentrantLock();

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
		lock.lock();
		try {
			count ++;
		}finally {
			lock.unlock();
		}
	}
}
