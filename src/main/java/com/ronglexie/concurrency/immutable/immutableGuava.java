package com.ronglexie.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ronglexie.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 不可变对象
 * a.对象创建以后其状态就不能修改
 * b.对象所有域都是final类型
 * c.对象是正确创建的（在对象创建期间，this引用没有逸出）
 *
 * Guava的ImmutableXXX可创建不可变对象
 *
 * ImmutableList、ImmutableSet、ImmutableMap
 *
 * @author ronglexie
 * @version 2018/4/19
 */
@Slf4j
@ThreadSafe
public class immutableGuava {

	private final static ImmutableList list = ImmutableList.of(1,2,3);

	private final static ImmutableSet set = ImmutableSet.of(1,2,3);

	private final static ImmutableMap<Integer,Integer> map = ImmutableMap.<Integer,Integer>builder()
			.put(1,2).put(3,4).put(5,6).build();

	public static void main(String[] args) {
		map.put(1,2);

		log.info("{}",map.toString());
	}
}
