package com.ronglexie.concurrency.atomic;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子性：AtomicReference并发测试类
 *
 * @author ronglexie
 * @version 2018/4/12
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceConcurrency {

	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

	public static void main(String[] args){
		//2
		count.compareAndSet(0, 2);
		//4
		count.compareAndSet(2, 4);
		//4
		count.compareAndSet(3, 5);
		log.info("count:{}",count.get());
	}
}
