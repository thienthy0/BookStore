package dal;

import entity.Account;
import java.sql.Connection;
import java.sql.Date;
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

    public List<Account> getAllAccounts() {
        List<Account> listAccount = new ArrayList<>();
        String sql = "SELECT account_id, first_name, last_name, phone, email, address, role_id, DateOfBirth, gender FROM Account";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt("account_id");
                int role_id = rs.getInt("role_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = rs.getBoolean("gender");
                Date DateOfBirth = rs.getDate("DateOfBirth");

                listAccount.add(new Account(account_id, role_id, first_name, last_name, phone, email, null, null, address, gender, DateOfBirth));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAccount;
    }

    public int countAccounts() {
        String sql = "SELECT COUNT(*) FROM Account";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Account> getAccountsByPage(int pageIndex, int pageSize) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT account_id, first_name, last_name, phone, email, address, role_id, DateOfBirth, gender FROM Account ORDER BY account_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (pageIndex - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt("account_id");
                int role_id = rs.getInt("role_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = rs.getBoolean("gender");
                Date DateOfBirth = rs.getDate("DateOfBirth");

                list.add(new Account(account_id, role_id, first_name, last_name, phone, email, null, null, address, gender, DateOfBirth));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteAccount(int account_id) {
        String sql = "DELETE FROM Account WHERE account_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, account_id);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAccount(int account_id, String first_name, String last_name, String phone, String email, String address, int role_id, Boolean gender, Date DateOfBirth) {
        String sql = "UPDATE Account SET first_name = ?, last_name = ?, phone = ?, email = ?, address = ?, role_id = ?, gender = ?, DateOfBirth = ? WHERE account_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.setInt(6, role_id);
            ps.setBoolean(7, gender);
            ps.setDate(8, DateOfBirth);
            ps.setInt(9, account_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public void createAccount(Account account) {
    String sql = "INSERT INTO Account (account_id, first_name, last_name, phone, email, password, account_image, address, role_id, gender, DateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        conn = new DBConnect().connection;
        ps = conn.prepareStatement(sql);
        ps.setInt(1, account.getAccount_id());
        ps.setString(2, account.getFirst_name());
        ps.setString(3, account.getLast_name());
        ps.setString(4, account.getPhone());
        ps.setString(5, account.getEmail());
        ps.setString(6, account.getPassword());
        ps.setString(7, account.getAccount_image());
        ps.setString(8, account.getAddress());
        ps.setInt(9, account.getRole_id());
        ps.setBoolean(10, account.getGender());
        ps.setDate(11, new java.sql.Date(account.getDateOfBirth().getTime()));
        ps.executeUpdate();
    } catch (Exception ex) {
        Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
    } 
}


    public Account getAccountById(int id) {
        String sql = "SELECT account_id, first_name, last_name, phone, email, address, role_id, gender, DateOfBirth FROM Account WHERE account_id = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int account_id = rs.getInt("account_id");
                int role_id = rs.getInt("role_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = rs.getBoolean("gender");
                Date DateOfBirth = rs.getDate("DateOfBirth");

                return new Account(account_id, role_id, first_name, last_name, phone, email, null, null, address, gender, DateOfBirth);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean getAccountByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE email = ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Account> searchAccountsByName(String searchText) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT account_id, first_name, last_name, phone, email, address, role_id, gender, DateOfBirth FROM Account WHERE first_name LIKE ? OR last_name LIKE ?";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchText + "%");
            ps.setString(2, "%" + searchText + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt("account_id");
                int role_id = rs.getInt("role_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = rs.getBoolean("gender");
                Date DateOfBirth = rs.getDate("DateOfBirth");

                list.add(new Account(account_id, role_id, first_name, last_name, phone, email, null, null, address, gender, DateOfBirth));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        DAOEmployee daoEmployee = new DAOEmployee();

        // Lấy tất cả các tài khoản từ cơ sở dữ liệu
        List<Account> accounts = daoEmployee.getAllAccounts();
        for (Account acc : accounts) {
            System.out.println(acc);
        }

        // Lấy tài khoản theo ID
        Account accountById = daoEmployee.getAccountById(1);
        System.out.println(accountById);

        // Tạo tài khoản mới
        Account newAccount = new Account(0, 2, "John", "Doe", "123456789", "john.doe@example.com", "password", "image.png", "123 Main St", true, new Date(System.currentTimeMillis()));
        daoEmployee.createAccount(newAccount);

        // Cập nhật tài khoản
        daoEmployee.updateAccount(1, "Jane", "Doe", "987654321", "jane.doe@example.com", "456 Main St", 1, false, new Date(System.currentTimeMillis()));

        // Xóa tài khoản
        daoEmployee.deleteAccount(3);

        // Đếm số lượng tài khoản
        int accountCount = daoEmployee.countAccounts();
        System.out.println("Total accounts: " + accountCount);

        // Tìm kiếm tài khoản theo tên
        List<Account> searchResults = daoEmployee.searchAccountsByName("John");
        for (Account acc : searchResults) {
            System.out.println(acc);
        }

        // Lấy tài khoản theo trang
        List<Account> accountsByPage = daoEmployee.getAccountsByPage(1, 10);
        for (Account acc : accountsByPage) {
            System.out.println(acc);
        }

        // Kiểm tra email đã tồn tại hay chưa
        boolean emailExists = daoEmployee.getAccountByEmail("john.doe@example.com");
        System.out.println("Email exists: " + emailExists);
    }
}
