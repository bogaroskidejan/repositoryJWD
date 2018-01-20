package utils;

import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PomocnaKlasa {

	static Scanner sc = new Scanner(System.in);
	
	//citanje promenljive String
	public static String ocitajTekst(){
		String tekst = "";
		while(tekst == null || tekst.equals(""))
			tekst = sc.nextLine();
		
		return tekst;
	}
		
	//citanje promenljive integer
	public static int ocitajCeoBroj(){
		while (sc.hasNextInt()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		int ceoBroj = sc.nextInt();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return ceoBroj;
	}
		
	//citanje promenljive double
	public static float ocitajRealanBroj(){

		while (sc.hasNextFloat()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		float realanBroj = sc.nextFloat();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return realanBroj;
	}
		
	//citanje promenljive char
	public static char ocitajKarakter(){
		
		String text;
		while ( (text=sc.next())==null || text.length()!=1) {
			System.out.println("GRESKA - Pogresno unsesena vrednost za karakter, pokusajte ponovo: ");
			sc.nextLine();
		}
		char karakter = text.charAt(0);
		return karakter;
	}
	
	//citanje promenljive char
	public static char ocitajOdlukuOPotvrdi(String tekst){
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while( !(odluka == 'Y' || odluka == 'N') ){
			odluka = ocitajKarakter();
			if (!(odluka == 'Y' || odluka == 'N')) {
				System.out.println("Opcije su Y ili N");
			}
		}
		return odluka;
	}
	public static Date proveriDatum() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		String datumUnos = null;
		Date datum = null;
		do {
			System.out.println("Unesi datum u formatu dd.MM.yyyy.");
			datumUnos = sc.nextLine();
		} while (!proveriDatum(datumUnos));
		try {
			datum = sdf.parse(datumUnos);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datum;
	}

	public static boolean proveriDatum(String datum) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

		try {
			sdf.parse(datum);
			return true;
		} catch (Exception e) {
			System.out.println("Unesite datum u formatu dd.MM.yyyy.");
			return false;
		}

	}
	
	public static String datumSQL(Date datum) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datumUnos = df.format(datum);
		
		return datumUnos;
	}
	
	static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean isDouble(String s){
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	static boolean isBoolean(String s){
		try {
			Boolean.parseBoolean(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
