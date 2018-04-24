package com.ronglexie.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * J.U.C之AQS-CyclicBarrier
 * 循环栅栏
 *
 * @author ronglexie
 * @version 2018/4/24
 */
@Slf4j
public class AqsCyclicBarrier {

	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> {
		log.info("callback is running");
	});

	public static void main(String[] args) throws Exception{

		//创建线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 20; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executorService.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					log.error("exception: {}",e);
				}
			});
		}

		executorService.shutdown();
	}

	private static void race(int threadNum) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready",threadNum);
		try {
			cyclicBarrier.await(2000, TimeUnit.MICROSECONDS);
		} catch (InterruptedException | TimeoutException | BrokenBarrierException e) {
			log.error("exception: {}",e);
		}
		log.info("{} is continue",threadNum);
	}
}
