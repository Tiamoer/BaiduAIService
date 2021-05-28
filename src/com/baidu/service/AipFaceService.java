package com.baidu.service;

import com.baidu.singleton.AipFaceSingleton;
import com.baidu.singleton.SingletonFactory;
import com.baidu.utils.Base64Util;
import com.baidu.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AipFaceService {

    private final AipFaceSingleton aipFaceSingleton = (AipFaceSingleton) SingletonFactory.getSingleton(SingletonFactory.FACE);

    public void faceDetection(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.迎来
        //1.1 首先将存在请求对象中的图片的字节数组取得
        byte[] img = FileUtil.getFileAsByteArray(req);

        //1.2 将图片中的字节数组转换为Base64字符串
        String imgStr = Base64Util.encode(img);

        //2.运算
        HashMap<String,String> options = new HashMap<>();
        options.put("face_field","age,beauty,expression,face_shape,gender,glasses,landmark,landmark150,quality,eye_status,emotion,face_type,mask,spoofing");
        options.put("max_face_num","1");
        options.put("face_type","LIVE");
        options.put("liveness_control","LOW");

        JSONObject jsonObject = aipFaceSingleton.detect(imgStr, "BASE64", options);

        //3.送往
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.write(jsonObject.toString());
        pw.flush();
    }
}
