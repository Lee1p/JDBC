package com.test.java;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ex01 {

    public static void main(String[] args) {

        /*
         * 
         * 
         * JDBC, Java Database Connectivity - 영속성 계층(Persistence Layer) - 중간계층 > 위치 -
         * Java 언어를 사용해서 관계형 데이터를 접근 + 조작해주는 계층 - 각종 API > 클래스들을 제공 - 자바 응용 프로그램 <- JDBC
         * -> 오라클 데이터베이스
         * 
         * 
         * 1. JDBC 2. Spring JDBC 3. MyBatis 4. JPA
         * 
         * JDBC Driver 설치 - 관련 클래스 가져오기 > *.jar 참조 - 어떤 DB 사용? > 오라클 사이트 접속 > JDBC 구현해놓은
         * jar 제공 > 다운로드 + 사용 - Oracle 설치 > Driver 같이 배포 - 빌드 도구를 통해서 참조 > Maven, Gradle
         * 등..
         * 
         * [사람 + SQL Developer <- (SQL) -> Oracle Database Server] 1. 클라이언트 툴 실행
         * 
         * 
         * 2. DB 접속 - 호스트명: localhost - 포트번호: 1521 - SID: xe - 드라이버: thin - 사용자명: hr -
         * 암호: java1234
         * 
         * 3. 질의 - JDBC > Statement 클래스 사용 - SQL 사용(DDL, DML, DCL...) 
         * 
         * 3.1 반환값이 없는 쿼리 - select를 제외한 모든 쿼리 - void Statement.executeUpdate("SQL");
         * 
         * 3.2 반환값이 있는 쿼리 - select - 결과셋을 반환하는 쿼리 - 결과셋을 업무에 사용 - ResultSet =
         * Statement.executeQuery("SQL"); - ResultSet = 결과셋 참조
         * 
         * 4. 접속 종료 - JDBC > Connection 클래스 사용 - commit/rollback
         * 
         * JDBC 라이브러리 클래스 - Connection, Statement, ResultSet
         * 
         * 
         * 
         * [자바 프로그램 + JDBC <- (SQL) -> Oracle Database Server] 1. 자바 프로그램 실행(+JDBC)
         * 
         * 2. DB 접속 - JDBC > Connection 클래스 사용 - 호스트명: localhost - 포트번호: 1521 - SID: xe
         * - 드라이버: thin - 사용자명: hr - 암호: java1234
         * 
         * 
         * 
         */

//        System.out.println(Connection.class);

        System.out.println("[데이터베이스 접속하기");

        // java.sql.Connection
        Connection conn = null;

        // 접속 정보 > 미리 문자열 > 연결 문자열(Connection String)
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "server";
        String pw = "java1234";

        // JDBC 작업 > 외부 입출력 > 예외 처리 필수
        try {
            
            //JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //DB 연결
            //- DriverManager가 DB 연결을 하고 > 연결 정보를 가지고 있는 Connection 객체를 반환한다.
            conn = DriverManager.getConnection(url, id, pw);
            
            
            //현재 오라클 접속 상태?
            //- 연결이 안되어있으면 true
            //- 연결되어있으면 false 
            System.out.println(conn.isClosed()); //false
            
            //업무 진행 > SQL
            System.out.println("업무 진행~");
            
            //접속 종료
            conn.close();
            
            
            System.out.println(conn.isClosed());
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
