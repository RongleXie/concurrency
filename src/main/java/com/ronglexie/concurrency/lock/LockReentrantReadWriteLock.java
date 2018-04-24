package com.ronglexie.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock（可重入读写锁ReentrantReadWriteLock）
 * 悲观读取锁
 * 原则：在没有任何读写锁的情况下，才能够取得写入锁
 *
 * @author ronglexie
 * @version 2018/4/24
 */
@Slf4j
public class LockReentrantReadWriteLock {

	/**
	 * map
	 */
	private final Map<String,Object> map = new HashMap<>();

	/**
	 * 锁
	 */
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

	/**
	 * 读取锁(悲观锁)
 	 */
	private final Lock readLock = reentrantReadWriteLock.readLock();

	/**
	 * 写入锁
	 */
	private final Lock writeLock = reentrantReadWriteLock.writeLock();


	public Object get(String key){
		readLock.lock();
		try {
			return map.get(key);
		}finally {
			readLock.unlock();
		}
	}

	public Set<String> getAllKeys() {
		readLock.lock();
		try {
			return map.keySet();
		}finally {
			readLock.unlock();
		}
	}

	public void put(String key, Object value){
		writeLock.lock();
		try{
			map.put(key,value);
		}finally {
			writeLock.unlock();
		}
	}


}
