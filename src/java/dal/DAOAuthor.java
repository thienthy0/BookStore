package dal;

import entity.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DAOAuthor extends DBConnect {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT author_id, author_name FROM Author";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                authors.add(new Author(rs.getInt("author_id"), rs.getString("author_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return authors;
    }

    public Vector<Author> getAllAuthor() {
        Vector<Author> list = new Vector<>();
        String sql = "Select * From Author";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name")
                );
                list.add(author);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
