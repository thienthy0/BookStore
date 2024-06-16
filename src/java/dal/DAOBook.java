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
        String query = "select b.name,b.book_id,b.quantity,b.price,a.author_name,b.image,b.language,c.category_name,b.publisher,b.description,b.number_of_pages,b.discount\n"
                + "from Book b , Category c ,Author a\n"
                + "where c.category_id=b.category_id and a.author_id=b.author_id";
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
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12)));
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
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
        }

        return list;

    }

    public List<Book> getProductbyName(String name) {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM Book WHERE name LIKE ?";

        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%"); // Correct way to include wildcards
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {

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
            } catch (Exception e) {

            }
        }

        return list;
    }

    public List<Book> getBooksByCategoryWithPagination(int categoryId, int page, int pageSize) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE category_id = ? LIMIT ? OFFSET ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Book(rs.getString(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(12)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getProductById(int pid) {
        String sql = "Select * from Book B \n"
                + "inner join Author A on A.author_id = B.author_id\n"
                + "inner join Category C on C.category_id = B.category_id\n"
                + "WHERE B.book_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Book(
                        rs.getString("name"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("author_name"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getString("category_name"),
                        rs.getString("publisher"),
                        rs.getString("description"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("discount")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void addProduct(Book pro) {
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([name]\n"
                + "           ,[book_id]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[author_id]\n"
                + "           ,[image]\n"
                + "           ,[language]\n"
                + "           ,[category_id]\n"
                + "           ,[publisher]\n"
                + "           ,[number_of_pages])\n"
                + "           ,[description])\n"
                + "           ,[discount])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro.getName());
            st.setInt(2, pro.getId());
            st.setInt(3, pro.getQuantity());
            st.setInt(4, pro.getPrice());
            st.setString(5, "1");
            st.setString(6, pro.getImage());
            st.setString(7, pro.getLanguage());
            st.setString(8, pro.getCategory());
            st.setString(9, pro.getPublisher());
            st.setString(10, pro.getDescription());
            st.setInt(11, pro.getNum_of_page());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int deleteProduct(int pid) {
        String sql = "DELETE FROM [dbo].[Book]\n"
                + " WHERE book_id = ?";
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
        String sql = "UPDATE [dbo].[Book]\n"
                + "SET \n"
                + "    [name] = ?,\n"
                + "    [quantity] = ?,\n"
                + "    [price] = ?,\n"
                + "    [author_id] = ?,\n"
                + "    [image] = ?,\n"
                + "    [language] = ?,\n"
                + "    [category_id] = ?,\n"
                + "    [publisher] = ?,\n"
                + "    [number_of_pages] = ?\n"
                + "    [discount] = ?\n"
                + "WHERE \n"
                + "    [book_id] = ?";
        if (!pro.getImage().equals("")) {
            sql = "UPDATE [dbo].[Book]\n"
                    + "SET \n"
                    + "    [name] = ?,\n"
                    + "    [quantity] = ?,\n"
                    + "    [price] = ?,\n"
                    + "    [author_id] = ?,\n"
                    + "    [image] = ?,\n"
                    + "    [language] = ?,\n"
                    + "    [category_id] = ?,\n"
                    + "    [publisher] = ?,\n"
                    + "    [number_of_pages] = ?\n"
                    + "    [discount] = ?\n"
                    + "WHERE \n"
                    + "    [book_id] = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (!pro.getImage().equals("")) {
                st.setString(1, pro.getName());
                st.setInt(2, pro.getQuantity());
                st.setInt(3, pro.getPrice());
                st.setString(4, "1"); // Assuming author_id is stored as a string in getAuthor()
                st.setString(5, pro.getImage());
                st.setString(6, pro.getLanguage());
                st.setString(7, pro.getCategory());
                st.setString(8, pro.getPublisher());
                st.setInt(9, pro.getNum_of_page());
                st.setInt(10, pro.getId());
            } else {
                st.setString(1, pro.getName());
                st.setInt(2, pro.getQuantity());
                st.setInt(3, pro.getPrice());
                st.setString(4, "1"); // Assuming author_id is stored as a string in getAuthor()
//            st.setString(5, pro.getImage());
                st.setString(5, pro.getLanguage());
                st.setString(6, pro.getCategory());
                st.setString(7, pro.getPublisher());
                st.setInt(8, pro.getNum_of_page());
                st.setInt(9, pro.getId());
            }
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public Vector<Book> search(String[] category_name, String[] author_name, String minPrice, String maxPrice, String discount, String order) {
    Vector<Book> list = new Vector<>();
    String sql = "SELECT * FROM Book B "
            + "JOIN Category C ON C.category_id = B.category_id "
            + "JOIN Author A ON A.author_id = B.author_id WHERE 1=1 ";

    // Filter by categories
    if (category_name != null && category_name.length > 0) {
        sql += " AND C.category_name IN (";
        for (int i = 0; i < category_name.length; i++) {
            sql += "N'" + category_name[i] + "'";
            if (i < category_name.length - 1) {
                sql += ",";
            }
        }
        sql += ")";
    }

    // Filter by authors
    if (author_name != null && author_name.length > 0) {
        sql += " AND A.author_name IN (";
        for (int i = 0; i < author_name.length; i++) {
            sql += "N'" + author_name[i] + "'";
            if (i < author_name.length - 1) {
                sql += ",";
            }
        }
        sql += ")";
    }

    // Filter by price range considering discount
    if ((minPrice != null && maxPrice != null) && (!minPrice.isEmpty() && !maxPrice.isEmpty())) {
        sql += " AND ((B.price - COALESCE(B.price * B.discount / 100, 0)) >= " + minPrice
                + " AND (B.price - COALESCE(B.price * B.discount / 100, 0)) <= " + maxPrice + ")";
    } else if (minPrice != null && !minPrice.isEmpty()) {
        sql += " AND ((B.price - COALESCE(B.price * B.discount / 100, 0)) >= " + minPrice + ")";
    } else if (maxPrice != null && !maxPrice.isEmpty()) {
        sql += " AND ((B.price - COALESCE(B.price * B.discount / 100, 0)) <= " + maxPrice + ")";
    }

    // Filter by discount
    if (discount != null && !discount.isEmpty()) {
        sql += " AND COALESCE(B.discount, 0) >= " + discount;
    }

    // Order by price
    if (order != null && !order.isEmpty() && !order.equals("default")) {
        sql += " ORDER BY (B.price - COALESCE(B.price * B.discount / 100, 0)) " + order;
    }

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Author author = new Author(
                    rs.getInt("author_id"),
                    rs.getString("author_name")
            );
            Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
            );
            Book p = new Book(
                    rs.getString("name"),
                    rs.getInt("book_id"),
                    rs.getInt("quantity"),
                    rs.getInt("price"),
                    rs.getString("author_id"),
                    rs.getString("image"),
                    rs.getString("language"),
                    rs.getString("category_name"),
                    rs.getString("publisher"),
                    rs.getString("description"),
                    rs.getInt("number_of_pages"),
                    rs.getInt("discount"),
                    author,
                    category
            );
            list.add(p);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

    return list;
}

    public Vector<Book> getBookByName(String name) {
        String sql = "Select * from Book B\n"
                + "join Category C on C.category_id = B.category_id\n"
                + "join Author A on A.author_id = B.author_id WHERE B.name like N'%" + name + "%'";
        Vector<Book> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name")
                );
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                Book p = new Book(
                        rs.getString("name"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("author_id"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getString("category_name"),
                        rs.getString("publisher"),
                        rs.getString("description"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("discount"),
                        author,
                        category
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Vector<Book> getAllProduct(String orderType) {
        String sql = "Select * from Book B\n"
                + " join Category C on C.category_id = B.category_id\n"
                + " join Author A on A.author_id = B.author_id";
        if (orderType != null && !orderType.equals("")) {
            sql += " order by B.price " + orderType;
        }
        Vector<Book> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name")
                );
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                Book p = new Book(
                        rs.getString("name"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("author_id"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getString("category_name"),
                        rs.getString("publisher"),
                        rs.getString("description"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("discount"),
                        author,
                        category
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
