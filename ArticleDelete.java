package org.example.servlet;

import org.example.dao.ArticleDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ids = req.getParameter("ids");//获取Http传过来的数据
        int num = ArticleDAO.delete(ids.split(","));
        return null;
    }
}



public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");
            for(int i=0; i<ids.length; i++){
                if(i != 0) {
                    sql.append(",");
                    sql.append("?");
                } else {
                    sql.append("?");
                }
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());
            //设置占位符：替换值
            for(int i=0; i<ids.length; i++){
                ps.setInt(i+1, Integer.parseInt(ids[i]));
            }
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("ART004", "删除文章出错", e);
        }finally {
            DBUtil.close(c, ps);
        }
    }