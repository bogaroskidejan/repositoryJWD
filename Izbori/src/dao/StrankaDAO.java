package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Stranka;


public class StrankaDAO {
	
	
	public static Stranka getStrankaByID(Connection conn, int id) {
		Stranka stranka = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT skracenica, naziv, idListe FROM stranka WHERE id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int index = 1;
				String skracenica = rset.getString(index++);
				String naziv = rset.getString(index++);
				int idListe = rset.getInt(index++);
				
				stranka = new Stranka(id, skracenica, naziv, idListe);
				
			} else {
				System.out.println("Greska u SQL upitu!");
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return stranka;
	}
	public static List<Stranka> getAllByIdListe(Connection conn, int idListe) {
		List<Stranka> sveStranke = new ArrayList<>();
		Stranka stranka = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT id, skracenica, naziv FROM stranka WHERE idListe = " + idListe;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String skracenica = rset.getString(index++);
				String naziv = rset.getString(index++);
				stranka = new Stranka(id, skracenica, naziv, idListe);
				sveStranke.add(stranka); 
							
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sveStranke;
	}

	public static List<Stranka> getAll(Connection conn) {
		List<Stranka> sveStranke = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT id, skracenica, naziv, idListe FROM stranka";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String skracenica = rset.getString(index++);
				String naziv = rset.getString(index++);
				int idListe = rset.getInt(index++);
				
				Stranka stranka = new Stranka(id, skracenica, naziv, idListe);
				sveStranke.add(stranka); 
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sveStranke;
	}

	public static boolean add(Connection conn, Stranka stranka) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO stranka (skracenica, naziv, idListe) VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, stranka.getSkracenica());
			pstmt.setString(index++, stranka.getNaziv());
			pstmt.setInt(index++, stranka.getIdListe());
			

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Connection conn, Stranka stranka) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE stranka SET skracenica = ?, naziv = ?, idListe = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, stranka.getSkracenica());
			pstmt.setString(index++, stranka.getNaziv());
			pstmt.setInt(index++, stranka.getIdListe());
			pstmt.setInt(index++, stranka.getId());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean delete(Connection conn, int id) {
		Statement stmt = null;
		try {
			String update = "DELETE FROM stranka WHERE id = " + id;

			stmt = conn.createStatement();
			return stmt.executeUpdate(update) == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
}
