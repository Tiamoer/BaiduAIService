package com.baidu.servlet;

import com.baidu.service.AipNlpService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AipNlpServlet用于自然语言处理功能的操作
 * 1.情感分析
 * 2.地址分析
 */
@WebServlet(name = "AipNlpServlet",urlPatterns = "/AipNlpServlet")
public class AipNlpServlet extends HttpServlet {

    //实例化自然语言处理的业务逻辑层
    AipNlpService aipNlpService = new AipNlpService();

    /**
     * @param req 请求对象，携带了从前端发来的所有的数据
     * @param resp 响应对象，通过这个对象将响应的结果发送给前端页面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String method = req.getParameter("method");

        try {
            if ("sentimentClassify".equals(method)) {
                aipNlpService.sentimentClassify(req,resp);
            } else if ("address".equals(method)) {
                aipNlpService.address(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
