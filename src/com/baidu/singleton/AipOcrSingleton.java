package com.baidu.singleton;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.register.KeysRegister;

import java.io.IOException;

public class AipOcrSingleton extends AipOcr {

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    private static AipOcrSingleton aipOcrSingleton;

    static {
        try {
            String[] keys = KeysRegister.getKeys(KeysRegister.OCR);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AipOcrSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipOcrSingleton getInstance() {
        if (aipOcrSingleton == null)
            aipOcrSingleton = new AipOcrSingleton();
        return aipOcrSingleton;
    }

}
