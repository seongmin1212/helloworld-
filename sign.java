package test2;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;




public class sign {
    private static Logger logger = Logger.getLogger(sign.class);
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        logger.info("jdbc ����");
        String jdbcUrl = "jdbc:mysql://localhost:3306/student_db?serverTimezone=Asia/Seoul";
        logger.info("connect �õ�");
        Connection connection = DriverManager.getConnection(jdbcUrl, "root", "abcd1234");
        logger.info("connect �Ϸ�");

        System.out.print("ID:");
        String id = sc.nextLine();

        System.out.print("password:");
        String password = sc.nextLine();

        System.out.print("name:");
        String name = sc.nextLine();
        
        System.out.print("sex:");
        String sex = sc.nextLine();
        
        System.out.print("phonenumber:");
        String phonenumber = sc.nextLine();
        
        System.out.print("address:");
        String address = sc.nextLine();
        
        System.out.print("birth:");
        String birth = sc.nextLine();

        String sql = "SELECT * FROM loginjoin";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String getId = resultSet.getString("id");
            String getPassword = resultSet.getString("password");
            String getName = resultSet.getString("name");
            String getSex = resultSet.getString("sex");
            String getPhonenumber = resultSet.getString("phonenumber");
            String getAddress = resultSet.getString("address");
            String getBirth = resultSet.getString("birth");
            String pwPattern ="^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
            Matcher matcher = Pattern.compile(pwPattern).matcher(getPassword);


            String encryptedPassword = SHA256.sha256(password);
            logger.info(getId);
            logger.info(getPassword);
            logger.info(getSex);
            logger.info(getPhonenumber);
            logger.info(getAddress);
            logger.info(getBirth);
            logger.info("===================");
            logger.info(encryptedPassword);
            String selectSQL = "SELECT COUNT(ID) FROM student_db.loginjoin;";
            resultSet = statement.executeQuery(selectSQL);
            resultSet.getString(id);
            
            
//            if(id.matches(".*[��-����-��-��-�R]+.*")) {
//            	System.out.println("���̵� �ѱ��� �Է��Ͻ� �� �����ϴ�. �ٽ� �Է����ּ���. \n");
//            } else {
//            	for(int i = 0; i<selectSQL.length(); i++)
//            	{
//            	  
//            	  selectSQL.compareTo(id);
//            	  System.out.println("���̵� �ߺ� �Ϸ�");
//            	  	 }
//        	}
//            if(password.matches(pwPattern))
//            {
//            	System.out.println("������,Ư������,���ڸ� �̿��Ͽ� ��й�ȣ�� �ٽ� �Է��ϼ���\n");
//            }
//            if(phonenumber.matches(" ^01(?:0|1|[6-9])(?:\\\\d{3}|\\\\d{4})\\\\d{4}$\r\n")){
//            	System.out.println("���ڿ� �ڸ��ָ� �����Ͽ� �Է��ϼ���\n");
//            }	
//            if(address.matches(regex))
            
            
            if(getId.equals(id) && getPassword.equals(encryptedPassword)){
                logger.info("�α��� ����");
            }else{
                String insertSQL = "INSERT INTO login VALUES(?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,id);
                preparedStatement.setString(2, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
               preparedStatement.executeUpdate();
            }
        }
    }
}


