package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sen")
public class SensitiveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");//响应头Content-Type，告诉对端响应体的解析方式

        PrintWriter pw = resp.getWriter();
        //敏感资源，需要使用false传参
        HttpSession session = req.getSession(false);
        if(session == null) { //用户没有登陆
            resp.setStatus(401);//没有登陆
            pw.println("没有登陆，不允许访问");
        }else {

            /*伪代码
            User user = (User)session.getAttribute("user");
            if(user.允许访问的资源不包含当前访问的路径) {
                resp.setStatus(403);//没有权限
                pw.println("没有权限，禁止访问");
            }else*/

                pw.println("访问成功");
            }
        }

    }