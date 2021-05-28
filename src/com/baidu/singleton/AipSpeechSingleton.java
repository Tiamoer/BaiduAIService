package com.baidu.singleton;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.register.KeysRegister;

import java.io.IOException;

public class AipSpeechSingleton extends AipSpeech {

    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    static {

        try {
            String[] keys = KeysRegister.getKeys(KeysRegister.Speech);

            APP_ID = keys[0];
            API_KEY = keys[1];
            SECRET_KEY = keys[2];

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static AipSpeechSingleton aipSpeechSingleton;

    private AipSpeechSingleton() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipSpeechSingleton getInstance() {
        if (aipSpeechSingleton == null)
            aipSpeechSingleton = new AipSpeechSingleton();
        return aipSpeechSingleton;
    }

}
