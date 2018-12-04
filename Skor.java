/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tebk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author persada
 */
public class Skor {
    
    
     int score;
     public static Connection con;
     public static Statement stm;
     String url ="jdbc:mysql://localhost/puz";
            String username="root";
            String password="";
     
     public int getskor(){
         return this.score;
         
     }
     
     public void main() throws SQLException{
         String sql = "INSERT INTO ScoresDB  (highScore) VALUES (?)";
//        try (PreparedStatement ps = getCon().prepareCall(sql)) {
//            ps.setString(1, highScore);
//            ps.executeUpdate();
  }
    }
         


