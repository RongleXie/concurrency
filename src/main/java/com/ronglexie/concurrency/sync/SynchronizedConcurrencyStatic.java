package com.ronglexie.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 原子性：synchronized 并发测试类
 * synchronized
 * 修饰静态方法：整个静态方法，作用于类的所有对象
 * 修饰类：括号括起来的部分，作用于类的所有对象
 * @author ronglexie
 * @version 2018/4/14
 */
@Slf4j
public class SynchronizedConcurrencyStatic {

	/**
	 * synchronized修饰类
	 *
	 * @param j
	 * @return void
	 * @author wxt.xqr
	 * @version 2018/4/14
	 */
	public static void test1(int j){
		synchronized (SynchronizedConcurrencyStatic.class){
			for (int i = 0; i < 10; i++) {
				log.info("test1 {} - {}",j,i);
			}
		}
	}

	/**
	 * synchronized修饰静态方法
	 *
	 * @param j
	 * @return void
	 * @author wxt.xqr
	 * @version 2018/4/14
	 */
	public static synchronized void test2(int j){
		for (int i = 0; i < 10; i++) {
			log.info("test2 {} - {}", j, i);
		}
	}

	public static void main(String[] args){
		SynchronizedConcurrencyStatic synchronizedCouncurrencyStatic1 = new SynchronizedConcurrencyStatic();
		SynchronizedConcurrencyStatic synchronizedCouncurrencyStatic2 = new SynchronizedConcurrencyStatic();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() ->{
			synchronizedCouncurrencyStatic1.test1(1);
		});
		executorService.execute(() ->{
			synchronizedCouncurrencyStatic2.test1(2);
		});
		executorService.execute(() ->{
			synchronizedCouncurrencyStatic1.test2(1);
		});
		executorService.execute(() ->{
			synchronizedCouncurrencyStatic2.test2(2);
		});
		executorService.shutdown();
	}

}
