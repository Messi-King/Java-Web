package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //通过session获取用户id
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        //请求数据类型为application/json,需要从输入流获取
        InputStream is = req.getInputStream();
        //请求体数据存在于输入流，json格式需要反序列化
        Article article = JSONUtil.deserialize(is, Article.class);
        article.setUserId(user.getId());
        int num = ArticleDAO.insert(article);
        return null;
    }
}




 public static int insert(Article article) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            String sql = "insert into article(title, content,user_id values(?, ?, ?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setInt(3, article.getUserId());
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("ART005", "插入文章操作出错", e);
        }finally {
            DBUtil.close(c, ps);
        }
    }