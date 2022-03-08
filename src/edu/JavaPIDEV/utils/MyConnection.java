/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class MyConnection {
/*
    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    
    

    //Design Patter - Singleton
    public String url = "jdbc:mysql://localhost:3306/PIDEVdb";
    public String login = "root";
    public String pwd = "";
    public Connection cnx;
    public static MyConnection instance;

    public MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            //System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
public Connection getCnx() {
return cnx;}

}
