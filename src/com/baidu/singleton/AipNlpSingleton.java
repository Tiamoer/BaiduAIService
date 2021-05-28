package com.baidu.singleton;

/*
 * 使用单例模式构建的创建唯一AipNlp对象的类型
 * 两种实现方式：懒汉模式，饿汉模式
 * 懒汉模式：懒加载模式
 * 饿汉模式：立即加载模式
 * 单例模式三大件：
 *  1.私有的构造器
 *  2.一个静态的，用来保存本类对象的全局变量
 *  3.一个用来获取本类唯一对象的接口方法
 * 本次使用懒汉模式
 *
 * 饿汉模式在多线程情况下是线程安全的
 * 懒汉模式在多线程情况下是不安全的，解决方法DCL：Double Check Lock
 */

import com.baidu.aip.nlp.AipNlp;
import com.baidu.register.KeysRegister;

/**
 * 使用静态代理的目的：
 *  为了让自定义的AipNlpSingleton对象具有AipNlp核心类的功能
 * 在这个代理模式种，我们使用继承的方式完成上述操作
 */
public class AipNlpSingleton extends AipNlp {

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    //静态初始化块
    static {
        try{
            String[] keys = KeysRegister.getKeys(KeysRegister.Nlp);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.一个静态的，用来保存本类对象的全局变量
    private static AipNlpSingleton aipNlpSingleton;

    //1.私有的构造器，为了防止外界能够重新实例化这个类型的对象，从而保证这个类型的对象在内存中始终只有一个
    private AipNlpSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    //3.创建一个外界能够获取本类唯一对象的接口方法
    public static AipNlpSingleton getInstance() {
        if (aipNlpSingleton == null)
            aipNlpSingleton = new AipNlpSingleton();
        return aipNlpSingleton;
    }

}
