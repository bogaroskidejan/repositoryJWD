package menjacnicaBaze.UI;

import java.text.ParseException;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import menjacnicaBaze.DAO.KursnaListaDAO;
import menjacnicaBaze.DAO.ValutaDAO;
import menjacnicaBaze.DAO.VrednostValuteDAO;
import menjacnicaBaze.model.KursnaLista;
import menjacnicaBaze.model.Valuta;
import menjacnicaBaze.model.VrednostValute;
import menjacnicaBaze.utils.PomocnaKlasa;

public class KursnaListaUI {

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
				kursnaListaIspis();
				break;
			case 2: 
				dodavanjeKursneListe();
				break;
			case 3:
				statistika();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa kursnim listama - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih vrednosti valuta za kursnu listu");
		System.out.println("\tOpcija broj 2 - dodavanje kursne liste");
		System.out.println("\tOpcija broj 3 - STATISTIKA");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	public static void kursnaListaIspis () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Unesite datum kursne liste (yyyy-mm-dd):");
		String DatumString = PomocnaKlasa.ocitajTekst();
		try {
			Date parsed = sdf.parse(DatumString);
			java.sql.Date sql_date = new java.sql.Date(parsed.getTime());

			KursnaLista k = KursnaListaDAO.getByDatum(AppUI.getConn(), sql_date);

			if (!k.getVrednostValuteArr().isEmpty()) {
				System.out.format("%-16s%-20s%-20s%-20s%n", "Datum" , "Valuta", "Kupovni Kurs" , "Prodajni Kurs"); System.out.println();
				System.out.println("**************************************************************"); System.out.println();
				for (VrednostValute vr: k.getVrednostValuteArr()) {
					System.out.format("%-20tF%-20s%-20.2f%-20.2f%n", k.getDatumFormiranja(), vr.getValuta().getOznaka(), 
							vr.getKupovni(), vr.getProdajni());System.out.println();
					System.out.println("________________________________________________________________");
				}

			} else {
				System.out.println("Ne postoji kursna lista za taj datum");
			}

		} catch (ParseException e) {
			System.out.println("Neispravan unos.");
		}
	}
	
	public static void dodavanjeKursneListe () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Unesite datum kursne liste (yyyy-mm-dd):");
		String datumString = PomocnaKlasa.ocitajTekst();
		try {
			Date parsed = sdf.parse(datumString);
			java.sql.Date datum = new java.sql.Date(parsed.getTime());
			KursnaLista lista= KursnaListaDAO.getListaByDatum(AppUI.getConn(), datum);
			if ( lista != null) {
				System.out.println("Kursna lista vec postoji!");				
			} else {
//				unos kursne liste
				lista= new KursnaLista(datum);
				KursnaListaDAO.add(AppUI.getConn(), lista);
				
				while (PomocnaKlasa.ocitajOdlukuOPotvrdi("da unesete valute i vrednosti valuta u novu kursnu listu?")== 'Y') {
//					unos valute
					System.out.println("Unesite oznaku valute: ");
					String oznaka= PomocnaKlasa.ocitajTekst();
					Valuta val= ValutaDAO.getByOznaka(AppUI.getConn(), oznaka);
					if (val== null) {
						System.out.println("Unesite naziv valute: ");
						String naziv= PomocnaKlasa.ocitajTekst();
						val= new Valuta(oznaka, naziv);
						ValutaDAO.add(AppUI.getConn(), val);
					}
//					unos vrednosti valute
					System.out.println("Unesite vrednost kupovnog kursa valute: ");
					double kupovni= PomocnaKlasa.ocitajRealanBroj();
					System.out.println("Unesite vrednost kupovnog kursa valute: ");
					double prodajni= PomocnaKlasa.ocitajRealanBroj();
					double srednji= (kupovni+prodajni) / 2;
					
					VrednostValute vr= new VrednostValute(val, datum, kupovni, prodajni, srednji);
					lista.getVrednostValuteArr().add(vr);
					VrednostValuteDAO.add(AppUI.getConn(), vr);
					
				}
			}
			
		} catch (ParseException e) {
			System.out.println("Greska u dodavanju kursne liste");			
			e.printStackTrace();
		} 	
	}
	
	public static void statistika () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Unesite datum pocetka statistike (yyyy-mm-dd):");
		String datumStringP = PomocnaKlasa.ocitajTekst();
		
		System.out.println("Unesite datum zavrsetka statistike (yyyy-mm-dd):");
		String datumStringZ = PomocnaKlasa.ocitajTekst();
		try {
			Date parsedP = sdf.parse(datumStringP);
			java.sql.Date datumP = new java.sql.Date(parsedP.getTime());
			
			Date parsedZ = sdf.parse(datumStringZ);
			java.sql.Date datumZ = new java.sql.Date(parsedZ.getTime());
			
			
			LinkedHashMap<String, Valuta> valuteHas = ValutaDAO.getAll(AppUI.getConn());
			ArrayList<Valuta> valute= new ArrayList<>(valuteHas.values());
			
			System.out.format("%-10s%-20s%-20s%n", "Valuta", "Datum Valute", "Srednji Kurs"); System.out.println();
			System.out.println("**************************************************************"); System.out.println();
				for (Valuta val: valute) {
					VrednostValute minimalna= KursnaListaDAO.stat(AppUI.getConn(), datumP, datumZ, val);
					if (minimalna == null) {
						System.out.format("%-10s%-40s%n", val.getOznaka(),
								"Ne postoje podaci u kursnim listama" );System.out.println();
						System.out.println("________________________________________________________________");
					} else {
						System.out.format("%-10s%-20tF%-20.2f%n", val.getOznaka(), minimalna.getDatumListe(), minimalna.getSrednji());System.out.println();
						System.out.println("________________________________________________________________");
					}

				}
		} catch (ParseException e) {
			System.out.println("Greska - statistika");			
			e.printStackTrace();
		} 	
	}
}
