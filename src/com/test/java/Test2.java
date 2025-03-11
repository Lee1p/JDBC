package com.test.java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test2 {

    public static void main(String[] args) {
        
        DBUtil util = new DBUtil();
        Connection conn = null;  // DB 연결
        Statement stat = null; // SQL 명령문 실행
        ResultSet rs = null; // 결과 집합
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            // 1. 데이터 삽입 (INSERT INTO)
            String insertSQL = "INSERT INTO Customer (id, name, email, phone) VALUES "
                    + "(1, '홍길동', 'hong@example.com', '010-1234-5678'), "
                    + "(2, '김철수', 'kim@example.com', '010-9876-5432'), "
                    + "(3, '이영희', 'leeyoung@example.com', '010-5555-3333')";
            
            int result = stat.executeUpdate(insertSQL);
            
            if (result > 0) {
                System.out.println("데이터 삽입 완료!");
            } else {
                System.out.println("데이터 삽입에 실패했습니다.");
            }
            
            // 2. 데이터 조회 (SELECT)
            String selectSQL = "SELECT * FROM Customer";
            rs = stat.executeQuery(selectSQL);
            
            // 3. 조회된 데이터 출력 (while(rs.next()))
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                
                System.out.printf("ID: %d, 이름: %s, 이메일: %s, 전화번호: %s\n", id, name, email, phone);
            }
            
            // 자원 해제
            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
