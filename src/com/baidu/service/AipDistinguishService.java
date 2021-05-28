package com.baidu.service;

import com.baidu.singleton.AipDistinguishSingleton;
import com.baidu.singleton.SingletonFactory;
import com.baidu.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.PrintWriter;

/**
 * 通用物体识别 service类
 */

public class AipDistinguishService {

    private final AipDistinguishSingleton aipDistinguishSingleton = (AipDistinguishSingleton) SingletonFactory.getSingleton(SingletonFactory.DISTINGUISH);

    public void objectReco(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //1.迎来
        byte[] imageByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算
        JSONObject jsonObject = aipDistinguishSingleton.advancedGeneral(imageByteArray, null);

        //3.送往
        resp.setContentType("Application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }

    public void animalDetect(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        byte[] imageByteArray = FileUtil.getFileAsByteArray(req);

        JSONObject jsonObject = aipDistinguishSingleton.animalDetect(imageByteArray, null);

        resp.setContentType("Application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }
}
