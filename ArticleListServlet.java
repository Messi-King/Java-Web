package org.example.servlet;

import org.example.dao.ActicleDAO;
import org.example.exception.AppException;
import org.example.model.Article;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //String id = req.getParameter("id");
        HttpSession session = req.getSession(false);
        if(session == null) {
            throw new AppException("ART002","没有登陆");
        }
        User user = (User) session.getAttribute("user");
        if(user == null) {
            throw new AppException("ART003","没有登陆，不允许访问");
        }
        List<Article> articles = ActicleDAO.queryByUserId(user.getId());
                return articles;
    }
}
