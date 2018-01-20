package menjacnicaBaze.model;

public class Valuta {
	
	private String oznaka;
	private String nazivValute;
	
	public Valuta() {
	}
	
	public Valuta(String oznaka, String nazivValute) {
		this.oznaka = oznaka;
		this.nazivValute = nazivValute;
	}
	
	public String getOznaka() {
		return oznaka;
	}
	
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	
	public String getNazivValute() {
		return nazivValute;
	}

	public void setNazivValute(String nazivValute) {
		this.nazivValute = nazivValute;
	}
	
	@Override
	public String toString() {
		return oznaka + " " + nazivValute;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valuta other = (Valuta) obj;
		if (oznaka == null) {
			if (other.oznaka != null)
				return false;
		} else if (!oznaka.equals(other.oznaka))
			return false;
		return true;
	}
	
}
