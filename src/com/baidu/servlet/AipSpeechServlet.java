package com.baidu.servlet;

import com.baidu.service.AipSpeechService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AipSpeechServlet", urlPatterns = "/AipSpeechServlet")
public class AipSpeechServlet extends HttpServlet {

    private final AipSpeechService aipSpeechService = new AipSpeechService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String method = req.getParameter("method");

        try {
            if (method.equals("synthesis")) {
                aipSpeechService.synthesis(req,resp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
