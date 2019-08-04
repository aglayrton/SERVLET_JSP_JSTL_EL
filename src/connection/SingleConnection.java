package connection;

import java.sql.*;
import java.sql.DriverManager;

public class SingleConnection {
	private static String url="jdbc:mysql://localhost:3306/jsp?autoReconnect=true";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;
	
	public SingleConnection() {
		conectar();
	}
	
	static {
		conectar();
	}
	
	private static void conectar() {
		try {
			if (connection==null) {
				connection = DriverManager.getConnection(url,user,password);
				connection.setAutoCommit(false);
				System.out.println("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
