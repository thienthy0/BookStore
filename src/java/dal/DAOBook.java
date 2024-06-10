/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Book;
import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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


    public static void main(String[] args) {
        DAOBook dao = new DAOBook();
        List<Book> list = dao.getProductbyCID("1");
        for (Book o : list) {
            System.out.println(o);
        }
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
        String sql = "Select * from Book P \n"
                + "inner join Author A on A.author_id = P.author_id\n"
                + "inner join Category C on C.category_id = P.category_id\n"
                + "WHERE P.book_id = ?";
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
                //                + "    [image] = ?,\n"
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

    public List<Book> getBooksByCategory(List<Integer> categoryIds) {
        List<Book> list = new ArrayList<>();
        if (categoryIds.isEmpty()) {
            return list;
        }
        StringBuilder queryBuilder = new StringBuilder("SELECT b.name, b.book_id, b.quantity, b.price, a.author_name, b.image, b.language, c.category_name, b.publisher,b.description, b.number_of_pages, b.discount "
                + "FROM Book b, Category c, Author a "
                + "WHERE c.category_id = b.category_id AND a.author_id = b.author_id AND b.category_id IN (");

        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");

        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(queryBuilder.toString());
            for (int i = 0; i < categoryIds.size(); i++) {
                ps.setInt(i + 1, categoryIds.get(i));
            }
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
        return list;
    }
    public List<Book> getBooksByCategoryAndAuthor(List<Integer> categoryIds, List<Integer> authorIds) {
        List<Book> list = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT b.name, b.book_id, b.quantity, b.price, a.author_name, b.image, b.language, c.category_name, b.publisher, b.description, b.number_of_pages, b.discount "
                + "FROM Book b, Category c, Author a "
                + "WHERE c.category_id = b.category_id AND a.author_id = b.author_id");

        if (!categoryIds.isEmpty() || !authorIds.isEmpty()) {
            queryBuilder.append(" AND (");

            if (!categoryIds.isEmpty()) {
                queryBuilder.append("b.category_id IN (");
                for (int i = 0; i < categoryIds.size(); i++) {
                    queryBuilder.append("?");
                    if (i < categoryIds.size() - 1) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(")");
            }

            if (!authorIds.isEmpty()) {
                if (!categoryIds.isEmpty()) {
                    queryBuilder.append(" OR ");
                }
                queryBuilder.append("b.author_id IN (");
                for (int i = 0; i < authorIds.size(); i++) {
                    queryBuilder.append("?");
                    if (i < authorIds.size() - 1) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(")");
            }

            queryBuilder.append(")");
        }

        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(queryBuilder.toString());

            int index = 1;
            for (int categoryId : categoryIds) {
                ps.setInt(index++, categoryId);
            }
            for (int authorId : authorIds) {
                ps.setInt(index++, authorId);
            }

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
        return list;
    }
}
