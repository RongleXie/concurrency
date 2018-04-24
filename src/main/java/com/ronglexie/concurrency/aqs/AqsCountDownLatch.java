package com.ronglexie.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * J.U.C之AQS-CountDownLatch
 * 闭锁
 *
 * @author ronglexie
 * @version 2018/4/23
 */
@Slf4j
public class AqsCountDownLatch {
	private final static Integer threadCount = 200;

	public static void main(String[] args) throws Exception{

		//创建线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		//创建闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			executorService.execute(()->{
				try {
					test(threadNum);
				}catch (Exception e){
					log.error("exception",e);
				}finally {
					countDownLatch.countDown();
				}
			});
		}

//		countDownLatch.await();
		countDownLatch.await(100, TimeUnit.MICROSECONDS);//等待指定时间
		log.info("finish");
		executorService.shutdown();
	}

	private static void test(int threadNum) throws Exception{
		Thread.sleep(100);
		log.info("{}",threadNum);
		Thread.sleep(100);
	}
}
