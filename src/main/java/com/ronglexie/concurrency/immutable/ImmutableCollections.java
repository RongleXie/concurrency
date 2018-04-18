package com.ronglexie.concurrency.immutable;

import com.google.common.collect.Maps;
import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 不可变对象
 * a.对象创建以后其状态就不能修改
 * b.对象所有域都是final类型
 * c.对象是正确创建的（在对象创建期间，this引用没有逸出）
 *
 * Collections.unmodifiableXXX可创建不可变对象
 *
 * Collections.unmodifiableMap
 *
 * @author ronglexie
 * @version 2018/4/19
 */
@Slf4j
@ThreadSafe
public class ImmutableCollections {

	private static Map<Integer,Integer> map = Maps.newHashMap();

	static {
		map.put(1,2);
		map.put(3,4);
		map.put(5,6);
		map = Collections.unmodifiableMap(map);
	}

	public static void main(String[] args) {
		map.put(1,5);//map不可变，要报错
		log.info("{}",map.toString());
	}
}
