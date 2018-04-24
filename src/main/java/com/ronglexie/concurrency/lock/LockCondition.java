package com.ronglexie.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition条件
 * Lock提供条件Condition,对线程的等待、唤醒操作更加详细和灵活
 *
 * @author ronglexie
 * @version 2018/4/25
 */
@Slf4j
public class LockCondition {
	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition condition = reentrantLock.newCondition();

		new Thread(() -> {
			try {
				reentrantLock.lock();
				log.info("wait signal"); // 1
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("get signal"); // 4
			reentrantLock.unlock();
		}).start();

		new Thread(() -> {
			reentrantLock.lock();
			log.info("get lock"); // 2
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condition.signalAll();
			log.info("send signal ~ "); // 3
			reentrantLock.unlock();
		}).start();
	}
}
