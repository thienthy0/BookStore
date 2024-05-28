package dal;

import entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmployee {

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
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return listEmployee;
    }

    public int countEmployees() {
        String sql = "SELECT COUNT(*) FROM Employee";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Employee> getEmployeesByPage(int pageIndex, int pageSize) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e_id, e_name, position, e_email, e_phone, e_address, gender, DOB FROM Employee ORDER BY e_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (pageIndex - 1) * pageSize);
            ps.setInt(2, pageSize);
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
                list.add(new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return list;
    }

    public void deleteEmployee(int e_id) {
        String sql = "DELETE FROM Employee WHERE e_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e_id);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public void createEmployee(Employee e) {
        String sql = "INSERT INTO Employee (e_name, position, e_email, e_phone, e_address, gender, DOB) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, e.getE_name());
            ps.setInt(2, e.getPosition());
            ps.setString(3, e.getE_email());
            ps.setString(4, e.getE_phone());
            ps.setString(5, e.getE_address());
            ps.setBoolean(6, e.isGender());
            ps.setString(7, e.getDOB());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getEmployeeByEmail(String e_email) {
        String sql = "SELECT COUNT(*) FROM Employee WHERE e_email = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, e_email);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }
public List<Employee> searchEmployeesByName(String searchText) {
    List<Employee> list = new ArrayList<>();
    String sql = "SELECT e_id, e_name, position, e_email, e_phone, e_address, gender, DOB FROM Employee WHERE e_name LIKE ?";
    try {
        conn = new DBConnect().connection;
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + searchText + "%");
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
            list.add(new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        closeResources();
    }
    return list;
}

}
