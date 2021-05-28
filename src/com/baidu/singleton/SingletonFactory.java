package com.baidu.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过传递参数的形式，通过一个方法根据不同的参数返回不同类型的Singleton对象
 */

public class SingletonFactory {

    public static final String FACE = "FACE";
    public static final String IMAGE_PROCESS = "IMAGE_PROCESS";
    public static final String NLP = "NLP";
    public static final String OCR = "OCR";
    public static final String SPEECH = "SPEECH";
    public static final String DISTINGUISH = "DISTINGUISH";

    private static Map<String, Object> map = new HashMap<>();

    static {

        map.put("FACE", AipFaceSingleton.getInstance());
        map.put("IMAGE_PROCESS", AipImageProcessSingleton.getInstance());
        map.put("NLP", AipNlpSingleton.getInstance());
        map.put("OCR", AipOcrSingleton.getInstance());
        map.put("SPEECH", AipSpeechSingleton.getInstance());
        map.put("DISTINGUISH", AipDistinguishSingleton.getInstance());

    }

    public static Object getSingleton(String type) {
        return map.get(type);
    }

}
