package com.test.java;

import java.sql.Connection;
import java.sql.Statement;

public class Test1 {

    public static void main(String[] args) {
        
        DBUtil util = new DBUtil(); // DB 연결 도우미 객체
        Connection conn = null;
        Statement stat = null;
        
        try {
            // DB 연결
            conn = util.open();
            stat = conn.createStatement();
            
            // Customer 테이블 생성 SQL (테이블이 존재하면 생성하지 않음)
            String sql = "CREATE TABLE IF NOT EXISTS Customer ("
                    + "    id INT PRIMARY KEY,"
                    + "    name VARCHAR(50) NOT NULL,"
                    + "    email VARCHAR(100) UNIQUE,"
                    + "    phone VARCHAR(15),"
                    + "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            
            // SQL 실행
            stat.executeUpdate(sql);
            
            System.out.println("테이블 'Customer' 생성 완료!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) stat.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
