package model;

import java.util.ArrayList;
import java.util.List;

public class Lista {

	private int id;
	private String slogan;
	private int brojGlasova;
	private String sLice;
	private List<Stranka> stranke = new  ArrayList<>();
	
	public Lista() {
	}

	public Lista(int id, String slogan, int brojGlasova, String sLice) {
		this.id = id;
		this.slogan = slogan;
		this.brojGlasova = brojGlasova;
		this.sLice = sLice;
	}

	public Lista(String slogan, int brojGlasova, String sLice) {
		this.slogan = slogan;
		this.brojGlasova = brojGlasova;
		this.sLice = sLice;
	}

	public Lista(int id, String slogan, int brojGlasova, String sLice,
			List<Stranka> stranke) {
		super();
		this.id = id;
		this.slogan = slogan;
		this.brojGlasova = brojGlasova;
		this.sLice = sLice;
		this.stranke = stranke;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lista other = (Lista) obj;
		if (brojGlasova != other.brojGlasova)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public int getBrojGlasova() {
		return brojGlasova;
	}

	public void setBrojGlasova(int brojGlasova) {
		this.brojGlasova = brojGlasova;
	}

	public String getsLice() {
		return sLice;
	}

	public void setsLice(String sLice) {
		this.sLice = sLice;
	}

	public List<Stranka> getStranke() {
		return stranke;
	}

	public void setStranke(List<Stranka> stranke) {
		this.stranke = stranke;
	}

	@Override
	public String toString() {
		return "Lista id=" + id + ", slogan=" + slogan + ", brojGlasova="
				+ brojGlasova + ", sLice=" ;
	}
	
}
