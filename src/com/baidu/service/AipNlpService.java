package com.baidu.service;

import com.baidu.singleton.AipNlpSingleton;
import com.baidu.singleton.SingletonFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.PrintWriter;

/**
 * 用来执行自然语言分析的业务逻辑层类
 * 所有和自然语言分析有关的方法都定义在这个类当中
 */

public class AipNlpService {

    private final AipNlpSingleton aipNlpSingleton = (AipNlpSingleton) SingletonFactory.getSingleton(SingletonFactory.NLP);
    JSONObject res;
    PrintWriter pw;

    //进行情感倾向分析的方法
    public void sentimentClassify(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //获取前端输入的内容，并将其发送给百度AI的SDK，得到结果后返回给前端
        String content = req.getParameter("content");
        res = aipNlpSingleton.sentimentClassify(content,null);

        //响应头的初始化
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //返回json字符串给前端
        pw = resp.getWriter();
        pw.write(res.toString());  //res type:JSON
        pw.flush();
    }

    //进行地址分析的方法
    public void address(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //获取前端输入的内容，并将其发送给百度AI的SDK，得到结果后返回给前端
        String content = req.getParameter("content");
        res = aipNlpSingleton.address(content,null);

        //响应头的初始化
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //返回json字符串给前端
        pw = resp.getWriter();
        pw.write(res.toString());
        pw.flush();
    }

}
