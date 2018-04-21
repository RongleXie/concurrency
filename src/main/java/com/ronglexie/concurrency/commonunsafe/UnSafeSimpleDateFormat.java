package com.ronglexie.concurrency.commonunsafe;

import com.ronglexie.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程不安全类：SimpleDataFormat类定义全局变量
 *
 * @author ronglexie
 * @version 2018/4/21
 */
@Slf4j
@UnThreadSafe
public class UnSafeSimpleDateFormat {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 请求总数
	 */
	private static int clientTotal = 5000;

	/**
	 * 线程并发数
	 */
	private static int threadTotal = 200;

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++){
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update();
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("exception:",e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}

	private static void update(){
		try {
			simpleDateFormat.parse("20180421");
		} catch (Exception e) {
			log.error("parse exception",e);
		}
	}
}
