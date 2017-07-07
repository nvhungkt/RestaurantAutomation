/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Administrator
 */
public class DBUtilities implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException {
//        Context context = new InitialContext();
//        Context tomcat = (Context) context.lookup("java:comp/env");
//        DataSource ds = (DataSource) tomcat.lookup("RestaurantDS");
//        Connection con = ds.getConnection();

        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=AutomationRestaurant;";
            con = DriverManager.getConnection(url, "sa", "721997nam");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
