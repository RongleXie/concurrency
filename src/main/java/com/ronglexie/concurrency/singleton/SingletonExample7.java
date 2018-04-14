package com.ronglexie.concurrency.singleton;

import com.ronglexie.concurrency.annotation.Recommend;
import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式:最安全
 * 线程安全
 * @author ronglexie
 * @version 2018/4/14
 */
@ThreadSafe
@Recommend
@Slf4j
public class SingletonExample7 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample7(){

	}

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static SingletonExample7 getInstance(){
		return Singleton.INSTANCE.getSingleton();
	}

	public enum  Singleton{
		INSTANCE;

		private SingletonExample7 singleton;

		/**
		 * JVM保证此方法只执行一次
		 */
		Singleton(){
			singleton = new SingletonExample7();
		}

		public SingletonExample7 getSingleton() {
			return singleton;
		}
	}

	public static void main(String[] args) {
		SingletonExample7 instance1 = getInstance();
		SingletonExample7 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
