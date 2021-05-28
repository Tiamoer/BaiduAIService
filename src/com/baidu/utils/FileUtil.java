package com.baidu.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *  文件工具类
 */

public class FileUtil {

    public static byte[] getFileAsByteArray(HttpServletRequest req) throws IOException {

        //1.使用字节数组输出流对象作为文件字节数组承载的载体
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //2.对上传文件进行分析前做准备
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(); //在服务器端为上传的文件开辟缓存
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory); //使用上一步骤中开辟的缓存空间解析请求中上传的文件
        servletFileUpload.setHeaderEncoding("UTF-8"); //因为这个对象还可以分析其他类型的字符串参数，所以要设置为支持中文的编码

        //3.开始分析请求中携带的上传文件
        ServletRequestContext servletRequestContext = new ServletRequestContext(req); //为请求对象准备上下文环境
        List<FileItem> fileItems = servletFileUpload.parseRequest(servletRequestContext); //开始获取请求中的所有数据

        for (FileItem item : fileItems) {

            //这个方法用来判断当前的请求数据是一般的字符串请求还是文件请求
            //一般字符串请求：true  文件上传请求：false
            if (!item.isFormField()) {

                //如果这是一个文件上传请求，则获得这个请求中携带文件数据的输入流对象
                InputStream is = item.getInputStream();

                //通过IO读写的方式将输入流中字节数据转换到byteArrayOutputStream对象身上
                byte[] buffer = new byte[1024]; //1024byte=1KB
                int len = 0; //用于保存缓存字节的数量
                while ((len = is.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }

                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                is.close();

            }

        }

        //能够将字节数组输出流中保存的字节信息以数组的形式进行返回
        return byteArrayOutputStream.toByteArray();
    }

}
