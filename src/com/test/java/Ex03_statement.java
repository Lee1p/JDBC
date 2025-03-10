package com.test.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex03_statement {

    public static void main(String[] args) {

        // Ex03_Statement.java
        /*
         * 
         * Connection - 연결/종료
         * 
         * Statement - 모든 SQL 실행
         * 
         * Statement 종류 1. Statement - 기본 2. PreparedStatement - Statement 개량 > 매개 변수 처리
         * 특화 3. CallableStatement - Statement 개량 > 프로시저 호출 전용
         * 
         * 
         */

//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
          m6();

          
    }// main

    private static void m6() {
        
        //UI > 사용자 데이터 입력 + DB 저장
        Scanner sc = new Scanner(System.in);
        
        System.out.print("이름: "); // varchar2 > String
        String name = sc.nextLine();
        
        System.out.print("나이: "); //number > String 
        String age = sc.nextLine();
        
        System.out.print("성별(m,f): ");
        String gender = sc.nextLine();
        
        System.out.print("전화번호: ");
        String tel = sc.nextLine();
        
        System.out.print("주소: ");
        String address = sc.nextLine();
        address = address.replace("'", "''");
        
        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) " +
                    "values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);

            System.out.println(sql);
            
            int result = stat.executeUpdate(sql);
            
            System.out.println(result);
            
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void m5() {

        // 반환값이 없는 모든 쿼리
        // - DML > insert, update, delete
        // - DDL
        // - DCL
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;

        try {

            conn = util.open();
            stat = conn.createStatement();

//            String sql = """
//
//                        create table tblAddress (
//                        seq number primary key,                              --PK
//                        name varchar2(30) not null,                          --이름
//                        age number(3) not null check(age between 0 and 120), --나이
//                        gender char(1) not null check(gender in ('m', 'f')), --성별(m,f)
//                        tel varchar2(15) not null,                           --전화번호
//                        address varchar2(300) not null,                      --주소
//                        regdate date default sysdate not null                --등록일
//                        )
//
//
//                                        """;
            
            String sql = "create sequence seqAddress";

            int result = stat.executeUpdate(sql);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 해제, Clean up code
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


    private static void m4() {

        // delete
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;

        try {

            conn = util.open();
            stat = conn.createStatement();

            String sql = "delete from tblAddress where seq = ";

            int result = stat.executeUpdate(sql);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 해제, Clean up code
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private static void m3() {

        // update
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;

        try {

            conn = util.open();
            stat = conn.createStatement();

            String sql = "update tblAddress set age = age + 1 where seq = 4";

            int result = stat.executeUpdate(sql);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 해제, Clean up code
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private static void m2() {

        // JDBC
        // 1. 기본 설정 > SQL 실행 시(executeXXX) + 자동 커밋 호출 > Auto Commit

        // 2. 사용자 설정 > 원하는 시점에 commit/rollback 호출

        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();

        try {

            conn = util.open();
            stat = conn.createStatement();

            if (!conn.isClosed()) {

                String sql = "insert into tblAddress (seq,name, age, gender, tel, address, regdate)\r\n"
                        + "    values (seqAddress.nextVal, '헤헤헤', 20, 'm', '010-1234-5671',\r\n"
                        + "    '서울시 강남구 역삼동', default)";

                int result = stat.executeUpdate(sql);

                if (result > 0) {
                    System.out.println("추가 성공!!");
                } else {
                    System.out.println("추가 실패;;");
                }

                Scanner sc = new Scanner(System.in);
                sc.nextLine();

                conn.commit();

                // 자원 정리
                stat.close();
                conn.close();

            } else {
                System.out.println("DB 접속 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();

            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void m1() {

        // insert
        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();

        try {

            // 1.
            conn = util.open();
            stat = conn.createStatement();

            if (!conn.isClosed()) { // file.exist()

                // 2.
                // - 자바는 SQL을 모른다. > SQL을 문자열로 취급한다.(의미X)

                /*
                 * Error Message = ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다. 테이블을 생성할때 마지막에 괄호닫고 세미콜론
                 * ; 이거 붙이면안된다. ㅇㅋ ? 이 오류 해결 방법임.
                 * 
                 */

                String sql = "insert into tblAddress (seq,name, age, gender, tel, address, regdate)\r\n"
                        + "    values (seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5671',\r\n"
                        + "    '서울시 강남구 역삼동', default)";

                // 반환값이 없는 쿼리
                // - int stat.executeUpdate(String sql)

                // 반환값이 있는 쿼리
                // - ResultSet stat.executeQuert(String sql)

                // SQL Developer > Ctrl + Enter > SQL 서버 전송(실행)
                // - result > SQL을 실행 후 적용된 행의 개수를 반환

                int result = stat.executeUpdate(sql);

                // System.out.println(result); //1
                if (result > 0) {
                    System.out.println("추가 성공!!");
                } else {
                    System.out.println("추가 실패;;");
                }

                // 자원 정리
                stat.close();
                conn.close();

            } else {
                System.out.println("DB 접속 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
