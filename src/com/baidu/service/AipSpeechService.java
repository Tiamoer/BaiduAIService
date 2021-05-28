package com.baidu.service;

import com.baidu.aip.speech.TtsResponse;
import com.baidu.singleton.AipSpeechSingleton;
import com.baidu.singleton.SingletonFactory;
import com.baidu.utils.Base64Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
    语音合成的业务逻辑层
 */
public class AipSpeechService {

    private final AipSpeechSingleton aipSpeechSingleton = (AipSpeechSingleton) SingletonFactory.getSingleton(SingletonFactory.SPEECH);

    public void synthesis(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.接收数据：语音合成文档，语音人物，语速，语调
        String content = req.getParameter("content");
        String per = req.getParameter("per");
        String spd = req.getParameter("spd");
        String pit = req.getParameter("pit");

        //2.运算：调用单例的AipSpeechSingleton当中的方法进行语音合成，并将得到的字节数组转换为Base64字符串
        HashMap<String, Object> options = new HashMap<>();
        options.put("per",per);
        options.put("spd",spd);
        options.put("pit",pit);

        TtsResponse ttsResponse = aipSpeechSingleton.synthesis(content, "zh", 1, options);

        byte[] data = null;
        if (ttsResponse.getResult() == null) {  //语音合成成功
            data = ttsResponse.getData();  //合成语音的字节数据
        }

        //将字节数组转换为Base64字符串
        assert data != null;
        String dataStr = Base64Util.encode(data);
        System.out.println(dataStr);

        //3.送往
        resp.setContentType("audio/mpeg"); //表示当前响应的数据是一段音频数据

        PrintWriter pw = resp.getWriter();
        pw.write(dataStr);
        pw.flush();

    }
}
