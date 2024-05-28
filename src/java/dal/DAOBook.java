/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Author;
import entity.Book;
import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laptop368
 */
public class DAOBook extends DBConnect {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Book> getAllProduct() {
        List<Book> list = new ArrayList<>();
        String query = "select * from Book";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)));
            }
        } catch (Exception e) {
        }

        return list;

    }

    public List<Book> getProductbyCID(String category_id) {
        List<Book> list = new ArrayList<>();
        String query = "select * from Book\n"
                + "where category_id=?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, category_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Book(rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)));
            }
        } catch (Exception e) {
        }

        return list;

    }

    public static void main(String[] args) {
        DAOBook dao = new DAOBook();
        List<Book> list = dao.getProductbyCID("1");
        for (Book o : list) {
            System.out.println(o);
        }
    }

    public Book getBookById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Vector<Book> getAllProduct(String start, String numPP) throws SQLException {
            String sql = "SELECT P.[name], P.[id], P.[quantity], P.[price], P.[author_id], P.[image], P.[language], " +
                 "P.[category_id], C.[category_name], P.[publisher], P.[number_of_pages] " +
                 "FROM [Bookstore].[dbo].[Book] P " +
                 "JOIN [Bookstore].[dbo].[Category] C ON C.[category_id] = P.[category_id] " +
                 "ORDER BY P.[id] " +
                 "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        Vector<Book> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author brand = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name")
                );
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                Book p = new Book(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getString("author"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getInt("category"),
                        rs.getString("publisher"),
                        rs.getInt("num of page")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Book getProductById(int pid) {
        String sql = "Select * from Book P\n"
                + "join categorys C on C.category_id = P.category_id\n"
                + "join brands B on B.brand_id = P.brand_id WHERE P.product_id = ?";
        Book p = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author brand = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name")
                );
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                p = new Book(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getString("author"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getInt("category"),
                        rs.getString("publisher"),
                        rs.getInt("num of page")
                );
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

    public void addProduct(Book pro) {
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([name]\n"
                + "           ,[id]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[author_id]\n"
                + "           ,[image]\n"
                + "           ,[language]\n"
                + "           ,[category_id]\n"
                + "           ,[publisher]\n"
                + "           ,[number_of_pages])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro.getName());
            st.setInt(2, pro.getId());
            st.setInt(3, pro.getQuantity());
            st.setInt(4, pro.getPrice());
            st.setString(5, pro.getAuthor()); // Assuming author_id is stored as a string in getAuthor()
            st.setString(6, pro.getImage());
            st.setString(7, pro.getLanguage());
            st.setInt(8, pro.getCategory());
            st.setString(9, pro.getPublisher());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int deleteProduct(int pid) {
        String sql = "DELETE FROM [dbo].[book]\n"
                + " WHERE id = ?";
        int n = -1;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;

    }

    public void updateProduct(Book pro) {
        String sql = "INSERT INTO [dbo].[Book] " +
                 "([name], [id], [quantity], [price], [author_id], [image], [language], [category_id], [publisher], [number_of_pages]) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro.getName());
            st.setInt(2, pro.getId());
            st.setInt(3, pro.getQuantity());
            st.setInt(4, pro.getPrice());
            st.setString(5, pro.getAuthor()); // Assuming author_id is stored as a string in getAuthor()
            st.setString(6, pro.getImage());
            st.setString(7, pro.getLanguage());
            st.setInt(8, pro.getCategory());
            st.setString(9, pro.getPublisher());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public Vector<Book> getAllProduct(String orderType) throws SQLException {
        String sql = "Select * from Book P\n"
                + " join categorys C on C.category_id = P.category_id\n"
                + " join brands B on B.brand_id = P.brand_id";
        if (orderType != null && !orderType.equals("")) {
            sql += " order by P.list_price " + orderType;
        }
        Vector<Book> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author brand = new Author(
                        rs.getInt("Author_id"),
                        rs.getString("Author_name")
                );
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                Book p;
                p = new Book(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getString("author"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getInt("category"),
                        rs.getString("publisher"),
                        rs.getInt("num of page")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }
}
