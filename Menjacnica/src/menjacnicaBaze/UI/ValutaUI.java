package menjacnicaBaze.UI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import menjacnicaBaze.DAO.ValutaDAO;
import menjacnicaBaze.model.Valuta;
import menjacnicaBaze.utils.PomocnaKlasa;

public class ValutaUI {
	
	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiSveValute();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa valutama - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih valuta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	public static void ispisiSveValute () {
		LinkedHashMap<String, Valuta> sveValute= ValutaDAO.getAll(AppUI.getConn());
		List<String> key= new ArrayList<>(sveValute.keySet());
		
		System.out.format("%-16s%-20s%n", "Oznaka valute" , "Naziv valute"); System.out.println();
		System.out.println("*************************");
		for (String	kljuc	: key) {
			System.out.format("%-16s%-20s%n", kljuc, sveValute.get(kljuc).getNazivValute());
			System.out.println("_________________________");
		}
	}
}
