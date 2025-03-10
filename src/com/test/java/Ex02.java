package com.test.java;

import java.sql.Connection;

public class Ex02 {
    public static void main(String[] args) {
        
        
        /*
         * 
         *  접속 시 발생하는 에러
         * 
         *  1. 서버 주소
         *  
         *  
         *  2. 아이디 X 및 비밀번호 X 
         *  java.sql.SQLException: ORA-01017: 사용자명/비밀번호가 부적합, 로그온할 수 없습니다.
         *  
         *  3. 서버 중지
         *  - 1번과 동일
         *  
         *  
         *  4. 연결 문자열 오타
         *  - java.sql.SQLException: ORA-17067: 부적합한 Oracle URL이 지정되었습니다.
         *  
         *  5. 포트번호 오류
         *  java.sql.SQLException: ORA-12541: 접속할 수 없습니다. 
         *  host localhost port 1525에 리스너가 없습니다.(CONNECTION_ID=hK/cjvPfT3SRbTHtjgjodA==)
         *  
         * 
         *  6. SID 오타
         *  java.sql.SQLException: ORA-12505: 데이터베이스에 접속할 수 없습니다. 
         *  SID e이(가) host localhost port 1521의 리스너에 등록되지 않았습니다. (CONNECTION_ID=evdvQyUhTqKdPf8EfQEkfQ==)
         *  
         *  
         *  7. 드라이버 오타 
         *  java.lang.ClassNotFoundException: oracle.jdbc.driver.Oracledriver
         *  
         *  8. jar 파일 참조 안하면 뜨는 오류 7번이랑 동일 프로젝트 > build path > class path > add extrnal
         *  java.lang.ClassNotFoundException: oracle.jdbc.driver.OracleDriver
         *  
         *  
         *  
         *  
         * */
        
        
        Connection conn = null;
        DBUtil util = new DBUtil();
        
        conn = util.open();
        
        
        try {
            
            System.out.println(conn.isClosed());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
}
