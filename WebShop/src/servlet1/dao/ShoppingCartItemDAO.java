package servlet1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


import servlet1.webshop.ShoppingCartItem;

import util.ConnectionManager;

public class ShoppingCartItemDAO {
	
	public boolean insertItem(ShoppingCartItem item) {
		boolean retVal = false;
		try {
			String update = "INSERT INTO ShoppingCartItem (count, user_id, product_id) values (?, ?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, item.getCount());
			pstmt.setInt(2, item.getUser().getId());
			pstmt.setInt(3, item.getProduct().getId());
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
