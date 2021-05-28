package com.baidu.servlet;

import com.baidu.service.AipFaceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AipFaceServlet", urlPatterns = "/AipFaceServlet")
public class AipFaceServlet extends HttpServlet {

    private final AipFaceService aipFaceService = new AipFaceService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        try {
            if ("faceDetection".equals(method)) {
                aipFaceService.faceDetection(req, resp);
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
