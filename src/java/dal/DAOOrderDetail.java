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
        String sql = "SELECT * FROM Order_items O\n"
                + "JOIN Products P ON P.product_id = O.product_id\n"
                + "WHERE O.order_id = ?";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book Pro = new Book(
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
                
                OrderDetail OD = new OrderDetail(
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getInt("item_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        Pro
                );
                list.add(OD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Vector<OrderDetail> getAllOrderDetail() {
        String sql = "SELECT * FROM Order_items O\n"
                + "JOIN Products P ON P.product_id = O.product_id";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book Pro = new Book(
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
                
                OrderDetail OI = new OrderDetail(
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getInt("item_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        Pro
                );
                list.add(OI);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Vector<OrderDetail> getOrderItemByOrderId(int OrderId) {
        String sql = "Select * from order_items where order_id = ?";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail OD = new OrderDetail(
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getInt("item_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity")
                );
                list.add(OD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Vector<OrderDetail> getAllOrderItem() {
        String sql = "SELECT * FROM [order_items]";
        Vector<OrderDetail> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail OI = new OrderDetail(
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getString("item_id"),
                        rs.getInt("item_id"),
                        rs.getInt("discount"),
                        rs.getInt("quantity")
                );
                list.add(OI);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
