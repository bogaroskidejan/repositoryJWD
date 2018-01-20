package biblioteka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteka.model.User;
import util.ConnectionManager;

public class UserDAO {
	
	public User getUserByID(int id) {
		User user = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			String selectSQL = "SELECT id, username, password FROM User WHERE id = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				String username = rset.getString(1);
				String password = rset.getString(2);

				user = new User(username, password);
			}
			rset.close();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		User user = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			String selectSQL = "SELECT id FROM User WHERE username = ? AND password = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				int id = rset.getInt(1);
				user = new User(id, username, password);
			}
			rset.close();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public boolean deleteUser(int id) {
		boolean retVal = false;
		try {
			Connection conn = ConnectionManager.getConnection();

			String selectSQL = "DELETE FROM User WHERE id = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() == 1)
				retVal = true;
			preparedStatement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retVal;
	}

	public boolean insertUser(User user) {
		boolean retVal = false;
		try {
			String update = "INSERT INTO User (username, password) values (?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;

	}

	public List<User> getAllUsers() {
		String query = "select id, username, password from User";
		Statement stmt;
		List<User> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<User>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String username = rset.getString(2);
				String password = rset.getString(3);
				User u = new User(id, username, password);
				retVal.add(u);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
