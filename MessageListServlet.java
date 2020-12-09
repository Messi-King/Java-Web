package Servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/messagelist")
public class MessageListServlet extends HttpServlet {
    private static DataSource dataSource;

    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("0000");
        mysqlDataSource.setDatabaseName("db2020");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        dataSource = mysqlDataSource;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messageList = getMessageList();
        String jsonMessage = buildJSONByJackson(messageList);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        writer.println(jsonMessage);
    }
    private String buildJSONByJackson(List<Message> messageList) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(messageList);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
    private List<Message> getMessageList() {
        List<Message> messageList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, who, `when`, what FROM message ORDER BY id DESC";
            try (PreparedStatement s = connection.prepareStatement(sql)) {
                try (ResultSet rs = s.executeQuery()) {
                    while (rs.next()) {
                        Message message = new Message();

                        message.id = rs.getInt("id");
                        message.who = rs.getString("who");
                        message.when = rs.getString("when");
                        message.what = rs.getString("what");

                        messageList.add(message);
                    }
                    return messageList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
