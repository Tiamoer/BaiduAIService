package com.baidu.singleton;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.register.KeysRegister;

import java.io.IOException;

/**
 * 通用物体识别 Singleton类
 */

public class AipDistinguishSingleton extends AipImageClassify {

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    private static AipDistinguishSingleton aipDistinguishSingleton;

    static {
        try {
            String[] keys = KeysRegister.getKeys(KeysRegister.DISTINGUISH);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AipDistinguishSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipDistinguishSingleton getInstance() {
        if (aipDistinguishSingleton == null) {
            aipDistinguishSingleton = new AipDistinguishSingleton();
        }
        return aipDistinguishSingleton;
    }

}
