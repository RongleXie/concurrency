package com.ronglexie.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 原子性：synchronized 并发测试类
 * synchronized
 * 修饰代码块：大括号括起来的代码，作用于调用的对象
 * 修饰普通方法：整个方法作用于调用的对象
 * @author ronglexie
 * @version 2018/4/14
 */
@Slf4j
public class SynchronizedConcurrencyNormal {

	/**
	 * synchronized修饰代码块
	 *
	 * @param j
	 * @return void
	 * @author wxt.xqr
	 * @version 2018/4/14
	 */
	public void test1(int j){
		synchronized (this){
			for (int i = 0; i < 10; i++) {
				log.info("test1 {} - {}",j,i);
			}
		}
	}

	/**
	 * synchronized修饰普通方法
	 *
	 * @param j
	 * @return void
	 * @author wxt.xqr
	 * @version 2018/4/14
	 */
	public synchronized void test2(int j){
		for (int i = 0; i < 10; i++) {
			log.info("test2 {} - {}", j, i);
		}
	}

	public static void main(String[] args){
		SynchronizedConcurrencyNormal synchronizedConcurrencyNormal = new SynchronizedConcurrencyNormal();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() ->{
			synchronizedConcurrencyNormal.test1(1);
		});
		executorService.execute(() ->{
			synchronizedConcurrencyNormal.test1(2);
		});
		executorService.execute(() ->{
			synchronizedConcurrencyNormal.test2(1);
		});
		executorService.execute(() ->{
			synchronizedConcurrencyNormal.test2(2);
		});
		executorService.shutdown();
	}

}
