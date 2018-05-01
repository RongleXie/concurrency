package com.ronglexie.concurrency.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁
 *
 * @author ronglexie
 * @version 2018/5/1
 */
@Slf4j
public class DeadLock {
	/**
	 * 静态对象是类的所有对象共享的
	 */
	public static final String a = "A";
	public static final String b = "B";

	public static void main(String[] args) {
		new DeadLock().deadLock();
	}

	private void deadLock(){
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (a) {
					try {
						log.info("thread1 is locking a");
						Thread.sleep(2000);
						synchronized (b) {
							log.info("thread1 is locking b");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (b) {
					log.info("thread2 is locking b");
					synchronized (a) {
						log.info("thread2 is locking a");
					}
				}
			}
		});

		thread1.start();
		thread2.start();
	}
}
