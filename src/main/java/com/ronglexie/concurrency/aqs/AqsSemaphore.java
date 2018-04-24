package com.ronglexie.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * J.U.C之AQS-Semaphore
 * 信号量
 *
 * @author ronglexie
 * @version 2018/4/23
 */
@Slf4j
public class AqsSemaphore {

	/**
	 * 线程总数
	 */
	private final static Integer threadCount = 200;

	/**
	 * 线程并发数
	 */
	private static Integer threadTotal = 10;

	public static void main(String[] args) throws Exception{

		//创建线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		//创建闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		//创建信号量
		Semaphore semaphore = new Semaphore(threadTotal);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			executorService.execute(()->{
				try {
					//==============普通获取信号==============
					//获取信号
					semaphore.acquire();
					test(threadNum);
					//释放信号
					semaphore.release();
					//==============普通获取信号==============
					//==============复杂获取信号，带个数和超时时间参数==============
					/*if(semaphore.tryAcquire(1,1,TimeUnit.SECONDS)){
						test(threadNum);
						semaphore.release(1);
					}*/
					//==============复杂获取信号，带个数和超时时间参数==============
				}catch (Exception e){
					log.error("exception",e);
				}finally {
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();
		executorService.shutdown();
	}

	private static void test(int threadNum) throws Exception{
		log.info("{}",threadNum);
		Thread.sleep(1000);
	}
}
