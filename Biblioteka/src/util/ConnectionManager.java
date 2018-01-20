package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private ConnectionManager() {
		super();
	}

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			// ucitavanje MySQL drajvera
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// konekcija
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/biblioteka", "root", "root");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return conn;
	}
	
	public static void closeConnection(){
		try {
			if(conn != null)
				conn.close();
				conn=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
