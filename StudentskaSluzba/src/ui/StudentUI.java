package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import model.IspitnaPrijava;
import model.Predmet;
import model.Student;
import utils.PomocnaKlasa;
import utils.StudentNameComparator;

public class StudentUI {

	public static ArrayList<Student> sviStudenti = new ArrayList<Student>();

	public static void meniStudentUI() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiTekstStudentOpcije();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				unosNovogStudenta();
				break;
			case 2:
				izmenaPodatakaOStudentu();
				break;
			case 3:
				brisanjePodatakaOStudentu();
				break;
			case 4:
				ispisiSveStudente();
				break;
			case 5:
				Student st = pronadjiStudentaIndeks();
				if (st != null) {
					System.out.println(st.toStringAllPredmet());
				}
				break;
			case 6:
				Student st2 = pronadjiStudentaIndeks();
				if (st2 != null) {
					System.out.println(st2.toStringAllIspitnaPrijava());
				}
				break;
			case 7:
				sortirajStudentePoImenu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiTekstStudentOpcije() {
		System.out.println("Rad sa studentima - opcije:");
		System.out.println("\tOpcija broj 1 - unos podataka o novom studentu");
		System.out.println("\tOpcija broj 2 - izmena podataka o studentu");
		System.out.println("\tOpcija broj 3 - brisanje podataka o studentu");
		System.out.println("\tOpcija broj 4 - ispis podataka svih studenata");
		System.out.println(
				"\tOpcija broj 5 - ispis podataka o odre\u0111enom studentu sa njegovim predmetima koje poha\u0111a");
		System.out.println("\tOpcija broj 6 - ispis podataka o odre\u0111enom studentu sa njegovim ispitnim prijavama");
		System.out.println("\tOpcija broj 7 - sortiranje studenata po imenu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}

	public static void ispisiSveStudente() {
		for (int i = 0; i < sviStudenti.size(); i++) {
			System.out.println(sviStudenti.get(i));
		}
	}

	public static Student pronadjiStudentaIndeks() {
		Student retVal = null;
		System.out.println("Unesi indeks studenta:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		retVal = pronadjiStudentaIndeks(stIndex);
		if (retVal == null)
			System.out.println("Student sa indeksom " + stIndex + " ne postoji u evidenciji");
		return retVal;
	}

	public static Student pronadjiStudentaIndeks(String stIndex) {
		Student retVal = null;
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getIndeks().equals(stIndex)) {
				retVal = st;
				break;
			}
		}
		return retVal;
	}

	public static Student pronadjiStudentaId(int id) {
		Student retVal = null;
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getId() == id) {
				retVal = st;
				break;
			}
		}
		return retVal;
	}

	public static int pronadjiPozicijuStudentaIndeks() {
		int retVal = -1;
		System.out.println("Unesi indeks studenta:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getIndeks().equals(stIndex)) {
				retVal = i;
				break;
			}
		}
		if (retVal == -1)
			System.out.println("Student sa indeksom " + stIndex + " ne postoji u evidenciji");
		return retVal;
	}

	public static void sortirajStudentePoImenu() {
		System.out.println(
				"Studente je mogu\u0107e sortirati\n\t1 - Ime Rastu\u0107e\n\t2- Ime Opadaju\u0107e\nIzaberi opciju:");
		int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
		switch (sortOpcija) {
		case 1:
			Collections.sort(sviStudenti, new StudentNameComparator(1));
			break;
		case 2:
			Collections.sort(sviStudenti, new StudentNameComparator(-1));
			break;
		default:
			break;
		}

	}

	public static void unosNovogStudenta() {
		System.out.print("Unesi index:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		stIndex = stIndex.toUpperCase();
		while (pronadjiStudentaIndeks(stIndex) != null) {
			System.out.println("Student sa indeksom " + stIndex + " vec postoji");
			stIndex = PomocnaKlasa.ocitajTekst();
		}
		System.out.print("Unesi ime:");
		String stIme = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String stPrezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi grad:");
		String stGrad = PomocnaKlasa.ocitajTekst();

		Student st = new Student(0, stIme, stPrezime, stGrad, stIndex);
		sviStudenti.add(st);

		while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studenta da poha\u0111a odre\u0111ene predmet") == 'Y') {
			PohadjaUI.dodajStudentaNaPredmet(st);
		}
	}

	public static void izmenaPodatakaOStudentu() {
		Student st = pronadjiStudentaIndeks();
		if (st != null) {
			System.out.print("Unesi ime:");
			String stIme = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi prezime:");
			String stPrezime = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi grad:");
			String stGrad = PomocnaKlasa.ocitajTekst();

			st.setIme(stIme);
			st.setPrezime(stPrezime);
			st.setGrad(stGrad);

			while (PomocnaKlasa
					.ocitajOdlukuOPotvrdi("ukloniti studenta da ne poha\u0111a odre\u0111ene predmet") == 'Y') {
				PohadjaUI.ukloniStudentaSaPredmeta(st);
			}

			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studenta da poha\u0111a odre\u0111ene predmet") == 'Y') {
				PohadjaUI.dodajStudentaNaPredmet(st);
			}
		}
	}

	public static void brisanjePodatakaOStudentu() {
		int pozicija = pronadjiPozicijuStudentaIndeks();
		if (pozicija != -1) {
			Student st = sviStudenti.remove(pozicija);

			ArrayList<Predmet> listStudOdPredmetaZaBrisanje = new ArrayList<Predmet>(st.getPredmeti());
			ArrayList<IspitnaPrijava> listIspOdPredmetaZaBrisanje = new ArrayList<IspitnaPrijava>(
					st.getIspitnePrijave());

			// sada moramo da uklonim sve za pohadja
			for (Predmet pr : listStudOdPredmetaZaBrisanje) {
				PohadjaUI.ukloniStudentaSaPredmeta(st, pr);
			}

			// sada moramo da uklonim sve za ispitnu prijavu
			for (IspitnaPrijava isp : listIspOdPredmetaZaBrisanje) {
				IspitnaPrijavaUI.ukloniIspitnuPrijavu(isp);
			}

			System.out.println("Podaci obrisani iz evidencije");
		}
	}

	static void citajIzFajlaStudente(File dokument) throws IOException {
		if (dokument.exists()) {

			BufferedReader in = new BufferedReader(new FileReader(dokument));

			in.mark(1); // zapamti trenutnu poziciju u fajlu da mozes kasnije da
						// se vratis na nju
			if (in.read() != '\ufeff') {
				in.reset();
			}

			String s2;
			while ((s2 = in.readLine()) != null) {
				sviStudenti.add(new Student(s2));
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}

	static void pisiUFajlStudente(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (Student student : sviStudenti) {
			out2.println(student.toFileRepresentation());
		}

		out2.flush();
		out2.close();
	}
}
