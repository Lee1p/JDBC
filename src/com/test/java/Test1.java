package com.test.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test1 {

    public static void main(String[] args) {
        
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "server";
        String userpassword = "java1234";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, userid, userpassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
