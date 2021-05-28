package com.baidu.servlet;

import com.baidu.service.AipDistinguishService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *  通用物体识别 Servlet类
 */

@WebServlet(name = "AipDistinguishServlet", urlPatterns = "/AipDistinguishServlet")
public class AipDistinguishServlet extends HttpServlet {

    private final AipDistinguishService aipDistinguishService = new AipDistinguishService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        try{
            if ("ObjectReco".equals(method)){
                aipDistinguishService.objectReco(req, resp);
            } else if ("animal".equals(method)) {
                aipDistinguishService.animalDetect(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
