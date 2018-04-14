package com.ronglexie.concurrency.publishobject;

import com.ronglexie.concurrency.annotation.UnRecommend;
import com.ronglexie.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 错误发布对象后（对象未构建完成，就将其发布），对象逸出事例
 *
 * 安全发布对象
 * 发布对象：使一个对象能够被当前范围之外的代码所使用
 * 对象逸出：一个错误的发布。当一个对象还没有构造完成时，就使它被其他线程可见
 * @author ronglexie
 * @version 2018/4/14
 */
@UnThreadSafe
@UnRecommend
@Slf4j
public class EscapeObject {
	private int thisCanBeEscape = 0;

	public EscapeObject(){
		new InnerClass();
	}

	private class InnerClass{

		public InnerClass(){
			log.info("{}",EscapeObject.this.thisCanBeEscape);
		}
	}


	public static void main(String[] args) {
		new EscapeObject();
	}
}
