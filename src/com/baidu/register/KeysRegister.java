package com.baidu.register;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 根据用户需求从配置文件中读取相应的APP_ID, API_KEY, SECRET_KEY;
 */
public class KeysRegister {

    public static final String Nlp = "NLP";
    public static final String Speech = "SPEECH";
    public static final String Face = "FACE";
    public static final String Img = "IMAGE_PROCESS";
    public static final String OCR = "OCR";
    public static final String DISTINGUISH = "DISTINGUISH";

    public static String[] getKeys(String prefix) throws IOException {

        String[] keys = new String[3];

        //使用Properties类进行.properties文件读取
        Properties properties = new Properties();

        //通过固定的写法找到配置文件流对象
        InputStream is = KeysRegister.class.getClassLoader().getResourceAsStream("keys.properties");

        //通过流对象的方式加载配置文件信息
        properties.load(is);

        //加载完配置文件之后，从配置文件当中将需要的参数读取出来
        String APP_ID = properties.getProperty(prefix+"_APP_ID");
        String API_KEY = properties.getProperty(prefix+"_API_KEY");
        String SECRET_KEY = properties.getProperty(prefix+"_SECRET_KEY");

        keys[0] = APP_ID;
        keys[1] = API_KEY;
        keys[2] = SECRET_KEY;

        return keys;
    }

}
