package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取文章id
        String id = req.getParameter("id");
        //根据文章id查询文章详细信息
        Article article = ArticleDAO.query(Integer.parseInt(id));
        return article;
    }
}



 public static Article query(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select title, content from article where id=?";
            ps = c.prepareStatement(sql);
            //设置占位符：替换值
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Article article = null;
            while (rs.next()){
                article = new Article();
                article.setId(id);
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
            }
            return article;
        }catch (Exception e){
            throw new AppException("ART006", "查询文章详情出错", e);
        }finally {
            DBUtil.close(c, ps, rs);
        }
    }