/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.jdi.connect.spi.Connection;
import entity.Account;
import entity.Book;
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOAccount extends DBConnect {

    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
 public Vector<Account> getAllAccount() {
        String sql = "SELECT * FROM Account WHERE 1=1";
        Vector<Account> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account cus = new Account(
                        rs.getInt("account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        
                        rs.getBoolean("is_admin"),
                        rs.getBoolean("is_employee"),
                        rs.getBoolean("active")
                );
                list.add(cus);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Account getAccountById(int id) {
        String sql = "SELECT * FROM Account WHERE account_id = ?";
        Account cus = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cus = new Account(
                        rs.getInt("account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        rs.getBoolean("is_admin"),
                        rs.getBoolean("is_employee"),
                        rs.getBoolean("active")
                );
                return cus;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cus;
    }

    public Account validateCustomer(String email, String password) {
        String sql = "SELECT * FROM Account where email = ? and password = ?";
        Account acc =  null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new  Account(
                        rs.getInt("account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        
                        rs.getBoolean("is_admin"),
                        rs.getBoolean("is_employee"),
                        rs.getBoolean("active")
                );                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return acc;
    }

    public boolean updatePassword(String newPassword, int accountId) {
        String sql = "UPDATE [dbo].[Account]\n"
                + " SET [password] = ?\n"
                + " WHERE [account_id] = ?";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setInt(2, accountId);
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n > 0;
    }
    
    public void updateAccountImg(String newImg, int accountId) {
        String sql = "UPDATE [dbo].[Account]\n"
                + " SET [account_image] = ?\n"
                + " WHERE [account_id] = ?";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newImg);
            st.setInt(2, accountId);
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean updateAccount(int account_id, String email, String first_name, 
        String last_name, String phone, String account_image, String address) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [email] = ?\n"
                + "      ,[first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[account_image] = ?\n"
                + "      ,[address] = ?\n"
                + " WHERE [account_id] = ?";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, first_name);
            st.setString(3, last_name);
            st.setString(4, phone);
            st.setString(5, account_image);
            st.setString(6, address);
            st.setInt(7, account_id);
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n > 0;
    }
    
    public boolean addAccount(Account acc) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([account_id]\n"
                + "           ,[email]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[password]\n"
                + "           ,[is_admin]\n"
                + "           ,[active])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, 0, 0)\n";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, acc.getAccount_id());
            st.setString(2, acc.getEmail());
            st.setString(3, acc.getFirst_name());
            st.setString(4, acc.getLast_name());
            st.setString(5, acc.getPhone());
            st.setString(6, acc.getPassword());
            n = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n > 0;
    }
    
    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        Account cus = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cus = new Account(
                        rs.getInt("account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("account_image"),
                        rs.getString("address"),
                        
                        rs.getBoolean("is_admin"),
                        rs.getBoolean("is_employee"),
                        rs.getBoolean("active")
                );
                return cus;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cus;
    }

    public void changePassword(String password, int id) throws SQLException {

        String sql = "UPDAte Account\n"
                + "set password=?\n"
                + "where account_id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public static void main(String[] args) throws SQLException {
        DAOAccount dao = new DAOAccount();
        Account acc = dao.getAccountByEmail("thientrieu20002@gmail.com");
        dao.changePassword("123123", acc.getAccount_id());
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("Customer not found.");
        }

    }

}
