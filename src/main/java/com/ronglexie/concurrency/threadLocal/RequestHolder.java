package com.ronglexie.concurrency.threadLocal;

import lombok.extern.slf4j.Slf4j;

/**
 * request处理器
 *
 * @author ronglexie
 * @version 2018/4/20
 */
@Slf4j
public class RequestHolder {
	private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

	public static void add(Long id){
		requestHolder.set(id);
	}

	public static Long getId(){
		return requestHolder.get();
	}

	public static void remove(){
		requestHolder.remove();
	}
}
