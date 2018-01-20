package menjacnicaBaze.DAO;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import menjacnicaBaze.model.VrednostValute;

public class VrednostValuteDAO {
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

	public static LinkedHashMap<String, VrednostValute> getAllByDatum (Connection conn, Date sql_date) {
		LinkedHashMap<String, VrednostValute> sveVredostiValute= new LinkedHashMap<>();
		Statement stmt= null;
		ResultSet rset= null;

		try {
			String sql= "SELECT kupovni, prodajni, valuta FROM vrednostValute " + 
					"WHERE datumListe= '" + sql_date + "'";
			
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index=1;
				double kupovni= rset.getDouble(index++);
				double prodajni= rset.getDouble(index++);
				String oznaka= rset.getString(index++);
				
			VrednostValute vrednostVal= null;
				vrednostVal = new VrednostValute(ValutaDAO.getByOznaka(conn, oznaka), sql_date, kupovni, prodajni);
				sveVredostiValute.put(oznaka, vrednostVal);
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- Vrednost valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return sveVredostiValute;
	}
	
	public static boolean add(Connection conn, VrednostValute vrednost) {
		PreparedStatement pstmt= null;
		try {
			String sql= "INSERT INTO vrednostValute (kupovni, prodajni, datumListe, valuta) VALUES (?, ?, ?, ?)";
			pstmt= conn.prepareStatement(sql);
			int index=1;
			pstmt.setDouble(index++, vrednost.getKupovni());
			pstmt.setDouble(index++, vrednost.getProdajni());
			pstmt.setDate(index++, vrednost.getDatumListe());
			pstmt.setString(index++, vrednost.getValuta().getOznaka());
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			System.out.println("Graska- dodavanje vrednosti valute");
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
	
	public static boolean delete (Connection conn, VrednostValute vrednost) {
		PreparedStatement pstmt= null;
		try {
			String sql= "DELETE FROM vrednostValute WHERE valuta= '" + vrednost.getValuta().getOznaka() +"' ";
			pstmt= conn.prepareStatement(sql);
		
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			System.out.println("Graska- brisanje vrednosti valute");
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
