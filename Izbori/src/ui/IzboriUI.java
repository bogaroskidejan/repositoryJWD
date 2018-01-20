package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.PomocnaKlasa;

public class IzboriUI {

private static Connection conn;
	
	static {
		// otvaranje konekcije, jednom na pocetku aplikacije
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");
			// otvaranje konekcije
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pilicarskiizbori?useSSL=false", 
					"root", 
					"root");
		} catch (Exception ex) {
			System.out.println("Neuspela konekcija na bazu!");
			ex.printStackTrace();

			// kraj aplikacije
			System.exit(0);
		}
	}
	
	public static void main(String[] args)  {
		int odluka = -1;
		while (odluka != 0) {
			IzboriUI.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				StrankaUI.ispisiSveStranke();
				break;
			case 2:
				ListaUI.ispisiSveListeSaStrankama();
				break;
			case 3:
				ListaUI.glasanje();
				break;
			case 4:
				ListaUI.ispisiStatistikuGlasanja();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}

		// zatvaranje konekcije, jednom na kraju aplikacije
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// ispis teksta osnovnih opcija
	public static void ispisiMenu() {
		System.out.println("Studentska Sluzba - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Prikaz svih stranki");
		System.out.println("\tOpcija broj 2 - Prikaz izbornih lista sa spiskom stranki");
		System.out.println("\tOpcija broj 3 - Glasanje za odredjenu listu");
		System.out.println("\tOpcija broj 4 - Statistika osvojenih glasova");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

	public static Connection getConn() {
		return conn;
	}
	
}

	
