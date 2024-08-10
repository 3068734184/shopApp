package com.example.shopmodel.utils;

import com.example.shopmodel.info.PageInfo;

public class LocalpageInfoUtil {
    //创建线程对象把你需要的实例对象给构建出来、
    private static ThreadLocal<PageInfo> localpageInfoThreadLocal=new ThreadLocal<>();

    //set 线程上设置一个标签{k,v}{total:10}
    public static void set(PageInfo pageInfo){
        localpageInfoThreadLocal.set(pageInfo);
    }
    //get 获取 从线程上面获取 只是只读 没有删除
    public static PageInfo get(){
        return localpageInfoThreadLocal.get();
    }
    //把标签数据删除
    public static void remove(){
        localpageInfoThreadLocal.remove();
    }

}
