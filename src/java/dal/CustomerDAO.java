
package dal;


import entity.Book;
import entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CustomerDAO extends DBConnect {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Customer login(String Email, String Password) {
        try {
            String query = "SELECT *\n"
                    + "  FROM Customer where [Email] = ? and [Password] = ?";
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, Email);
            ps.setString(2, Password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getDate(7),
                        rs.getInt(8));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public void updatePassword(String email, String newpass) {
        PreparedStatement ps = null;
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [Password] = ?\n"
                + " WHERE Email = ?";
        try {
            try {
                ps = new DBConnect().connection.prepareStatement(sql);
            } catch (Exception ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setString(1, newpass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
//
//    public Customer getUserById(int id) throws Exception {
//
//        try {
//
//            String sql = "SELECT Customer_ID"
//                    + ", [Name]\n"
//                    + "      ,[Phone_Number]\n"
//                    + "      ,[Address]\n"
//                    + "      ,[Gender]\n"
//                    + "      ,[Email]\n"
//                    + "      ,[DOB]\n"
//                    + "      ,[Salary]\n"
//                    + "      ,[CitizenID]\n"
//                    + "      ,[Username]\n"
//                    + "      ,[Password]\n"
//                    + "  \n"
//                    + "  FROM Customer\n"
//                    + "  where Customer_ID =?";
//            Connection connection = this.connection;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                return new Customer(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getBoolean(5),
//                        rs.getString(6),
//                        rs.getDate(7),
//                        rs.getDouble(8),
//                        rs.getString(9),
//                        rs.getString(10),
//                        rs.getString(11)
//                );
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return null;
//    }

    // Edit profile
//    public void editUser(int id, String email, String fname, String gender, Date dob, String address, String phoneNum, double Salary, String citizenid) throws SQLException, Exception {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "UPDATE [Customer]\n"
//                + "   SET [Name] = ?\n"
//                + "      ,[Phone_Number] = ?\n"
//                + "      ,[Address] = ?\n"
//                + "      ,[Gender] = ?\n"
//                + "      ,[Email] = ?\n"
//                + "      ,[DOB] = ?\n"
//                + "      ,[Salary] = ?\n"
//                + "      ,[CitizenID] = ?\n"
//                + " WHERE [Customer_ID]= ?";
//        try {
//            ps = getConnection().prepareStatement(sql);
//            ps.setString(1, fname);
//            ps.setString(2, phoneNum);
//            ps.setString(3, address);
//            ps.setString(4, gender);
//            ps.setString(5, email);
//            ps.setDate(6, new java.sql.Date(dob.getTime()));
//            ps.setDouble(7, Salary);
//            ps.setString(8, citizenid);
//            ps.setInt(9, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
//        } finally {
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//    }

    //Update cOTP of Customer
    public void updatcOTPinDatabase(String email, String otp) {
        String query = "UPDATE Customer SET cOTP = ? WHERE Email = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, otp);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Get Customer by email
    public List<Customer> getCustomerByEmail(String email) {
        List<Customer>list=new ArrayList<>();
        String query = "SELECT * FROM Customer WHERE Email = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                 list.add(new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getDate(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
        
    }

    //Check cOTP is valid
    public boolean isCOTPValid(String cOTP) {
        String query = "SELECT COUNT(*) FROM Customer WHERE [cOTP] = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, cOTP);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu cOTP hợp lệ
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    //Change Customer Pass OTP
    public boolean changeCPassOTP(String cOTP, String newPassword) {
        String query = "update Account\n"
                + "set [Password] = ?\n"
                + "where [cOTP] = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, cOTP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
         List<Customer> list=dao.getCustomerByEmail("thientrieu20002@gmail.com");
        for (Customer o : list) {
            System.out.println(o);
       }
        
    }

}
