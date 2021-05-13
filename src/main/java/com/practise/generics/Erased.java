package com.practise.generics;

public class Erased<T> {

    private final int SIZE = 100;

    public  void f(Object arg){
//		// Class or array expected
//		if(arg instanceof  T){}
//
//		// 参数T不能被直接实例化
//		T var = new T();
//
//		// 参数T不能被直接实例化
//		T[] array = new T[SIZE];
//
//		// unchecked cast
		T[] array2 = (T[])new Object[SIZE];
    }
}
