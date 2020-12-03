package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActicleDAO {
    public static List<Article> queryByUserId(int userId) {
        List<Article> articles = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select id, title, content, user_id, create_time," +
                    " view_count from article where user_id=?";
            ps = c.prepareStatement(sql);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                articles.add(a);
            }
            return articles;
        }catch (Exception e) {
            throw new AppException("ART001","查询出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
    }
}
