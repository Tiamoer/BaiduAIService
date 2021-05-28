package com.baidu.singleton;

import com.baidu.aip.face.AipFace;
import com.baidu.register.KeysRegister;

import java.io.IOException;

public class AipFaceSingleton extends AipFace {

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    private static AipFaceSingleton aipFaceSingleton;

    static {
        try {
            String[] keys = KeysRegister.getKeys(KeysRegister.Face);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AipFaceSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipFaceSingleton getInstance(){
        if (aipFaceSingleton == null)
            aipFaceSingleton = new AipFaceSingleton();
        return aipFaceSingleton;
    }
}
