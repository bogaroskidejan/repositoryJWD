package menjacnicaBaze.DAO;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import menjacnicaBaze.model.KursnaLista;
import menjacnicaBaze.model.Valuta;
import menjacnicaBaze.model.VrednostValute;


public class KursnaListaDAO {
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
	
	public static ArrayList<KursnaLista> getAll (Connection conn) {
		ArrayList<KursnaLista> sveKursneListe= new ArrayList<>();
		Statement stmt= null;
		ResultSet rset= null;
	
		try {
			String sql= "SELECT datumFormiranja, kupovni, prodajni, valuta FROM kursnaLista "
					+ "JOIN vrednostValute ON datumFormiranja= datumListe";
			
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index=1;
				Date datum= rset.getDate(index++);
				KursnaLista lista= new KursnaLista(datum);
				LinkedHashMap<String, VrednostValute> vr= VrednostValuteDAO.getAllByDatum(conn, datum);
				ArrayList<VrednostValute> vrednsotVal= new ArrayList<>(vr.values());
				lista.getVrednostValuteArr().addAll(vrednsotVal);
				sveKursneListe.add(lista);
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- Vrednost valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return sveKursneListe;
	}
	
	public static KursnaLista getListaByDatum (Connection conn, Date datum) {
		KursnaLista kursnaLista= null; 
		Statement stmt= null;
		ResultSet rset= null;
		
		
		try {
			String sql= "SELECT datumFormiranja FROM kursnaLista "+
					"WHERE datumFormiranja = '" + datum+ "'";
					
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			if (rset.next()) {
				Date date= rset.getDate(1);
				kursnaLista= new KursnaLista(date);
			}
		}  catch (SQLException e) {
			System.out.println("Greska u upitu- valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return kursnaLista;
	}
			
	public static KursnaLista getByDatum (Connection conn, Date datum) {
		KursnaLista kursnaLista= new KursnaLista(datum);
		Statement stmt= null;
		ResultSet rset= null;
		
		
		try {
			String sql= "SELECT kupovni, prodajni, valuta FROM vrednostValute "+
					"WHERE datumListe = '" + datum+ "'";
					
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index=1;
				double kupovni= rset.getDouble(index++);
				double prodajni= rset.getDouble(index++);
				String oznaka= rset.getString(index++);
				VrednostValute v = new VrednostValute(ValutaDAO.getByOznaka(conn, oznaka), kupovni, prodajni);
				kursnaLista.getVrednostValuteArr().add(v);
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return kursnaLista;
	}
	
	public static boolean add(Connection conn, KursnaLista lista) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO kursnaLista (datumFormiranja) VALUES (?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, lista.getDatumFormiranja());;
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static VrednostValute stat (Connection conn, Date pocetak, Date kraj, Valuta valuta) {
		VrednostValute vrednost= null;
		Statement stmt= null;
		ResultSet rset= null;
	
		try {
			String sql= "SELECT datumListe, oznaka, kupovni, prodajni, (kupovni+prodajni)/2 as srednji FROM vrednostValute " +
					"LEFT JOIN valuta ON valuta = oznaka " +
					"WHERE (datumListe <= '"+kraj + "' and datumListe >= '"+ pocetak + "') AND  oznaka= '" + valuta.getOznaka() + "' " + 
					"ORDER BY srednji ASC limit 1";
					
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			if (rset.next()) {
				int index=1;
				Date datum= rset.getDate(index++);
				String oznaka= rset.getString(index++);
				double kupovni= rset.getDouble(index++);
				double prodajni= rset.getDouble(index++);
				double srednji= rset.getDouble(index++);
				vrednost= new VrednostValute(ValutaDAO.getByOznaka(conn, oznaka), datum, kupovni, prodajni, srednji);
				return vrednost;
			}
		} catch (SQLException e) {
			System.out.println("Greska u upitu- valuta");
			e.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}
			try {rset.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		return vrednost;
	}
	
	public static boolean delete (Connection conn, KursnaLista lista) {
		PreparedStatement pstmt= null;
		try {
			String sql= "DELETE FROM kursnaLista WHERE datumFormiranja = '" + lista.getDatumFormiranja() +"' ";
			pstmt= conn.prepareStatement(sql);
		
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			System.out.println("Graska- brisanje kursne liste");
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
