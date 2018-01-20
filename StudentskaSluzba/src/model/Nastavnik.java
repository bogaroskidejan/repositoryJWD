package model;

import java.util.ArrayList;

public class Nastavnik {

	private static int brojacID = 0;
	
	private int id;
	private String ime;
	private String prezime;
	private String zvanje;
	private ArrayList<Predmet> nastavnikPredajePredmete = new ArrayList<Predmet>();

	public Nastavnik() {

	}

	public Nastavnik(int id, String ime, String prezime, String zvanje) {
		if (id == 0) {
			brojacID++;
			id = brojacID;
		}
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
	}

	public Nastavnik(int id, String ime, String prezime, String zvanje, ArrayList<Predmet> nastavnikPredajePredmete) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
		this.nastavnikPredajePredmete = nastavnikPredajePredmete;
	}

	public Nastavnik(String text) {
		String[] tokeni = text.split(",");

		if (tokeni.length != 4) {
			System.out.println("Greska pri ocitavanju " + text + " nastavnik!");
			System.exit(0);
		}
		id = Integer.parseInt(tokeni[0]);
		ime = tokeni[1];
		prezime = tokeni[2];
		zvanje = tokeni[3];
	}

	public static int getBrojacID() {
		return brojacID;
	}

	public static void setBrojacID(int brojacID) {
		Nastavnik.brojacID = brojacID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public ArrayList<Predmet> getNastavnikPredajePredmete() {
		return nastavnikPredajePredmete;
	}

	public void setNastavnikPredajePredmete(ArrayList<Predmet> nastavnikPredajePredmete) {
		this.nastavnikPredajePredmete = nastavnikPredajePredmete;
	}

	@Override
	public String toString() {
		return "Nastavnik sa ID " + id + " ima ime " + ime + " prezime " + prezime + " i zvanje " + zvanje + "\n";
	}

	public String toFileRepresentation() {
		StringBuilder bild = new StringBuilder();
		bild.append(id + ", " + ime + ", " + prezime + ", " + zvanje);
		return bild.toString();
	}

	public String toStringAllPredmet() {
		StringBuilder sb = new StringBuilder(
				"Nastavnik sa id " + id + " čije je ime i prezime " + ime + " " + prezime + " ima zvanje " + zvanje);

		if (nastavnikPredajePredmete != null) {
			sb.append(" i pohađa predmete\n");
			for (int i = 0; i < nastavnikPredajePredmete.size(); i++) {
				sb.append("\t" + nastavnikPredajePredmete.get(i).toString() + "\n");
			}
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nastavnik other = (Nastavnik) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
