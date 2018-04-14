package com.ronglexie.concurrency.singleton;

import com.ronglexie.concurrency.annotation.UnRecommend;
import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式
 * 懒汉模式:单例实例在第一次使用时创建
 * 线程安全
 * @author ronglexie
 * @version 2018/4/14
 */
@ThreadSafe
@UnRecommend
@Slf4j
public class SingletonExample3 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample3(){

	}

	/**
	 * 单例对象
	 */
	private static SingletonExample3 instance = null;

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static synchronized SingletonExample3 getInstance(){
		if(instance == null) {
			instance = new SingletonExample3();
		}
		return instance;
	}

	public static void main(String[] args) {
		SingletonExample3 instance1 = getInstance();
		SingletonExample3 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
