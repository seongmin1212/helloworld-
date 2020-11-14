package test2;

import java.sql.*;


public class DBManager {
   private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
   private final String DB_URL = "jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC"; //접속할 DB 서버
   
      
   private final String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
   private final String PASSWORD = "abcd1234"; //사용자의 비밀번호를 상수로 정의
   
   //생성자
   public DBManager(){ 
       Connection conn = null; 
      Statement state = null; 
       try{
         Class.forName(JDBC_DRIVER);
         conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
         state = conn.createStatement();
         String sql; //SQL문을 저장할 String
         sql = "SELECT * FROM student";
         ResultSet rs = state.executeQuery(sql); //SQL문을 전달하여 실행   
             while(rs.next()){
               String number = rs.getString("_number");
               String name = rs.getString("name");
               String kor = rs.getString("kor");
               String math = rs.getString("math");
               String eng = rs.getString("eng");
               System.out.println("Number: "+ number + "\nName: " + name + "\nKOR: " + kor); 
               System.out.println("MATH: "+ math + "\nENG: " + eng + "\n-------------\n");
            }
            
            rs.close();
            state.close();
            conn.close();

         } catch(Exception e){
            e.printStackTrace();
         } finally { //예외가 있든 없든 무조건 실행
            try{
               if(state!=null)
                  state.close();
            }catch(SQLException ex1){
               ex1.printStackTrace();
            }
                  
            try{
               if(conn!=null)
                  conn.close();
            }catch(SQLException ex1){
               ex1.printStackTrace();
            }
      }
   }
   
   public static void main(String[] args) {
      DBManager db = new DBManager();
      System.out.println("asdf");
   }
}