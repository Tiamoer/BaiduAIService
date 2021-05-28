package com.baidu.singleton;

import com.baidu.aip.imageprocess.AipImageProcess;
import com.baidu.register.KeysRegister;

import java.io.IOException;

public class AipImageProcessSingleton extends AipImageProcess {

    private static AipImageProcessSingleton aipImageProcessSingleton;

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    static {
        try {
            String[] keys = KeysRegister.getKeys(KeysRegister.Img);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AipImageProcessSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipImageProcessSingleton getInstance() {
        if (aipImageProcessSingleton == null)
            aipImageProcessSingleton = new AipImageProcessSingleton();
        return aipImageProcessSingleton;
    }

}
