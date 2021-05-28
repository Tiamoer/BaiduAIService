package com.baidu.servlet;

import com.baidu.service.AipOcrService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AipOcrServlet", urlPatterns = "/AipOcrServlet")
public class AipOcrServlet extends HttpServlet {

    private final AipOcrService aipOcrService = new AipOcrService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        try {
            if ("idcard".equals(method)) {
                aipOcrService.idcard(req, resp);
            } else if ("textdetect".equals(method)) {
                aipOcrService.textdect(req, resp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}
