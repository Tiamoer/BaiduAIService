package com.baidu.service;

import com.baidu.singleton.AipImageProcessSingleton;
import com.baidu.singleton.SingletonFactory;
import com.baidu.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AipImageProcessService {

    private final AipImageProcessSingleton aipImageProcessSingleton = (AipImageProcessSingleton) SingletonFactory.getSingleton(SingletonFactory.IMAGE_PROCESS);

    public void selfieanime(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.迎来，以字节数组的形式接受上传的图片信息
        byte[] imageByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算，调用百度AI实现人物动漫画操作
        HashMap<String, String> options = new HashMap<>();
        options.put("type", "anime");
//        options.put("mask_id", "3");

        JSONObject jsonObject = aipImageProcessSingleton.selfieAnime(imageByteArray, options);


        //3.送往，将接到的JSONObject转换为字符串进行返回
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }

    public void blackwhite(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.迎来，以字节数组的形式接受上传的图片信息
        byte[] imageByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算，调用百度AI实现黑白操作
//        HashMap<String,String> options = new HashMap<>();

        JSONObject jsonObject = aipImageProcessSingleton.colourize(imageByteArray, null);

        //3.送往，将接到的JSONObject转换为字符串进行返回
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();

    }

    public void styleTrans(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.迎来，以字节数组的形式接受上传的图片信息
        byte[] imageByteArray = FileUtil.getFileAsByteArray(req);

        //2.运算，调用百度AI实现黑白操作
        HashMap<String, String> options = new HashMap<>();
        /*
         option参数列表
            cartoon：卡通画风格
            pencil：铅笔风格
            color_pencil：彩色铅笔画风格
            warm：彩色糖块油画风格
            wave：神奈川冲浪里油画风格
            lavender：薰衣草油画风格
            mononoke：奇异油画风格
            scream：呐喊油画风格
            gothic：哥特油画风格
         */
        options.put("option", "pencil");

        JSONObject jsonObject = aipImageProcessSingleton.styleTrans(imageByteArray, options);

        //3.送往，将接到的JSONObject转换为字符串进行返回
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();
    }
}
