package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import model.Nastavnik;
import utils.NastavnikNameComparator;
import utils.PomocnaKlasa;

public class NastavnikUI {

	public static ArrayList<Nastavnik> sviNastavnici = new ArrayList<Nastavnik>();

	public static void meniNastavnikUI() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiTekstNastavnikOpcije();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiSveNastavnike();
				break;
			case 2:
				Nastavnik n = pronadjiNastavnikaID();
				if (n != null) {
					System.out.println(n.toString());
				}
				break;
			case 3:
				izmenaPodatakaONastavniko();
				break;
			case 4:
				unosNovogNastavnika();
				break;
			case 5:
				sortiranjeNastavnikaPoImenu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiTekstNastavnikOpcije() {
		System.out.println("Rad sa nastavniciima - opcije:");
		System.out.println("\tOpcija broj 1 - ispisi sve nastavnike");
		System.out.println("\tOpcija broj 2 - ispisi odredjenog nastanika (po ID)");
		System.out.println("\tOpcija broj 3 - izmeni odredjenog nastavnika(ID se ne menja)");
		System.out.println("\tOpcija broj 4 - unos novog nastavnika");
		System.out.println("\tOpcija broj 5 - sortiranje nastavnika po imenu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}

	static void citajIzFajlaNastavnike(File dokument) throws IOException {
		if (dokument.exists()) {
			BufferedReader in = new BufferedReader(new FileReader(dokument));
			in.mark(1); // zapamti trenutnu poziciju u fajlu da mozes kasnije da
						// se vratis na nju
			if (in.read() != '\ufeff') {
				in.reset();
			}

			String s2;
			while ((s2 = in.readLine()) != null) {
				sviNastavnici.add(new Nastavnik(s2));
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}

	public static void ispisiSveNastavnike() {
		for (int i = 0; i < sviNastavnici.size(); i++) {
			System.out.println(sviNastavnici.get(i));
		}
	}

	public static Nastavnik pronadjiNastavnikaID() {
		Nastavnik retVal = null;
		System.out.println("Unesi ID nastavnika:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiNastavnikaID(id);
		if (retVal == null)
			System.out.println("Nastavik sa ID " + id + " ne postoji u evidenciji");
		return retVal;
	}

	// pronadji studenta
	public static Nastavnik pronadjiNastavnikaID(int id) {
		Nastavnik retVal = null;
		for (int i = 0; i < sviNastavnici.size(); i++) {
			Nastavnik nast = sviNastavnici.get(i);
			if (nast.getId() == id) {
				retVal = nast;
				break;
			}
		}
		return retVal;
	}

	public static void izmenaPodatakaONastavniko() {
		Nastavnik nast = pronadjiNastavnikaID();
		if (nast != null) {
			System.out.print("Unesi ime:");
			String stIme = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi prezime:");
			String stPrezime = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi zvanje:");
			String zvanje = PomocnaKlasa.ocitajTekst();

			nast.setIme(stIme);
			nast.setPrezime(stPrezime);
			nast.setZvanje(zvanje);
		}
	}

	public static void unosNovogNastavnika() {
		System.out.print("Unesi id:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		while (pronadjiNastavnikaID(id) != null) {
			System.out.println("Nastavnik sa ID  " + id + " vec postoji");
		}
		System.out.print("Unesi ime:");
		String ime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String prezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi zvanje:");
		String zvanje = PomocnaKlasa.ocitajTekst();

		// ID atribut ce se dodeliti automatski
		Nastavnik nastavnik = new Nastavnik(id, ime, prezime, zvanje);
		sviNastavnici.add(nastavnik);
	}

	static void pisiUFajlNastavnike(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (Nastavnik nastavnici : sviNastavnici) {
			out2.println(nastavnici.toFileRepresentation());
		}

		out2.flush();
		out2.close();
	}

	public static void sortiranjeNastavnikaPoImenu() {
		System.out.println("Nastavnike je moguće sortirati\n\t1 - Ime Rastuće\n\t2- Ime Opadajuće\nIzaberi opciju:");
		int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
		switch (sortOpcija) {
		case 1:
			Collections.sort(sviNastavnici, new NastavnikNameComparator(1));
			System.out.println(sviNastavnici);
			break;
		case 2:
			Collections.sort(sviNastavnici, new NastavnikNameComparator(-1));
			System.out.println(sviNastavnici);
			break;
		default:
			break;
		}

	}

}
