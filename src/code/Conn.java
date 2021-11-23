package code;

import java.sql.*;
 
 
public class Conn {
	public static Connection Connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");
		} catch (Exception e) {
			System.out.println("驱动加载失败");
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/Test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Hongkong",
					"root", "123456");

			System.out.println("数据库连接成功");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			System.out.println("连接失败");
		}
		return conn;
	}
}