package test2;
import org.apache.log4j.Logger;



import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class sign {
    private static Logger logger = Logger.getLogger(sign.class);
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        logger.info("jdbc 설정");
        String jdbcUrl = "jdbc:mysql://localhost:3306/student_db?serverTimezone=Asia/Seoul";
        logger.info("connect 시도");
        Connection connection = DriverManager.getConnection(jdbcUrl, "root", "abcd1234");
        logger.info("connect 완료");

        System.out.print("ID:");
        String id = sc.nextLine();

        System.out.print("password:");
        String password = sc.nextLine();

        System.out.print("name:");
        String name = sc.nextLine();

        String sql = "SELECT * FROM loginjoin";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String getId = resultSet.getString("id");
            String getPassword = resultSet.getString("password");
            String getName = resultSet.getString("name");

            String encryptedPassword = signSHA.sha256(password);
            logger.info(getId);
            logger.info(getPassword);
            logger.info("===================");
            logger.info(encryptedPassword);
            if(getId.equals(id) && getPassword.equals(encryptedPassword)){
                logger.info("로그인 성공");
            }else{
                String insertSQL = "INSERT INTO loginjoin VALUES(?, ?,null,null,null,null,null)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,id);
                preparedStatement.setString(2, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
               preparedStatement.executeUpdate();
            }
        }
    }
}
