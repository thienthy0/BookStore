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
import entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEmployee {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Employee> getAllEmployee() {
        List<Employee> listEmployee = new ArrayList<>();
        String sql = "SELECT e_id, e_name, position, e_email, e_phone, e_address, gender, DOB FROM Employee";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int e_id = rs.getInt(1);
                String e_name = rs.getString(2);
                int position = rs.getInt(3);
                String e_email = rs.getString(4);
                String e_phone = rs.getString(5);
                String e_address = rs.getString(6);
                boolean gender = rs.getBoolean(7);
                String DOB = rs.getString(8);
                listEmployee.add(new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return listEmployee;
    }

    public void deleteEmployee(int e_id) {
        String sql = "DELETE FROM Employee WHERE e_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e_id);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public void updateEmployee(int e_id, String e_name, int position, String e_email, String e_phone, String e_address, boolean gender, String DOB) {
        String sql = "UPDATE Employee SET e_name = ?, position = ?, e_email = ?, e_phone = ?, e_address = ?, gender = ?, DOB = ? WHERE e_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, e_name);
            ps.setInt(2, position);
            ps.setString(3, e_email);
            ps.setString(4, e_phone);
            ps.setString(5, e_address);
            ps.setBoolean(6, gender);
            ps.setString(7, DOB);
            ps.setInt(8, e_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public void createEmployee(Employee e) {
        String sql = "INSERT INTO Employee (e_id, e_name, position, e_email, e_phone, e_address, gender, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
             conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getE_id());
            ps.setString(2, e.getE_name());
            ps.setInt(3, e.getPosition());
            ps.setString(4, e.getE_email());
            ps.setString(5, e.getE_phone());
            ps.setString(6, e.getE_address());
            ps.setBoolean(7, e.isGender());
            ps.setString(8, e.getDOB());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT e_id, e_name, position, e_email, e_phone, e_address, gender, DOB FROM Employee WHERE e_id = ?";
        try {
           conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int e_id = rs.getInt("e_id");
                String e_name = rs.getString("e_name");
                int position = rs.getInt("position");
                String e_email = rs.getString("e_email");
                String e_phone = rs.getString("e_phone");
                String e_address = rs.getString("e_address");
                boolean gender = rs.getBoolean("gender");
                String DOB = rs.getString("DOB");
                return new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB);
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return null;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        DaoEmployee dao = new DaoEmployee();
        List<Employee> list=dao.getAllEmployee();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
