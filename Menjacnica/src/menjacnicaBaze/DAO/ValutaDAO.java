package menjacnicaBaze.DAO;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import menjacnicaBaze.model.Valuta;

public class ValutaDAO {
	public static LinkedHashMap<String, Valuta> getAll (Connection conn) {
		LinkedHashMap<String, Valuta> sveValute= new LinkedHashMap<>();
		Statement stmt= null;
		ResultSet rset= null;
		
		try {
			String sql= "SELECT oznaka, nazivValute FROM valuta";
			
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			while (rset.next()) {
				String oznaka= rset.getString(1);
				String nazivValute=rset.getString(2);
				
				Valuta val= new Valuta(oznaka, nazivValute);
				
				sveValute.put(oznaka, val);
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return sveValute;
	}
	
	public static Valuta getByOznaka (Connection conn, String oznaka) {
		Valuta val= null;
		Statement stmt= null;
		ResultSet rset= null;
		
		try {
			String sql= "SELECT  nazivValute FROM valuta WHERE oznaka= '" + oznaka+ "'";
			
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			if (rset.next()) {
				String nazivValute=rset.getString(1);
				
				val= new Valuta(oznaka, nazivValute);
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return val;
	}
	
	public static boolean add(Connection conn, Valuta valuta) {
		PreparedStatement pstmt= null;
		try {
			String sql= "INSERT INTO valuta (oznaka, nazivValute) VALUES (?, ?)";
			pstmt= conn.prepareStatement(sql);
			int index=1;
			pstmt.setString(index++, valuta.getOznaka());
			pstmt.setString(index++, valuta.getNazivValute());
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			System.out.println("Graska- dodavanje valute");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean delete (Connection conn, Valuta valuta) {
		PreparedStatement pstmt= null;
		try {
			String sql= "DELETE FROM valuta WHERE oznaka= '" + valuta.getOznaka() +"' ";
			pstmt= conn.prepareStatement(sql);
		
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			System.out.println("Graska- brisanje valute");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
}
