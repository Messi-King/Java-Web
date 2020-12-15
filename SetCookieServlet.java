package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setHeader("set-Cookie","k1=v1");
        Cookie cookie = new Cookie("k2","v2");//创建cookie对象
        resp.addCookie(cookie);//添加cookie到响应中
    }
}
