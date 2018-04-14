package com.ronglexie.concurrency.singleton;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式
 * 饿汉模式:单例实例在类装载时创建
 * 静态块初始化对象
 * 线程安全
 * @author ronglexie
 * @version 2018/4/14
 */
@ThreadSafe
@Slf4j
public class SingletonExample6 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample6(){

	}

	/**
	 * 单例实例
	 */
	private static SingletonExample6 instance ;

	/**
	 * 静态块初始化对象
	 */
	static {
		instance = new SingletonExample6();
	}

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static SingletonExample6 getInstance(){
		return instance;
	}

	public static void main(String[] args) {
		SingletonExample6 instance1 = getInstance();
		SingletonExample6 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
