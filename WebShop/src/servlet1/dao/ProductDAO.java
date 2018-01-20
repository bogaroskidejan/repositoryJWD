package servlet1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servlet1.webshop.Product;

import util.ConnectionManager;

public class ProductDAO {
	
	public Product getProductByID(int id) {
		Product product = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			String selectSQL = "SELECT name, price FROM Product WHERE id = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				String name = rset.getString(1);
				double price = rset.getDouble(2);

				product = new Product(name, price);
			}
			rset.close();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}
	
	public boolean deleteProduct(int id) {
		boolean retVal = false;
		try {
			Connection conn = ConnectionManager.getConnection();

			String selectSQL = "DELETE FROM Product WHERE id = ?";
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
	
	public boolean insertProduct(Product product) {
		boolean retVal = false;
		try {
			String update = "INSERT INTO Product (name, price) values (?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, product.getName());
			pstmt.setDouble(2, product.getPrice());
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public List<Product> getAllProducts() {
		String query = "select id, name, price from Product";
		Statement stmt;
		List<Product> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<Product>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String name = rset.getString(2);
				double price = rset.getDouble(3);
				Product p = new Product(id, name, price);
				retVal.add(p);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
