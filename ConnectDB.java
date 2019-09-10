/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIAuto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author david
 */
public class ConnectDB {
    public ConnectDB() {}
    Connection con;
    public void panggilDriver(){
        try{
            String driver ="com.mysql.jdbc.Driver";
            Class.forName(driver);
            System.out.println("Connection Success");
            
        }catch(ClassNotFoundException enfe ){
            System.out.println("Message Error:" +enfe);
        }
        
        
    }
    public Connection condb() throws SQLException{
        Connection konkasi =null;
        try{
            String url=""; //WAS muss hier stehen?
            String user ="root";
            String pass="root";
            konkasi=DriverManager.getConnection(url,user,pass);
        }catch(SQLException e){
            System.out.println("Failed on:" +e);
        }
        return konkasi;
    }
    
    
}
