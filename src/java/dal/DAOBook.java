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
        String query = "select * from Book";
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
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)));
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
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)));
            }
        } catch (Exception e) {
        }

        return list;

    }
    public int getTotalBook(){
        String query="select count(*) from Book";
        try {
            conn = new DBConnect().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
            
        } catch (Exception e) {
        }
        
        return 0;
    }

    

    public static void main(String[] args) {
        DAOBook dao=new DAOBook();
        List<Book> list=dao.getProductbyCID("1");
        for (Book o : list) {
            System.out.println(o);
        }
    }
}
