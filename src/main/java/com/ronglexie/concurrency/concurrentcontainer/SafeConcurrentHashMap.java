package com.ronglexie.concurrency.concurrentcontainer;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 线程安全并发容器类：HashMap -> ConcurrentHashMap
 *
 * @author ronglexie
 * @version 2018/4/22
 */
@Slf4j
@ThreadSafe
public class SafeConcurrentHashMap {
	/**
	 * 请求总数
	 */
	private static int clientTotal = 5000;

	/**
	 * 线程并发数
	 */
	private static int threadTotal = 200;

	/**
	 * map
	 */
	private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++){
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("exception:",e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("size:{}",map.size());
	}

	private static void update(int i){
		map.put(i,i);
	}
}
