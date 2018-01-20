package menjacnicaBaze.model;

import java.sql.Date;

public class VrednostValute {
	
	private Valuta valuta;
	private Date datumListe;
	private double kupovni;
	private double prodajni;
	private double srednji;
	
	public VrednostValute() {
	}
	
	public VrednostValute(Valuta valuta, double kupovni, double prodajni) {
		this.valuta = valuta;
		this.kupovni = kupovni;
		this.prodajni = prodajni;
	}

	public VrednostValute(Valuta valuta, Date datumListe, double kupovni, double prodajni) {
		this.valuta = valuta;
		this.datumListe = datumListe;
		this.kupovni = kupovni;
		this.prodajni = prodajni;
	}
	
	public VrednostValute(Valuta valuta, Date datumListe, double kupovni, double prodajni, double srednji) {
		this.valuta = valuta;
		this.datumListe = datumListe;
		this.kupovni = kupovni;
		this.prodajni = prodajni;
		this.srednji = srednji;
	}
	
	public double srednji () {
		double srednji= (kupovni+ prodajni)/ 2;
		return srednji;
	}

	public Valuta getValuta() {
		return valuta;
	}
	
	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}
	
	public Date getDatumListe() {
		return datumListe;
	}
	
	public void setDatumListe(Date datumListe) {
		this.datumListe = datumListe;
	}
	
	public double getKupovni() {
		return kupovni;
	}
	
	public void setKupovni(double kupovni) {
		this.kupovni = kupovni;
	}
	
	public double getProdajni() {
		return prodajni;
	}

	public void setProdajni(double prodajni) {
		this.prodajni = prodajni;
	}

	public double getSrednji() {
		return srednji;
	}

	public void setSrednji(double srednji) {
		this.srednji = srednji;
	}
	
	@Override
	public String toString() {
		return "Vrednost valute " + valuta + ", na dan " + datumListe + ", kupovni kurs " + kupovni
				+ ", prodajni kurs " + prodajni + " i srednji "+ srednji;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VrednostValute other = (VrednostValute) obj;
		if (datumListe == null) {
			if (other.datumListe != null)
				return false;
		} else if (!datumListe.equals(other.datumListe))
			return false;
		if (valuta == null) {
			if (other.valuta != null)
				return false;
		} else if (!valuta.equals(other.valuta))
			return false;
		return true;
	}
	
}
