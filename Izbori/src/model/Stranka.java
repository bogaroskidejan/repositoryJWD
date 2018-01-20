package model;

public class Stranka {
		
	private int id;
	private String skracenica;
	private String naziv;
	private int idListe;
	
	public Stranka() {
		
	}

	public Stranka(String skracenica, String naziv, int idListe) {
		this.skracenica = skracenica;
		this.naziv = naziv;
		this.idListe = idListe;
	}

	public Stranka(int id, String skracenica, String naziv, int idListe) {
		this.id = id;
		this.skracenica = skracenica;
		this.naziv = naziv;
		this.idListe = idListe;
	}

	public int getId() {
		return id;
	}

	public String getSkracenica() {
		return skracenica;
	}

	public void setSkracenica(String skracenica) {
		this.skracenica = skracenica;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getIdListe() {
		return idListe;
	}

	public void setIdListe(int idListe) {
		this.idListe = idListe;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stranka other = (Stranka) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stranka id=" + id + ", skracenica=" + skracenica + ", naziv="
				+ naziv + ", idListe=" + idListe ;
	} 
	
}
