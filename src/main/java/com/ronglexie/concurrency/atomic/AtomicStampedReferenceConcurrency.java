package com.ronglexie.concurrency.atomic;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子性：AtomicStampedReference并发测试类
 * 带有时间戳的对象引用，解决ABA问题
 * 参考链接:https://www.cnblogs.com/java20130722/p/3206742.html
 *
 * @author ronglexie
 * @version 2018/4/12
 */
@ThreadSafe
@Slf4j
public class AtomicStampedReferenceConcurrency {

	private static AtomicInteger atomicInt = new AtomicInteger(100);
	private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

	public static void main(String[] args) throws InterruptedException {
		Thread intT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				atomicInt.compareAndSet(100, 101);
				atomicInt.compareAndSet(101, 100);
			}
		});

		Thread intT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				boolean c3 = atomicInt.compareAndSet(100, 101);
				// true
				System.out.println(c3);
			}
		});

		intT1.start();
		intT2.start();
		intT1.join();
		intT2.join();

		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
				atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
			}
		});

		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int stamp = atomicStampedRef.getStamp();
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
				}
				boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
				// false
				System.out.println(c3);
			}
		});

		refT1.start();
		refT2.start();
	}
}
