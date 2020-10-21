package com.czhe.sysmanage.proxy;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/19 14:39
 * @description
 **/
public class DemoClazz {

    private static volatile DemoClazz instance;

    private DemoClazz() {
    }

    public static DemoClazz getInstance() {
        if (instance == null) {
            synchronized (DemoClazz.class) {
                if (instance == null) {
                    instance = new DemoClazz();
                }
            }
        }
        return instance;
    }
}