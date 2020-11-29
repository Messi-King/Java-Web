package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//定义当前类为Servlet（服务端java代码提供的http服务）
//服务路径必须以/开头，否则tomcat启动就会报错
    @WebServlet("/loginUseSession")
    public class LoginUserSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");//响应头Content-Type，告诉对端响应体的解析方式

        //getParameter可以获取url中的queryString请求数据，及请求体表单数据类型格式的请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.printf("username=%s, password=%s%n", username, password);


        PrintWriter pw = resp.getWriter();
        //伪代码：用户登录的jdbc校验
        // 作业：完成用户登录Servlet+JDBC的代码，返回数据要求是JSON格式的数据
        /*if(LoginDAO.query(username, password)){
            pw.println("登录成功");
        }else{
            pw.println("登录失败");
        }
        ObjectMapper*/

        //模拟登陆
        if ("abc".equals(username) && "123".equals(password)) {
            //获取Session对象(从服务端)
            //如果方法参数为true，表示没有，就创建一个；false就返回null
            //无参就是true
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", String.format("username=%s, password=%s", username, password));
            pw.println("登陆成功");
        } else {
            pw.println("登陆失败");
        }
        pw.flush();
        pw.close();
    }
}