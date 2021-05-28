package com.baidu.servlet;

import com.baidu.service.AipImageProcessService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AipImageProcessServlet", urlPatterns = "/AipImageProcessServlet")
public class AipImageProcessServlet extends HttpServlet {

    private final AipImageProcessService aipImageProcessService = new AipImageProcessService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        try {

            if ("selfieanime".equals(method)) {
                aipImageProcessService.selfieanime(req, resp);
            }else if ("blackwhite".equals(method)) {
                aipImageProcessService.blackwhite(req, resp);
            }else if ("styleTrans".equals(method)) {
                aipImageProcessService.styleTrans(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
