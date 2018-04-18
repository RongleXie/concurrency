package com.ronglexie.concurrency.immutable;



import com.google.common.collect.Maps;
import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 不可变对象
 * a.对象创建以后其状态就不能修改
 * b.对象所有域都是final类型
 * c.对象是正确创建的（在对象创建期间，this引用没有逸出）
 *
 * final关键字
 * 1、修饰类：不能被继承
 * 2、修饰方法：a.锁定方法不被继承类修改；b.效率
 * 3、修饰变量：基本数据类型变量初始化后不可变，引用类型变量初始化后指向对象地址不可变
 *
 * @author ronglexie
 * @version 2018/4/18
 */
@Slf4j
@ThreadSafe
public class ImmutableFinal {
	private final static Integer a = 1;
	private final static String b = "2";
	private final static Map<Integer,Integer> map = Maps.newHashMap();

	static {
		map.put(1,2);
		map.put(3,4);
		map.put(5,6);
	}

	public static void main(String[] args) {
		map.put(1,5);
		log.info("{}",map.toString());
	}

}
