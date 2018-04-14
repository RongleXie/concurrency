package com.ronglexie.concurrency.singleton;

import com.ronglexie.concurrency.annotation.UnRecommend;
import com.ronglexie.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式 -> 双重同步锁单例模式
 * 懒汉模式:单例实例在第一次使用时创建
 * 单线程环境下线程安全
 * 多线程环境下线程不安全
 * @author ronglexie
 * @version 2018/4/14
 */
@UnThreadSafe
@UnRecommend
@Slf4j
public class SingletonExample4 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample4(){

	}

	/**
	 * new初始化对象过程，指令
	 * 1、memory = allocate() 分配对象的内存空间
	 * 2、ctorInstance() 初始化对象
	 * 3、instance = memory 设置instance 指向分配的内存
	 */

	/**
	 * new初始化对象过程
	 * JVM和CPU优化，发生指令重排
	 * 1、memory = allocate() 分配对象的内存空间
	 * 3、instance = memory 设置instance 指向分配的内存
	 * 2、ctorInstance() 初始化对象
	 *
	 * 此顺序造成线程不安全，第一个线程还未初始化对象时，第二个线程就引用了对象
	 */


	/**
	 * 单例对象
	 */
	private static SingletonExample4 instance = null;

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static SingletonExample4 getInstance(){
		if(instance == null) {// 双重检测机制
			synchronized (SingletonExample4.class){//同步锁
				if(instance == null){
					instance = new SingletonExample4();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		SingletonExample4 instance1 = getInstance();
		SingletonExample4 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
