package com.li.reggie.common;

/**
 * @Author LI
 * @create 2022/6/25 21:33
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static long getCurrentId(){
        return threadLocal.get();
    }
}
