/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Order;
import entity.OrderDetail;
import entity.Book;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author HP
 */
public class DAOOrderDetail extends DBConnect {

    public Vector<OrderDetail> getOrderDetail(int OrderId) {
        String sql = "SELECT * FROM Order_details O\n"
                + "JOIN Book B ON B.book_id = O.book_id\n"
                + "WHERE O.o_id = ?";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("name"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("author_id"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getString("category_id"),
                        rs.getString("publisher"),
                        rs.getString("description"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("discount")
                );

                OrderDetail OI = new OrderDetail(
                        rs.getInt("detail_id"),
                        rs.getInt("discount"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        book
                );
                list.add(OI);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAOOrderDetail Dao = new DAOOrderDetail();
        System.out.println(Dao.getAllOrderItem());
    }

    public Vector<OrderDetail> getAllOrderDetail() {
        String sql = "SELECT * FROM Order_details O JOIN Book B ON B.book_id = O.book_id";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book Book = new Book(
                        rs.getString("name"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("author_id"),
                        rs.getString("image"),
                        rs.getString("language"),
                        rs.getString("category_id"),
                        rs.getString("publisher"),
                        rs.getString("description"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("discount")
                );

                OrderDetail OD = new OrderDetail(
                        rs.getInt("book_id"),
                        rs.getInt("o_id"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getInt("discount"),
                        Book
                );
                list.add(OD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Vector<OrderDetail> getOrderItemByOrderId(int OrderId) {
        String sql = "Select * from Order_details where o_id = ?";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail OD = new OrderDetail(
                        rs.getInt("detail_id"),
                        rs.getInt("o_id"),
                        rs.getInt("book_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                );
                list.add(OD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Vector<OrderDetail> getAllOrderItem() {
        String sql = "SELECT * FROM [Order_details]";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail OI = new OrderDetail(
                        rs.getInt("detail_id"),
                        rs.getInt("o_id"),
                        rs.getInt("book_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                );
                list.add(OI);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
