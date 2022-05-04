package com.example.demo.Util;


public class UserThreadLocal {

    private UserThreadLocal(){}
    //线程变量隔离
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void put(String password){
        LOCAL.set(password);
    }

    public static String get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
