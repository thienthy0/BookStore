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
public class DAOCategory {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public List<Category> getCategory(){
         List<Category> list=new ArrayList<>();
         String query="select * from Category";
         try {
            conn=new DBConnect().connection;
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
                        
            }
        } catch (Exception e) {
        }
         
         return list;
     }
}
