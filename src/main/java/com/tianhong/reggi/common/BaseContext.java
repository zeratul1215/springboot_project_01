package com.tianhong.reggi.common;

//based on ThreadLocal, get and set the user id currently login
//every thread has one of these piece
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static long getCurrentId(){
        return threadLocal.get();
    }

}
