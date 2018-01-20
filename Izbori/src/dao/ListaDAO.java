package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ui.IzboriUI;
import model.Lista;
import model.Stranka;

public class ListaDAO {
	
	public static Lista getListaByID(Connection conn, int id) {
		Lista lista = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT slogan, brojGlasova, sLice FROM lista WHERE id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int index = 1;
				String slogan = rset.getString(index++);
				int brojGlasova = rset.getInt(index++);
				String sLice = rset.getString(index++);
						
				lista = new Lista(id, slogan, brojGlasova, sLice);
				
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

		return lista;
	}
	
	public static List<Lista> getAll(Connection conn) {
		List<Lista> sveListe = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT id, slogan, brojGlasova, sLice FROM lista";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String slogan = rset.getString(index++);
				int brojGlasova = rset.getInt(index++);
				String sLice = rset.getString(index++);
				List<Stranka> stranke = StrankaDAO.getAllByIdListe(IzboriUI.getConn(), id);	
				
				Lista lista = new Lista(id, slogan, brojGlasova, sLice, stranke);
				sveListe.add(lista); 
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sveListe;
	}

	public static boolean add(Connection conn, Lista lista) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO lista (slogan, brojGlasova, sLice) VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, lista.getSlogan());
			pstmt.setInt(index++, lista.getBrojGlasova());
			pstmt.setString(index++, lista.getsLice());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Connection conn, Lista lista) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE lista SET slogan = ?, brojGlasova = ?, sLice = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, lista.getSlogan());
			pstmt.setInt(index++, lista.getBrojGlasova());
			pstmt.setString(index++, lista.getsLice());
			pstmt.setInt(index++, lista.getId());

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
			String update = "DELETE FROM lista WHERE id = " + id;

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
	
	public static int ukupanBrojGlasova(Connection conn) {
		int ukupno = 0;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT sum(brojGlasova) AS Ukupno FROM pilicarskiizbori.lista;";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				ukupno = rset.getInt(index++);
				
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ukupno;
	}
}
