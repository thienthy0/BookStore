/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author HP
 */
public class DAOOrder extends DBConnect {

    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM Orders O JOIN Account A ON A.account_id = O.account_id WHERE O.o_id = ?";
        Order order = null;
        DAOOrderDetail d = new DAOOrderDetail();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt("account_id"),
                        rs.getInt("role_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        rs.getString("description"),
                        rs.getString("Dateofbirth"),
                        rs.getBoolean("gender")
                );
                order = new Order(
                        rs.getInt("o_id"),
                        rs.getString("status"),
                        rs.getDate("order_date"),
                        rs.getDate("receive_date"),
                        d.getOrderItemByOrderId(rs.getInt("o_id")),
                        acc);
                return order;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return order;
    }

    public Vector<Order> getAllOrders() {
        String sql = "select * from Orders ";
        Vector<Order> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("o_id"),
                        rs.getInt("account_id"),
                        rs.getString("status"),
                        rs.getDate("order_date"),
                        rs.getDate("receive_date")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //check ham nay
    public Vector<Order> getAllOrder() {
        String sql = "select * from Orders O\n"
                + " JOIN Account A on A.account_id = O.account_id";
        Vector<Order> list = new Vector<>();
        Vector<OrderDetail> listItem = new Vector<>();
        DAOOrderDetail d = new DAOOrderDetail();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt("account_id"),
                        rs.getInt("role_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        rs.getString("description"),
                        rs.getString("Dateofbirth"),
                        rs.getBoolean("gender")
                );
                Order order = new Order(
                        rs.getInt("o_id"),
                        rs.getString("status"),
                        rs.getDate("order_date"),
                        rs.getDate("receive_date"),
                        d.getOrderItemByOrderId(rs.getInt("o_id")),
                        acc);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static void main(String[] args) {
        DAOOrder Dao = new DAOOrder();
        System.out.println(Dao.getAllOrder());
    }
    public Vector<Order> getOrderByStatus(String status) {
        String sql = "SELECT * FROM Orders O JOIN Account A ON A.account_id = O.account_id WHERE O.status LIKE ?";

        Vector<Order> list = new Vector<>();
        Vector<OrderDetail> listOrder = new Vector<>();
        DAOOrderDetail d = new DAOOrderDetail();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt("account_id"),
                        rs.getInt("role_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        rs.getString("description"),
                        rs.getString("Dateofbirth"),
                        rs.getBoolean("gender")
                );
                Order order = new Order(
                        rs.getInt("o_id"),
                        rs.getString("status"),
                        rs.getDate("order_date"),
                        rs.getDate("receive_date"),
                        d.getOrderItemByOrderId(rs.getInt("o_id")),
                        acc);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }


    
    public Vector<Order> getAllOrderByAccount(int accId) {
        String sql = "SELECT * FROM Orders O JOIN Account A ON A.account_id = O.account_id WHERE A.account_id = ? ORDER BY order_date DESC";

        Vector<Order> list = new Vector<>();
        DAOOrderDetail d = new DAOOrderDetail();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt("account_id"),
                        rs.getInt("role_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        rs.getString("description"),
                        rs.getString("Dateofbirth"),
                        rs.getBoolean("gender")
                );
                Order order = new Order(
                        rs.getInt("o_id"),
                        rs.getString("status"),
                        rs.getDate("order_date"),
                        rs.getDate("receive_date"),
                        d.getOrderItemByOrderId(rs.getInt("o_id")),
                        acc);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getFormatDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public boolean updateStatusOrder(String status, int orderId) {
        String sql;
        boolean isDone = false;
        int n = 0;
        if (status.equals("done")) {
            isDone = true;
            sql = "UPDATE [dbo].[Orders]\n"
                    + "   SET [status] = ?,"
                    + "       [receive_date] = ?"
                    + " WHERE [o_id] = ?";
        } else {
            sql = "UPDATE [dbo].[Orders]\n"
                    + "   SET [status] = ?"
                    + " WHERE [o_id] = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            //if done will set recieve_date
            if (isDone) {
                st.setTimestamp(2, Timestamp.valueOf(getFormatDate()));
                st.setInt(3, orderId);
            } else {
                st.setInt(2, orderId);
            }
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n > 0;
    }
        

    //checkount
    public void checkcount(Account acc, Vector<Book> listItem) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        int newOrderId = getAllOrders().get(getAllOrders().size() - 1).getO_id() + 1;
        try {
            String sql1 = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([o_id]\n"
                    + "           ,[account_id]\n"
                    + "           ,[order_date]\n"
                    + "           ,[status])\n"
                    + "     VALUES(?, ?, ?, ?)\n";
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, newOrderId);
            st.setInt(2, acc.getAccount_id());
            st.setTimestamp(3, Timestamp.valueOf(formattedDate));
            st.setString(4, "wait");
            st.executeUpdate();
            //insert into order item
            String sql2 = "SELECT TOP 1 order_id FROM [orders] ORDER BY order_id DESC";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            ResultSet rs = st2.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                DAOOrderDetail Dod = new DAOOrderDetail();
                //get last order Item
                int newOrderItemId = Dod.getAllOrderItem().get(Dod.getAllOrderItem().size() - 1).getDetail_id() + 1;
                //add all order in cart to database
                for (Book item : listItem) {
                    String sql3 = "INSERT INTO [dbo].[Order_details]\n"
                            + "           ([item_id]\n"
                            + "           ,[order_id]\n"
                            + "           ,[product_id]\n"
                            + "           ,[quantity]\n"
                            + "           ,[list_price]\n"
                            + "           ,[discount])\n"
                            + "     VALUES(?, ?, ?, ?, ?, ?)";
                    PreparedStatement st3 = connection.prepareStatement(sql3);
                    st3.setInt(1, newOrderItemId);
                    st3.setInt(2, orderId);
                    st3.setInt(3, item.getId());
                    st3.setInt(4, item.getQuantity());
                    st3.setDouble(5, item.getPrice());
                    st3.setInt(6, item.getDiscount());
                    st3.executeUpdate();
                    newOrderItemId++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
}
