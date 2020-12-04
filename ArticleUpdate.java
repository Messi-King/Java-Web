package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        InputStream is = req.getInputStream();
        Article article = JSONUtil.deserialize(is, Article.class);
        int num = ArticleDAO.update(article);
        return null;
    }
}




public static int update(Article article) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            String sql = "update article set title=?, content=? where id=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setInt(3, article.getId());
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("ART007", "修改文章操作出错", e);
        }finally {
            DBUtil.close(c, ps);
        }
    }
}