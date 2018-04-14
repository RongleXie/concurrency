package com.ronglexie.concurrency.publishobject;

import com.ronglexie.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 线程不安全发布对象事例
 *
 * 安全发布对象
 * 发布对象：使一个对象能够被当前范围之外的代码所使用
 * 对象逸出：一个错误的发布。当一个对象还没有构造完成时，就使它被其他线程可见
 * @author ronglexie
 * @version 2018/4/14
 */
@UnThreadSafe
@Slf4j
public class UnSafePublishObject {

	private String[] states = {"a","b","c"};

	public String[] getStates(){
		return states;
	}

	public static void main(String[] args) {
		UnSafePublishObject unSafePublishObject = new UnSafePublishObject();
		log.info("{}", Arrays.toString(unSafePublishObject.getStates()));

		unSafePublishObject.states[1] = "d";
		log.info("{}", Arrays.toString(unSafePublishObject.getStates()));
	}
}
