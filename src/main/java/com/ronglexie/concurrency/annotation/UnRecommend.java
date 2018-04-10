package com.ronglexie.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记【不推荐】的类或用法
 *
 * @author ronglexie
 * @version 2018/4/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface UnRecommend {

	String value() default "";
}
