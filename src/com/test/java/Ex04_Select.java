package com.test.java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Ex04_Select {

    public static void main(String[] args) {
        
        
        //Ex04_Select.java
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        m9();
    }

    private static void m9() {
        Connection conn = null; // DB 연결
        Statement stat = null; // SQL 명령어 객체 
        ResultSet rs = null; // SQL 쿼리 결과를 저장하고 관리하는 객체
        DBC util = new DBC();
        
        //요구사항] 영업부 > 직원수와 직원명단을 출력하시오.
        //1. select * from tblInsa where buseo = '영업부'
        //2. select count(*) from tblInsa where buseo = '영업부'
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            

            String sql = "select * from tblInsa where buseo = '영업부'";
            
            rs = stat.executeQuery(sql);
            
            //결과셋(rs)의 레코드 수를 알아내기?
            // 불가능하다. 이거안됨.
            
            int count = 0;
            
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                count++;
            }
            
            rs.close();
            
            //인원수?
            System.out.println("인원수: " + count);
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void m8() {
        
        //요구사항] 영업부 > 직원수와 직원명단을 출력하시오.
        //1. select count(*) from tblInsa where buseo = '영업부'
        //2. select * from tblInsa where buseo = '영업부'
        
        Connection conn = null; // DB 연결
        Statement stat = null; // SQL 명령어 객체 
        ResultSet rs = null; // SQL 쿼리 결과를 저장하고 관리하는 객체
        DBC util = new DBC();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select count(*) as cnt from tblInsa where buseo = '영업부'";
            rs = stat.executeQuery(sql);
            
            if(rs.next()) {
                System.out.println("인원수:" + rs.getString("cnt"));
            }
            
            rs.close();
            
            sql = "select * from tblInsa where buseo = '영업부'";
            
            rs = stat.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    private static void m7() {
        
        //요구사항] 특정 직원에게 보너스를 지급하시오.
        //- tblInsa + tblBonus
        //1. 모든 직원 명단을 출력하기(tblInsa > 직원번호, 이름, 부서, 직위) > m6 참고(select.. tblInsa)
        //2. 사용자 > 보너스 지급할 직원의 번호 입력(scan.nextLine())
        //3. 사용자 > 보너스 금액을 입력(scan.nextLine())
        //4. 보너스 지급(insert tblBonus)
        //5. 지금된 내역을 출력하시오.(select.. tblBonus > 직원번호, 이름, 부서, 직위, 보너스 금액) > inner join
        
        Scanner sc  = new Scanner(System.in);
        Connection conn = null; // DB 연결
        Statement stat = null; // SQL 명령어 객체 
        ResultSet rs = null; // SQL 쿼리 결과를 저장하고 관리하는 객체
        DBC util = new DBC();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            // 1. select 쿼리 날려서 모든 직원 명단을 출력하기 컬럼(Attribute): num, name, buseo,jikwi
            // 2. SELECT 쿼리로 조회한 데이터를 ResultSet에 담습니다.
            // 3. select 출력 -> 반환값있는거 excuteQurey  
            String sql = "select num, name, buseo, jikwi from tblinsa order by num asc";
            
            rs = stat.executeQuery(sql);
            
            while(rs.next()) {
                
                int num = rs.getInt("num");
                String name = rs.getString("name");
                String buseo = rs.getString("buseo");
                String jikwi = rs.getString("jikwi");
                
                System.out.printf("직원명단:%s, '%s','%s','%s'\r\n",num,name,buseo,jikwi);
                
            }
            rs.close(); // 위에 ResultSet 즉 select 가 끝났으니까 사용직후에 닫기. *****
            
            
            //2. 사용자 > 보너스 지급할 직원의 번호 입력
            System.out.print("보너스 지급할 직원의 번호 입력:");
            String num = sc.nextLine();
            
            //3. 사용자 > 보너스 금액을 입력
            System.out.print("보너스 금액을 입력:");
            String amount = sc.nextLine();
            
            //4. 보너스 지급
            String insertsql = String.format("insert into tblbonus(seq,num,bonus) " + "values(seqBonus.nextval, '%s', '%s')",num,amount);
            
            int result = stat.executeUpdate(insertsql);
            
            if (result > 0) {
                System.out.println("보너스 지급 완료!");
                
                //지급 완료
                //5. 지급된 내역을 출력하시오.
            String selectsql = "select i.num, i.name, i.buseo, i.jikwi, b.bonus "
                    + "from tblInsa i inner join tblBonus b on i.num = b.num order by b.seq desc";
            
            rs = stat.executeQuery(selectsql);
            
            while (rs.next()) {
                System.out.printf("%s, %s, %s, %s, %s원\n"
                        , rs.getString("num")
                        , rs.getString("name")
                        , rs.getString("buseo")
                        , rs.getString("jikwi")
                        , rs.getString("bonus")
                        
                        );
            }
            rs.close();
            
                
            } else {
                System.out.println("보너스 지급에 실패했습니다.");
            }

            
            
            stat.close();
            conn.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m6() {
        
        //insert > select
        //주소록 데이터 입력 > 주소록 명단 출력하기
        //UI > 사용자 데이터 입력 + DB 저장
        Scanner sc = new Scanner(System.in);
        
        System.out.print("이름: "); 
        String name = sc.nextLine();
        
        System.out.print("나이: "); 
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
        ResultSet rs = null;
        DBUtil util = new DBUtil();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) " +
                    "values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);

            
            int result = stat.executeUpdate(sql);
            
            if (result > 0) {
                
                //N행 N열
                sql = "select * from tblAddress order by seq";
                rs = stat.executeQuery(sql);
                
                while (rs.next()) {
                    
                    System.out.printf("%s, %s, %s, %s\r\n"
                            , rs.getString("seq")
                            , rs.getString("name")
                            , rs.getString("gender")
                            , rs.getString("age")
                            );
                }
                rs.close();
                
                
            } else {
                System.out.println("추가 실패;;");
                
            }
            
            System.out.println(result + "행 추가하였습니다.");
            
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    

    private static void m5() {
        
        //부서명 입력 > 직원 명단 출력
        
        Scanner sc = new Scanner(System.in);
        
        DBC util = new DBC();
        Connection conn = null; 
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            //부서명??? 입력?
            // > 미리 부서명 가져와서 알려주기
            String sql = "select distinct buseo from tblinsa order by buseo asc";
            
            rs = stat.executeQuery(sql);
            
            System.out.println("보고 싶은 부서명을 입력하세요.");
            
            while (rs.next()) {
                System.out.println("-" + rs.getString("buseo"));
            }
            
            rs.close(); //부서명 목록 닫기
            
            System.out.print("부서명:");
            String buseo = sc.nextLine();
            
            sql = String.format("select name from tblinsa where buseo = '%s' order by name asc", buseo);
            
            rs = stat.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            
            rs.close(); //직원 명단 목록 닫기
            
            
            stat.close(); // 더이상 SQL 실행할 일이 없으면 닫는다.
            conn.close(); // 접속이 필요없을 때 닫는다.
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m4() {
        
        //다중값 반환
        //- N행 1열
        //- 다중 레코드 
        
        DBUtil util = new DBUtil();
        Connection conn = null; 
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select name from tblAddress order by name";
            
            //결과셋의 참조(+커서)
            rs = stat.executeQuery(sql);
            
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
            
            
            
            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m3() {
        
        //다중값 반환
        //- 1행 N열
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            
            String sql = "select * from tblAddress where seq = 1";
            
            rs = stat.executeQuery(sql);
            
            if(rs.next()) {
                
                String name = rs.getString("name");
                String age = rs.getString("age");
                String address = rs.getString("address");
                
                System.out.println(name);
                System.out.println(age);
                System.out.println(address);
                
                
                
            } else {
                System.out.println("not found");
            }
            
            
            
            
            rs.close();
            stat.close();
            conn.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m2() {
        //단일값 반환
        //- 1행 1열
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null; // sql명령을 실행하는 객체
        ResultSet rs = null; // select 결과를 저장하는 객체 
        
        try {
            
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select name from tblAddress where seq = 10";
            
            //결과셋의 참조 객체(rs)
            rs = stat.executeQuery(sql); //BOF
            
            if(rs.next()) { //** 문제 발생 가능!!
            
          
            String name = rs.getString("name");
            
            System.out.println(name);
            
            
            } else { 
                
            System.out.println("데이터 없음");
            
            }
            
            
            rs.close();
            stat.close();
            conn.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void m1() {
        
        //단일값 반환
        //- 1행 1열
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null; // sql명령을 실행하는 객체
        ResultSet rs = null; // select 결과를 저장하는 객체 
        
        try {
            
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select count(*) as cnt from tblAddress";
            
            //결과셋의 참조 객체(rs)
            rs = stat.executeQuery(sql); //BOF
            
            rs.next(); //커서 레코드 1줄 전진
            
            //현재 커서가 가르키고 있는 레코드의 원하는 컬럼 가져오기
            //- rs.getXXX() <- 이걸로 가져오면 된다. 
            
//            int count = rs.getInt(1); //컬럼 순서
//            int count = rs.getInt("cnt"); //컬럼명(***)
            String count = rs.getString("cnt");
            
            System.out.println(count);
            
            rs.close();
            stat.close();
            conn.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
    }
}
