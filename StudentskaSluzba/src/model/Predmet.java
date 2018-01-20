package model;

import java.util.ArrayList;

public class Predmet {

	private static int brojacID = 0;

	private int id;
	private String naziv;
	private ArrayList<Student> studenti = new ArrayList<Student>();
	private ArrayList<IspitnaPrijava> ispitnePrijave = new ArrayList<IspitnaPrijava>();

	public Predmet() {
	}

	public Predmet(int id, String naziv) {
		if (id == 0) {
			brojacID++;
			id = brojacID;
		}
		this.id = id;
		this.naziv = naziv;
	}

	public Predmet(int id, String naziv, ArrayList<Student> studenti, ArrayList<IspitnaPrijava> ispitnePrijave) {
		this.id = id;
		this.naziv = naziv;
		this.studenti = studenti;
		this.ispitnePrijave = ispitnePrijave;
	}

	public Predmet(String tekst) {
		String[] tokeni = tekst.split(",");
		// npr. 1,Matematika
		// tokeni 0 1

		if (tokeni.length != 2) {
			System.out.println("Gre\u0161ka pri o\u010Ditavanju predmeta " + tekst);
			System.exit(0);
		}

		id = Integer.parseInt(tokeni[0]);
		naziv = tokeni[1];

		if (brojacID < id) {
			brojacID = id;
		}
	}

	public String toFileRepresentation() {

		StringBuilder bild = new StringBuilder();
		bild.append(id + "," + naziv);
		return bild.toString();
	}

	@Override
	public String toString() {
		String ispis = "Predmet sa id " + id + " ima naziv " + naziv;
		return ispis;
	}

	public String toStringAllStudent() {
		StringBuilder sb = new StringBuilder("Predmet sa id " + id + " ima naziv " + naziv);

		if (studenti != null) {
			sb.append(" i poha\u0111aju ga studenti\n");
			for (int i = 0; i < studenti.size(); i++) {
				sb.append("\t" + studenti.get(i).toString() + "\n");
			}
		}

		return sb.toString();
	}

	public String toStringAllIspitnaPrijava() {
		StringBuilder sb = new StringBuilder("Predmet sa id " + id + " ima naziv " + naziv);

		if (ispitnePrijave != null) {
			sb.append(" za njega su prijavljene ispitne prijave\n");
			for (int i = 0; i < ispitnePrijave.size(); i++) {
				sb.append("\t" + ispitnePrijave.get(i).toString() + "\n");
			}
		}

		return sb.toString();
	}

	public boolean isti(Predmet pr) {
		boolean isti = false;
		if (id == pr.id)
			isti = true;
		return isti;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predmet other = (Predmet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
	}

	public ArrayList<IspitnaPrijava> getIspitnePrijave() {
		return ispitnePrijave;
	}

	public void setIspitnePrijave(ArrayList<IspitnaPrijava> ispitnePrijave) {
		this.ispitnePrijave = ispitnePrijave;
	}
}
