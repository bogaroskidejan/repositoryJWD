package menjacnicaBaze.UI;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

import menjacnicaBaze.utils.PomocnaKlasa;

public class AppUI {
	
private static Connection conn;
	
	static {
		// otvaranje konekcije, jednom na pocetku aplikacije
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");
			// otvaranje konekcije
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/menjacnica?useSSL=false", 
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
			AppUI.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				ValutaUI.menu();
				break;
			case 2:
				KursnaListaUI.menu();
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
		System.out.println("Menjacnica - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Rad sa valutama");
		System.out.println("\tOpcija broj 2 - Rad sa kursnim listama");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

	public static Connection getConn() {
		return conn;
	}
}
