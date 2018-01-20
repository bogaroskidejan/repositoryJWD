package biblioteka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteka.model.Autor;
import util.ConnectionManager;

public class AutorDAO {
	
	public static List<Autor> getAll(){
		String query = "select id, ime, prezime from Autor";
		Statement stmt;
		List<Autor> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<Autor>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String ime = rset.getString(2);
				String prezime = rset.getString(3);
				Autor a = new Autor(id, ime, prezime);
				retVal.add(a);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public static boolean insertAutor(Autor autor) {
		boolean retVal = false;
		try {
			String update = "INSERT INTO Autor (ime, prezime) values (?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, autor.getIme());
			pstmt.setString(2, autor.getPrezime());
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
