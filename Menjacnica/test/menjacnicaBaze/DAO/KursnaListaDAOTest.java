package menjacnicaBaze.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import menjacnicaBaze.model.KursnaLista;
import menjacnicaBaze.model.Valuta;
import menjacnicaBaze.model.VrednostValute;

public class KursnaListaDAOTest {
	
	private Connection conn;

	@Before
	public void setUp() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/menjacnica?useSSL=false", 
					"root", 
					"root");
		} catch (Exception ex) {
			ex.printStackTrace();

			// kraj testa
			assertTrue("Neuspela konekcija na bazu!", false);
		}
	}

	@After
	public void tearDown() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datum= "2004-01-01";
		Date datumParse= sdf.parse(datum);
		java.sql.Date novi= new java.sql.Date(datumParse.getTime());
		KursnaLista lista= KursnaListaDAO.getByDatum(conn, novi);
		Valuta val= ValutaDAO.getByOznaka(conn, "new");
		HashMap<String,VrednostValute> vv= VrednostValuteDAO.getAllByDatum(conn, novi);
		assertTrue("delete valuta", ValutaDAO.delete(conn, val));
		assertTrue("delete vrednost", VrednostValuteDAO.delete(conn, vv.get("new")));
		assertTrue("delete", KursnaListaDAO.delete(conn, lista));
		
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datumString = "2004-01-01";
		
		Statement stmt= null;
		ResultSet rset= null;
		
		try {
			Date parsed = sdf.parse(datumString);
			java.sql.Date datum = new java.sql.Date(parsed.getTime());
			KursnaLista kursnaLista= new KursnaLista(datum);
			assertTrue("add", KursnaListaDAO.add(conn, kursnaLista));
			
			String sql= "SELECT datumFormiranja FROM kursnaLista "
					+ "WHERE datumFormiranja= '" + datumString+ "'";
			stmt= conn.createStatement();
			rset= stmt.executeQuery(sql);
			
			if (rset.next()) {
				int index= 1;
				java.sql.Date date= rset.getDate(index++);
				Valuta valuta= new Valuta("new", "novaValuta");
				VrednostValute vrednostValute= new VrednostValute(valuta, date, 200, 250);
				KursnaLista lista= new KursnaLista(datum);
				assertEquals(kursnaLista.toString(), lista.toString());
				
				lista.getVrednostValuteArr().add(vrednostValute);
				assertTrue("add Vrednost", VrednostValuteDAO.add(conn, vrednostValute));
				assertTrue("add valuta", ValutaDAO.add(conn, valuta));
			}
		} catch (Exception e) {
			System.out.println("Neuspesno parsiranje datuma"); e.printStackTrace();
		}
		
	}
}
