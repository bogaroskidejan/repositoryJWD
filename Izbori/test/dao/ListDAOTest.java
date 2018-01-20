package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Lista;

public class ListDAOTest {

	private Connection conn;

	@Before
	public void setUp() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pilicarskiizbori?useSSL=false", "root",
					"root");
		} catch (Exception ex) {
			ex.printStackTrace();

			// kraj testa
			assertTrue("Neuspela konekcija na bazu!", false);
		}
	}

	@After
	public void tearDown() throws Exception {
		Lista lista = new Lista(1, "Rasne koke pobeđuju", 84, "Bela Mihalji");
		assertTrue("update", ListaDAO.update(conn, lista));

		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testAll() {

		Lista lista = new Lista(1, "Rasne koke pobeđuju", 84, "Bela Mihalji");

		lista.setId(1);
		lista.setSlogan("Rasne koke pobeđuju");
		lista.setBrojGlasova(85);
		lista.setsLice("Bela Mihalji");
		assertTrue("update", ListaDAO.update(conn, lista));

		lista = ListaDAO.getListaByID(conn, lista.getId());
		assertNotNull("getByID", lista);
		assertTrue("update", lista.getId() == (1));
		assertTrue("update", lista.getSlogan().equals("Rasne koke pobeđuju"));
		assertTrue("update", lista.getBrojGlasova() == (85));
		assertTrue("update", lista.getsLice().equals("Bela Mihalji"));
		
	}
}
