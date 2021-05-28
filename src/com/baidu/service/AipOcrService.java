package com.baidu.service;

import com.baidu.singleton.AipOcrSingleton;
import com.baidu.singleton.SingletonFactory;
import com.baidu.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.HashMap;

public class AipOcrService {

    private final AipOcrSingleton aipOcrSingleton = (AipOcrSingleton) SingletonFactory.getSingleton(SingletonFactory.OCR);

    public void idcard(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //1.迎来
        //1.1 首先将存在请求对象中的图片的字节数组取得
        byte[] imgByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算
        HashMap<String,String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "true");

        JSONObject jsonObject = aipOcrSingleton.idcard(imgByteArray, "front", options);

        //3.送往
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }

    public void textdect(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //1.迎来
        //1.1 首先将存在请求对象中的图片的字节数组取得
        byte[] imgByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算
        HashMap<String,String> options = new HashMap<>();
        options.put("detect_direction", "false");
        options.put("probability", "true");

        JSONObject jsonObject = aipOcrSingleton.basicAccurateGeneral(imgByteArray, options);

        //3.送往
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }
}
