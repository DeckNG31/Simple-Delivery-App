/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBCs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mariano
 */
class DBConnector {
       private static String user = "deso";
    private static String pass = "deso";
    private static String url = "jdbc:mysql://localhost:3306";
    private static String database = "deso";
    
    public static Connection instance;
    
    public static Connection getInstance(){
        if(instance == null) {
            
            try {
                System.out.println(user + pass);
                instance = DriverManager.getConnection(url+"/"+database, user, pass);
            } catch (SQLException ex) {
                System.out.println("Error: "+ ex.getLocalizedMessage());
            }
        }
        return instance;
    }
    
}
