package test2;


import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalDate;


import org.apache.log4j.Logger;

import CryptoTest.AES256Util;
import CryptoTest.Sha256Uil;

public class example {

   public static void main(String[] args) throws Exception {
      Logger log = Logger.getLogger(example.class);
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC", "root", "abcd1234");
      Statement stat = conn.createStatement();
      String sql = "select * from loginjoin";
      ResultSet rs = stat.executeQuery(sql);
     
      Scanner sc = new Scanner(System.in);
      String id = sc.nextLine();
      String password = sc.nextLine();
      
      Sha256Uil sha256Test = new Sha256Uil();
      AES256Util aes256Test = new AES256Util();
      

      
      while(rs.next()) {
    	  String _id = rs.getString("id");
    	  String _password = rs.getString("password");
    	  boolean c = id.equals(_id) && _password.equals(sha256Test.sha256(password));
    	  System.out.println("================");
    	  if(c) {
        	 System.out.println("로그인 성공"); 
          }
    	  else {
             
    	  }
      } 
      
      
      System.out.println(id);
      System.out.println(password);
      log.info(sha256Test.sha256(password));
      
// String plainText = "12345";
//      
//      
//    System.out.println("원문: " + plainText);
//    System.out.println("SHA-256 암호화: " + sha256Test.sha256(plainText));
//    System.out.println("AES-256 암호화: " + aes256Test.encrypt(plainText));
//    System.out.println("AES-256 복호화: " + aes256Test.decrypt(aes256Test.encrypt(plainText)));
      
      
      
   }
}