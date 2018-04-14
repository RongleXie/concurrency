package com.ronglexie.concurrency.singleton;

import com.ronglexie.concurrency.annotation.ThreadSafe;
import com.ronglexie.concurrency.annotation.UnRecommend;
import com.ronglexie.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式 -> 双重同步锁单例模式+volatile
 * 懒汉模式:单例实例在第一次使用时创建
 * 线程安全
 * @author ronglexie
 * @version 2018/4/14
 */
@ThreadSafe
@Slf4j
public class SingletonExample5 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample5(){

	}

	/**
	 * new初始化对象过程，指令
	 * 1、memory = allocate() 分配对象的内存空间
	 * 2、ctorInstance() 初始化对象
	 * 3、instance = memory 设置instance 指向分配的内存
	 */

	/**
	 * volatile 禁止创建对象时的指令重排
	 */

	/**
	 * 单例对象
	 */
	private volatile static SingletonExample5 instance = null;

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static SingletonExample5 getInstance(){
		if(instance == null) {// 双重检测机制
			synchronized (SingletonExample5.class){//同步锁
				if(instance == null){
					instance = new SingletonExample5();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		SingletonExample5 instance1 = getInstance();
		SingletonExample5 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
