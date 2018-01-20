package biblioteka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteka.model.Autor;
import biblioteka.model.Knjiga;
import util.ConnectionManager;

public class KnjigaDAO {
	
	public static List<Knjiga> getAll(){
		String query = "select k.id, k.naslov, a.id, a.ime, a.prezime from knjiga k join autor a"
				+ " on k.id_autora = a.id";
		
		Statement stmt;
		List<Knjiga> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<Knjiga>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String naslov = rset.getString(2);
				
				int idAutora = rset.getInt(3);
				String ime = rset.getString(4);
				String prezime = rset.getString(5);
				Autor a = new Autor(idAutora, ime, prezime);
				Knjiga k = new Knjiga(id, naslov, a);
				retVal.add(k);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public static boolean insertKnjiga(String naslov, int id){
		boolean retVal = false;
		try {
			String update = "INSERT INTO knjiga (naslov, id_autora) values (?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, naslov);
			pstmt.setInt(2, id);
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public Knjiga getKnjigaByNaziv(String naslov) {
		Knjiga knjiga = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			String selectSQL = "select k.naslov, a.ime, a.prezime from knjiga k left join autor a on k.id_autora = a.id where k.naslov = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);
			preparedStatement.setString(1, naslov);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				int index = 2;//pocinje od 2 zasto sto je 1 naslov pa se uvecava sa ++
				//izvlacanje podataka
				String ime = rset.getString(index++);//da nema indexa islo bi od 2 pa 3, 4..
				String prezime = rset.getString(index++);
				Autor autor = new Autor(ime, prezime);
				knjiga = new Knjiga(naslov, autor);
			}
			rset.close();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return knjiga;
	}
	
}
  