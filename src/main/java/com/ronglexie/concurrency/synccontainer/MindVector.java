package com.ronglexie.concurrency.synccontainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * Vector注意事项
 * 在循环操作时不建议对集合进行remove操作
 * 如需在循环内进行remove操作，不能使用foreach和iterator,建议使用for循环
 *
 * @author ronglexie
 * @version 2018/4/22
 */
public class MindVector {

	//java.util.ConcurrentModificationException
	private static void test1(Vector<Integer> vector){//foreach
		for (Integer i: vector ) {
			if(i.equals(3)){
				vector.remove(i);
			}
		}
	}

	//java.util.ConcurrentModificationException
	private static void test2(Vector<Integer> vector){//iterator
		Iterator<Integer> iterator = vector.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if(i.equals(3)){
				vector.remove(i);
			}
		}
	}
	//正常
	private static void test3(Vector<Integer> vector){
		for (int i = 0; i < vector.size(); i++) {
			if(vector.get(i).equals(3)){
				vector.remove(i);
			}
		}
	}

	public static void main(String[] args) {
		Vector<Integer> vector = new Vector<>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		test1(vector);
		test2(vector);
		test3(vector);
	}
}
